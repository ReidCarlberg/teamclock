/*
 * Created on Sep 4, 2006
 *
 */
package com.fivesticks.time.contactv2.forms.external;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.util.StringUtils;

import com.fivesticks.time.common.dao.GenericDAOFactory;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.contactv2.ContactV2KeyValue;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegate;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegateFactory;
import com.fivesticks.time.contactv2.forms.WebForm;
import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.lookups.LookupServiceDelegate;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;
import com.fivesticks.time.messages.Message;
import com.fivesticks.time.messages.MessageServiceDelegateFactory;
import com.fivesticks.time.messages.merge.MessageDataMerger;
import com.fivesticks.time.messages.merge.MessageMergerFactory;
import com.fivesticks.time.messages.util.ConvertMessageAndAddToQueueHelper;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegate;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;
import com.fivesticks.time.settings.FstxTimeSettings;
import com.fivesticks.time.settings.SettingsInitializer;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoPriorityTypeEnum;
import com.fivesticks.time.todo.ToDoStatusEnum;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.webwork.interceptor.ParameterAware;
import com.opensymphony.xwork.ActionSupport;

public class InputFromWebFormAction extends ActionSupport implements
        ParameterAware {

    private String contactform;

    private Map parameters;

    private ContactV2 contact = new ContactV2();

    private String contactdetail;

    private String resultLocationURL;

    private String result = "SUCCESS";
    
    
    public boolean isProbablySpam(String data) {
        
        boolean ret = false;
        
        data = data.toLowerCase();
        
        if (data.indexOf("<a href") > -1) {
            return true;
        }
        
        if (data.indexOf("[url=") > -1) {
            return true;
        }
        
        if (data.length() > 2000) {
            return true;
        }
        
        return false;
    }
    
    public String execute() throws Exception {

        if (this.isProbablySpam(this.getContactdetail())) {
            return SUCCESS + ".spam";
        }
        
        if (!StringUtils.hasText(this.getContactform())) {
            return GlobalForwardStatics.GLOBAL_LOGIN;
        }

        WebForm webForm = this.getWebForm(this.getContactform());

        if (webForm == null) {
            return GlobalForwardStatics.GLOBAL_LOGIN;
        }

        SystemOwner systemOwner = this.getSystemOwner(webForm);

        if (systemOwner == null) {
            return GlobalForwardStatics.GLOBAL_LOGIN;
        }

        contact.setOwnerKey(systemOwner.getKey());

        ContactV2ServiceDelegate csd = ContactV2ServiceDelegateFactory.factory
                .build(systemOwner);

        csd.save(contact);

        /*
         * do we have extra fields?
         */
        if (this.getParameters() != null) {
            for (Iterator iter = this.getParameters().keySet().iterator(); iter
                    .hasNext();) {
                String element = (String) iter.next();

                if (!element.startsWith("contact")) {

                    String[] values = (String[]) parameters.get(element);
                    if (values == null || values[0] == null
                            || values[0].length() == 0) {
                        // do nothing
                    } else {
                        for (int i = 0; i < values.length; i++) {
                            String currentValue = values[i];
                            if (currentValue != null
                                    && currentValue.length() > 0) {
                                String suffix = "";
                                if (values.length > 1) {
                                    suffix = "" + i + 1;
                                }
                                ContactV2KeyValue keyValue = new ContactV2KeyValue();
                                keyValue.setContactV2Id(contact.getId());
                                keyValue.setKey(element + suffix);
                                keyValue.setValue(values[i]);

                                csd.add(contact, keyValue);
                            }
                        }
                    }

                }

            }
        }

        /*
         * associate with Lookups
         */
        LookupServiceDelegate lsd = LookupServiceDelegateFactory.factory
                .build(systemOwner);

        Lookup contactClass = lsd.get(webForm.getContactClass());

        Lookup contactInterest = lsd.get(webForm.getContactInterest());

        Lookup contactSource = lsd.get(webForm.getContactSource());

        contact.setClasses(new TreeSet<Lookup>());
        contact.getClasses().add(contactClass);
        contact.setInterests(new TreeSet<Lookup>());
        contact.getInterests().add(contactInterest);
        contact.setSources(new TreeSet<Lookup>());
        contact.getSources().add(contactSource);


        /*
         * create a todo for the assignee.
         */
        FstxTimeSettings settings = new SettingsInitializer(systemOwner)
                .initialize();

        ToDo newToDo = new ToDo();
        newToDo.setAssignedToUser(webForm.getAssignToUsername());
        newToDo.setComplete(false);
        newToDo.setLinkedObjectType(ContactV2.class.getName());
        newToDo.setLinkedObjectId(contact.getId());

        String detail = "New Contact: " + this.getContact().getNameFormatted();

        if (this.getContactdetail() != null) {
            newToDo.setDetail(detail + " detail: " + this.getContactdetail());
        } else {
            newToDo.setDetail(detail);
        }

        newToDo.setEnteredByUser(webForm.getAssignToUsername());
        newToDo.setPriority(ToDoPriorityTypeEnum.Q1.getName());
        newToDo.setProjectId(settings.getTodoDefaultProject());
        newToDo.setStatus(ToDoStatusEnum.ASSIGNED.getName());
        newToDo.setOwnerKey(systemOwner.getKey());
        newToDo.setCreateTimestamp(new Date());

        GenericDAOFactory.factory.build().save(newToDo);

        ConvertMessageAndAddToQueueHelper queueHelper = new ConvertMessageAndAddToQueueHelper();

        QueuedMessageServiceDelegate qsd = QueuedMessageServiceDelegateFactory.factory
                .build(systemOwner);

        /*
         * 2006-09-16 Reid@fivesticks.com
         * 
         * if we're using the insecure version, this doesn't connect to the
         * queue.
         * 
         * this just adds it to the queue and the secure one handles the rest
         * from there.
         * 
         */
        /*
         * send notification
         */
        if (webForm.isSendNotification()) {

            User user = UserBDFactory.factory.build().getByUsername(
                    webForm.getAssignToUsername());

            Message message = MessageServiceDelegateFactory.factory.build(
                    systemOwner).get(webForm.getNotificationMessageId());

            MessageDataMerger messageDataMerger = MessageMergerFactory.build(
                    systemOwner, this.getContact(), message);

            Message toSend = messageDataMerger.getMergedMessage();

            queueHelper.convertAndAdd(systemOwner, settings, toSend, qsd,
                    webForm.getAssignToUsername(), user.getEmail(), user
                            .getUsername());

            if (webForm.getNotificationMessageRecipient() != null) {
                String[] additional = webForm.getNotificationMessageRecipient()
                        .split(";");
                ValidationHelper helper = new ValidationHelper();
                for (int i = 0; i < additional.length; i++) {
                    if (helper.isEmailAddress(additional[i])) {
                        queueHelper.convertAndAdd(systemOwner, settings,
                                toSend, qsd, webForm.getAssignToUsername(),
                                additional[i], additional[i]);
                    }
                }
            }

        }

        /*
         * send thank you
         */
        if (webForm.isSendThankYouMessage()) {

            Message message = MessageServiceDelegateFactory.factory.build(
                    systemOwner).get(webForm.getThankYouMessageId());

            MessageDataMerger messageDataMerger = MessageMergerFactory.build(
                    systemOwner, this.getContact(), message);

            Message toSend = messageDataMerger.getMergedMessage();

            queueHelper.convertAndAdd(systemOwner, settings, toSend, this
                    .getContact(), qsd, webForm.getAssignToUsername());

        }

        this.setResultLocationURL(webForm.getSuccessURL());

        if (webForm.isRedirectResponse())
            return SUCCESS;

        return SUCCESS + ".redirect";
    }

    public WebForm getWebForm(String form) {

        WebForm ret = null;

        String hql = " from " + WebForm.class.getName() + " where key = '"
                + form + "' and active = true";

        Collection r = GenericDAOFactory.factory.build().find(hql);

        if (r.size() == 1) {
            ret = (WebForm) r.toArray()[0];
        }

        return ret;
    }

    public SystemOwner getSystemOwner(WebForm webForm) {

        SystemOwner ret = null;

        String hql = " from " + SystemOwner.class.getName() + " where key = '"
                + webForm.getOwnerKey() + "' and activated = true";

        Collection r = GenericDAOFactory.factory.build().find(hql);

        if (r.size() == 1) {
            ret = (SystemOwner) r.toArray()[0];
        }

        return ret;

    }

    /**
     * @return Returns the contact.
     */
    public ContactV2 getContact() {
        return contact;
    }

    /**
     * @param contact
     *            The contact to set.
     */
    public void setContact(ContactV2 contact) {
        this.contact = contact;
    }

    /**
     * @return Returns the form.
     */
    public String getContactform() {
        return contactform;
    }

    /**
     * @param form
     *            The form to set.
     */
    public void setContactform(String form) {
        this.contactform = form;
    }

    /**
     * @return Returns the resultLocationURL.
     */
    public String getResultLocationURL() {
        return resultLocationURL;
    }

    /**
     * @param resultLocationURL
     *            The resultLocationURL to set.
     */
    public void setResultLocationURL(String resultLocationURL) {
        this.resultLocationURL = resultLocationURL;
    }

    /**
     * @return Returns the notes.
     */
    public String getContactdetail() {
        return contactdetail;
    }

    /**
     * @param notes
     *            The notes to set.
     */
    public void setContactdetail(String notes) {
        this.contactdetail = notes;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return the parameters
     */
    public Map getParameters() {
        return parameters;
    }

    /**
     * @param parameters
     *            the parameters to set
     */
    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }

}

/*
 * Created on Aug 18, 2005
 *
 */
package com.fivesticks.time.admin.xwork;

import java.util.Date;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.queuedmessages.QueuedMessage;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;

public class SuggestImprovementAction extends SessionContextAwareSupport {

    private String submitSuggestion;

    private String suggestion;

    public String execute() throws Exception {

        if (this.getSubmitSuggestion() == null) {
            return SUCCESS;
        }

        QueuedMessage qm = new QueuedMessage();
        if (this.getSessionContext().getUser() != null) {
            qm.setFromAddress(this.getSessionContext().getUser().getEmail());
            qm.setFromName(this.getSessionContext().getUser().getUsername());
        } else {
            qm.setFromAddress("info@fstxtime.com");
            qm.setFromName("FSTX Time Suggest Improvement");
        }
        qm.setModifiedByUsername("reid");
        qm.setOwnerKey("CXZASTPKGU");
        qm.setSendTime(new Date());
        qm.setToName("Reid Carlberg");
        qm.setToAddress("info@fivesticks.com");
        qm.setSubject("<<<FSTXTime Suggestion>>>");
        qm.setMessage(this.getSuggestion());
        
        QueuedMessageServiceDelegateFactory.factory.build("CXZASTPKGU").add(qm);
        
        this.getSessionContext().setMessage("Your suggestion has been sent.  Thanks!");
        return SUCCESS;
    }

    /**
     * @return Returns the submitSuggestion.
     */
    public String getSubmitSuggestion() {
        return submitSuggestion;
    }

    /**
     * @param submitSuggestion
     *            The submitSuggestion to set.
     */
    public void setSubmitSuggestion(String submitSuggestion) {
        this.submitSuggestion = submitSuggestion;
    }

    /**
     * @return Returns the suggestion.
     */
    public String getSuggestion() {
        return suggestion;
    }

    /**
     * @param suggestion
     *            The suggestion to set.
     */
    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}

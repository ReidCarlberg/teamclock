/*
 * Created on Sep 1, 2005
 *
 */
package com.fivesticks.time.contactv2.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegate;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegateFactory;
import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.lookups.LookupServiceDelegate;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupType;

public class ContactV2ModifySettingsAction extends SessionContextAwareSupport
        implements ContactV2ModifyContextAware {

    private ContactV2ModifyContext contactV2ModifyContext;

    private String saveSettings;

    private String cancelSettings;

    private String[] selectedMembers;

    private String lookupTypeShortName;

    private LookupType lookupType;

    private Collection settings;

    private String action;

    public String execute() throws Exception {

        if (this.getContactV2ModifyContext().getTarget() == null
                && this.getSaveSettings() == null
                && this.getCancelSettings() == null)
            return SUCCESS;

        if (this.getCancelSettings() != null)
            return SUCCESS;

        if (this.getLookupTypeShortName() == null
                && this.getContactV2ModifyContext().getLookupType() == null) {
            return SUCCESS;
        }

        if (this.getLookupTypeShortName() != null) {
            this.getContactV2ModifyContext().setLookupType(
                    LookupType.getByShortName(this.getLookupTypeShortName()));
        }

        this.setLookupType(this.getContactV2ModifyContext().getLookupType());

        if (this.getLookupType() == null) {
            return SUCCESS;
        }

        ContactV2ServiceDelegate csd = ContactV2ServiceDelegateFactory.factory
                .build(this.getSystemOwner());

        LookupServiceDelegate lsd = LookupServiceDelegateFactory.factory
                .build(this.getSystemOwner());

        String[] proto = {};

        Collection currentlySelected = null;

        if (this.getLookupType() == LookupType.CONTACT_CLASS) {
            currentlySelected = this.getContactV2ModifyContext().getTarget()
                    .getClasses();
        } else if (this.getLookupType() == LookupType.CONTACT_INTEREST) {
            currentlySelected = this.getContactV2ModifyContext().getTarget()
                    .getInterests();
        } else {
            currentlySelected = this.getContactV2ModifyContext().getTarget()
                    .getSources();
        }

        Collection currentlySelectedIds = new ArrayList();

        for (Iterator iter = currentlySelected.iterator(); iter.hasNext();) {
            Lookup element = (Lookup) iter.next();
            currentlySelectedIds.add("" + element.getId());
        }

        String[] current = (String[]) currentlySelectedIds.toArray(proto);

        if (this.getSaveSettings() == null) {
            this.setSelectedMembers(current);
            this.setSettings(lsd.getAll(this.getLookupType()));
            return INPUT;
        }

        Set<Lookup> newLu = new TreeSet<Lookup>();

        if (this.getSelectedMembers() != null) {
            for (int i = 0; i < this.getSelectedMembers().length; i++) {
                String idAsString = this.getSelectedMembers()[i];

                Long id = Long.parseLong(idAsString);

                Lookup l = lsd.get(id);

                newLu.add(l);
            }

        }

        if (this.getLookupType() == LookupType.CONTACT_CLASS) {
            this.getContactV2ModifyContext().getTarget().setClasses(newLu);
        } else if (this.getLookupType() == LookupType.CONTACT_INTEREST) {
            this.getContactV2ModifyContext().getTarget().setInterests(newLu);
        } else {
            this.getContactV2ModifyContext().getTarget().setSources(newLu);
        }

        csd.save(this.getContactV2ModifyContext().getTarget());

        return SUCCESS;
    }

    /**
     * @return Returns the cancelTeam.
     */
    public String getCancelSettings() {
        return cancelSettings;
    }

    /**
     * @param cancelTeam
     *            The cancelTeam to set.
     */
    public void setCancelSettings(String cancelTeam) {
        this.cancelSettings = cancelTeam;
    }

    /**
     * @return Returns the saveTeam.
     */
    public String getSaveSettings() {
        return saveSettings;
    }

    /**
     * @param saveTeam
     *            The saveTeam to set.
     */
    public void setSaveSettings(String saveTeam) {
        this.saveSettings = saveTeam;
    }

    /**
     * @return Returns the selectedMembers.
     */
    public String[] getSelectedMembers() {
        return selectedMembers;
    }

    /**
     * @param selectedMembers
     *            The selectedMembers to set.
     */
    public void setSelectedMembers(String[] selectedMembers) {
        this.selectedMembers = selectedMembers;
    }

    /**
     * @return Returns the contactV2ModifyContext.
     */
    public ContactV2ModifyContext getContactV2ModifyContext() {
        return contactV2ModifyContext;
    }

    /**
     * @param contactV2ModifyContext
     *            The contactV2ModifyContext to set.
     */
    public void setContactV2ModifyContext(
            ContactV2ModifyContext contactV2ModifyContext) {
        this.contactV2ModifyContext = contactV2ModifyContext;
    }

    /**
     * @return Returns the lookupType.
     */
    public LookupType getLookupType() {
        return lookupType;
    }

    /**
     * @param lookupType
     *            The lookupType to set.
     */
    public void setLookupType(LookupType lookupType) {
        this.lookupType = lookupType;
    }

    /**
     * @return Returns the lookupTypeName.
     */
    public String getLookupTypeShortName() {
        return lookupTypeShortName;
    }

    /**
     * @param lookupTypeName
     *            The lookupTypeName to set.
     */
    public void setLookupTypeShortName(String lookupTypeName) {
        this.lookupTypeShortName = lookupTypeName;
    }

    /**
     * @return Returns the settings.
     */
    public Collection getSettings() {
        return settings;
    }

    /**
     * @param settings
     *            The settings to set.
     */
    public void setSettings(Collection settings) {
        this.settings = settings;
    }

    /**
     * @return Returns the action.
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     *            The action to set.
     */
    public void setAction(String action) {
        this.action = action;
    }

}

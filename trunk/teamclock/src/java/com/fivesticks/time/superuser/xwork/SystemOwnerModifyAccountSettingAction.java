/*
 * Created on Nov 24, 2004 by Reid
 */
package com.fivesticks.time.superuser.xwork;

import java.util.ArrayList;
import java.util.Collection;

import com.fivesticks.time.common.delete.DeleteContext;
import com.fivesticks.time.common.delete.DeleteContextFactory;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.settings.event.SettingsEventPublisher;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegate;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;

/**
 * @author Reid
 */
public class SystemOwnerModifyAccountSettingAction extends SessionContextAwareSupport
        implements SystemOwnerManagerContextAware {

    

    private SystemOwnerManagerContext systemOwnerManagerContext;

    private Long target;

    private String submit;

    private String cancel;

    private String delete;

    private SystemSettingsModifyParameters modifyParameters = new SystemSettingsModifyParameters();

    public String execute() throws Exception {

        /*
         * We should have a system owner.
         */
        if ((this.getTarget() == null)
                && this.getSystemOwnerManagerContext().getActiveSystemOwner() == null) {
            this.addActionError("System Owner Not Found");
            return ERROR;
        }

        if (this.getTarget() != null) {

            SystemOwnerServiceDelegate sd = SystemOwnerServiceDelegateFactory.factory
                    .build();

            SystemOwner targetOwner = null;
            try {
                targetOwner = sd.get(this.getTarget());
            } catch (SystemOwnerServiceDelegateException e) {
                this.addActionError("System Owner Not Found");
                return ERROR;
            }

            if (targetOwner == null) {
                this.addActionError("System Owner Not Found");
                return ERROR;
            }

            this.getSystemOwnerManagerContext().setActiveSystemOwner(
                    targetOwner);

            this.prepareModifyParameters();

            return INPUT;
        }

        /*
         * Cancel should go back to the OwnerList
         */
        if (this.getCancel() != null) {
            return SUCCESS;
        }

        if (this.getDelete() != null) {
            FstxSystemOwnerDeleteCommand dc = new FstxSystemOwnerDeleteCommand(
                    this.getSystemOwnerManagerContext().getActiveSystemOwner());
            DeleteContext deleteContext = DeleteContextFactory.factory.build(dc, this
                    .getSessionContext().getSuccessOverride());
            this.getSessionContext().setDeleteContext(deleteContext);
            return GlobalForwardStatics.GLOBAL_COMMON_DELETE;
        }

        if (this.getSubmit() != null) {
            SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                    .build(this.getSystemOwnerManagerContext()
                            .getActiveSystemOwner());

            sd
                    .updateSetting(SettingTypeEnum.SETTING_MAX_ACTIVE_USERS,
                            this.getModifyParameters().getNumberOfActiveUsers()
                                    .longValue());

            sd.updateSetting(SettingTypeEnum.SYSTEM_IS_FREE_SYSTEM, this
                    .getModifyParameters().isFreeSystem());

            sd.updateSetting(
                    SettingTypeEnum.SYSTEM_CAN_USE_ACCOUNT_TRANSACTIONS, this
                            .getModifyParameters()
                            .isCanUseAccountTransactions());

            sd.updateSetting(SettingTypeEnum.SYSTEM_CAN_HAVE_EXTERNAL_USERS,
                    this.getModifyParameters().isCanHaveExternalUsers());

            sd.updateSetting(SettingTypeEnum.SYSTEM_CAN_USE_BETA_FEATURES, this
                    .getModifyParameters().isCanUseBetaFeatures());

            new SettingsEventPublisher().publishSettingsModifiedEvent(this
                    .getSystemOwnerManagerContext().getActiveSystemOwner());
            return SUCCESS;
        }
        return INPUT;
    }

    /**
     * @return Returns the systemOwnerManagerContext.
     */
    public SystemOwnerManagerContext getSystemOwnerManagerContext() {
        return systemOwnerManagerContext;
    }

    /**
     * @param systemOwnerManagerContext
     *            The systemOwnerManagerContext to set.
     */
    public void setSystemOwnerManagerContext(
            SystemOwnerManagerContext systemOwnerManagerContext) {
        this.systemOwnerManagerContext = systemOwnerManagerContext;
    }

    /**
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }

    public SystemSettingsModifyParameters getModifyParameters() {
        return modifyParameters;
    }

    private void prepareModifyParameters() {

        SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                .build(this.getSystemOwnerManagerContext()
                        .getActiveSystemOwner());

        this.modifyParameters.setNumberOfActiveUsers(new Long(sd
                .getSettingAsLong(SettingTypeEnum.SETTING_MAX_ACTIVE_USERS)));

        this.modifyParameters.setFreeSystem(sd
                .getSettingAsBoolean(SettingTypeEnum.SYSTEM_IS_FREE_SYSTEM));

        this.modifyParameters
                .setCanUseAccountTransactions(sd
                        .getSettingAsBoolean(SettingTypeEnum.SYSTEM_CAN_USE_ACCOUNT_TRANSACTIONS));

        this.modifyParameters
                .setCanUseBetaFeatures(sd
                        .getSettingAsBoolean(SettingTypeEnum.SYSTEM_CAN_USE_BETA_FEATURES));

        this.modifyParameters
                .setCanHaveExternalUsers(sd
                        .getSettingAsBoolean(SettingTypeEnum.SYSTEM_CAN_HAVE_EXTERNAL_USERS));

    }

    public Collection getMaxUserOptions() {
        Collection col = new ArrayList();
        col.add(new Long(3));
        col.add(new Long(5));
        col.add(new Long(10));
        col.add(new Long(20));
        col.add(new Long(50));
        return col;
    }

    public void setModifyParameters(
            SystemSettingsModifyParameters modifyParameters) {
        this.modifyParameters = modifyParameters;
    }
}
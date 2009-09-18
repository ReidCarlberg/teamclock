/*
 * Created on Sep 10, 2003
 *
 */
package com.fivesticks.time.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.delete.DeleteCommand;
import com.fivesticks.time.common.delete.DeleteContext;
import com.fivesticks.time.common.delete.DeleteContextFactory;
import com.fivesticks.time.common.util.TimezoneDateTimeResolver;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.dashboard.util.DashboardRecentAction;
import com.fivesticks.time.externaluser.common.ExternalUserSessionContext;
import com.fivesticks.time.menu.MenuSection;
import com.fivesticks.time.settings.FstxTimeSettings;
import com.fivesticks.time.settings.SettingFeatureSetTypeEnum;
import com.fivesticks.time.settings.broker.MasterSettingsBroker;
import com.fivesticks.time.settings.broker.RightsBroker;
import com.fivesticks.time.settings.broker.SystemRights;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.useradmin.UserTypeDeterminator;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fivesticks.time.useradmin.settings.UserSettingServiceDelegateFactory;
import com.fivesticks.time.useradmin.settings.UserSettingVO;
import com.fstx.stdlib.authen.AuthenticatedUser;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * <p>
 * If the settings initializer gets any more complicated, we should refactor it.
 * 
 * @version 8/21/2004
 * @author Reid
 * 
 */
public class SessionContext implements Serializable {

    private Log log = LogFactory.getLog(SessionContext.class);

    public final static String KEY = "session.context";

    private AuthenticatedUser user;

    private UserTypeEnum userTypeEnum;

    private SystemOwner systemOwner;

    // private SettingsBroker settings;

    private SystemRights rights;

    private DeleteContext deleteContext;

    private String successOverride;

    private MenuSection menuSection;

    private String message;
    
    private String fadeMessage;

    private ExternalUserSessionContext externalUserSessionContext;

    private TimezoneDateTimeResolver resolver;
    
    private UserSettingVO userSettings;

    private List recentActions = new ArrayList();



    /**
     * 
     */
    public SessionContext() {
        super();
        this.rights = new RightsBroker();
    }

    public SessionContext(AuthenticatedUser user, SystemOwner systemOwner) {
        this();
        this.user = user;
        this.setSystemOwner(systemOwner);
        if (this.user == null) {
            throw new RuntimeException(
                    "Attempting to set the authenticated user as null...");
        }
    }

    /**
     * @return
     */
    public AuthenticatedUser getUser() {
        return user;
    }

    public UserTypeEnum getUserTypeEnum() throws Exception {
        if (userTypeEnum == null) {
            userTypeEnum = new UserTypeDeterminator().getUserType(this
                    .getSystemOwner(), this.getUser().getUser());
        }
        return userTypeEnum;
    }

    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    public void setSystemOwner(SystemOwner systemOwner) {

        this.systemOwner = systemOwner;

        RightsBroker rightsBroker = new RightsBroker();
        rightsBroker.setOwner(systemOwner);
        this.rights = rightsBroker;
    }

    public void setUser(AuthenticatedUser user) {

        if (this.user != null) {
            throw new RuntimeException(
                    " You only can set a AuthenticatedUser once");
        }
        this.user = user;
    }

    /**
     * @return Returns the settings.
     */
    public FstxTimeSettings getSettings() {
        if (this.getUser() != null) {
            return MasterSettingsBroker.singleton.getSettings(this
                    .getSystemOwner(), this.getUser().getUser());
        }

        return MasterSettingsBroker.singleton.getSettings(
                this.getSystemOwner(), null);

    }

    public UserSettingVO getUserSettings()  {
        
        if (userSettings == null) {
            try {
                this.userSettings =UserSettingServiceDelegateFactory.factory.build(this.getSystemOwner()).find(
                        this.getUser().getUser());
            } catch (Exception e) {
                e.printStackTrace();
                userSettings = new UserSettingVO();
            } 
        }
        
        return userSettings;
        
    }
    
    /**
     * @param userSettings The userSettings to set.
     */
    public void setUserSettings(UserSettingVO userSettings) {
        this.userSettings = userSettings;
    }
    /**
     * @return Returns the deleteContext.
     */
    public DeleteContext getDeleteContext() {
        return deleteContext;
    }

    /**
     * @param deleteContext
     *            The deleteContext to set.
     */
    public void setDeleteContext(DeleteContext deleteContext) {
        this.deleteContext = deleteContext;
    }
    
    public String getCommonDelete(DeleteCommand deleteCommand) {
       DeleteContext dc = DeleteContextFactory.factory.build(deleteCommand);
       this.setDeleteContext(dc);
       return GlobalForwardStatics.GLOBAL_COMMON_DELETE;
    }

    public boolean isValid() {
        return this.getSystemOwner() != null && this.getUser() != null;
    }

    /**
     * @return Returns the successOverride.
     */
    public String getSuccessOverride() {
        return successOverride;
    }

    /**
     * @param successOverride
     *            The successOverride to set.
     */
    public void setSuccessOverride(String successOverride) {
        this.successOverride = successOverride;
    }

    public boolean isFeatureSuperUser() {
        boolean ret = this.getSettings().getFeatureSet() == SettingFeatureSetTypeEnum.SUPERUSER;

        return ret;

    }

    public boolean isFeatureComplete() {
        boolean ret = this.isFeatureSuperUser()
                || this.getSettings().getFeatureSet() == SettingFeatureSetTypeEnum.COMPLETE;

        return ret;
    }

    public boolean isFeatureGeneral() {
        boolean ret = this.isFeatureComplete()
                || this.getSettings().getFeatureSet() == SettingFeatureSetTypeEnum.GENERAL;

        return ret;
    }

    /**
     * @return Returns the menuSection.
     */
    public MenuSection getMenuSection() {
        return menuSection;
    }

    /**
     * @param menuSection
     *            The menuSection to set.
     */
    public void setMenuSection(MenuSection menuSection) {
        this.menuSection = menuSection;
    }

    public boolean isMessagePresent() {
        return message != null;
    }

    /**
     * @return Returns the message.
     */
    public String getMessage() {
        String ret = message;
        message = null;
        return ret;
    }

    /**
     * @param message
     *            The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return Returns the externalUserSessionContext.
     */
    public ExternalUserSessionContext getExternalUserSessionContext() {
        return externalUserSessionContext;
    }

    /**
     * @param externalUserSessionContext
     *            The externalUserSessionContext to set.
     */
    public void setExternalUserSessionContext(
            ExternalUserSessionContext externalUserSessionContext) {
        this.externalUserSessionContext = externalUserSessionContext;
    }

    /**
     * @return Returns the rights.
     */
    public SystemRights getRights() {
        return rights;
    }

    /**
     * @param rights
     *            The rights to set.
     */
    public void setRights(SystemRights rights) {
        this.rights = rights;
    }

    public SimpleDate resolveTime(SimpleDate target) {
        if (this.resolver == null) {
            this.resolver = new TimezoneDateTimeResolver();
            this.resolver.setTimezone(this.getSettings().getSystemTimeZone());
        }

        return this.resolver.resolve(target);
    }

    public Date resolveTime(Date target) {
        return resolveTime(SimpleDate.factory.build(target)).getDate();
    }

    public Date getResolvedNow() {
        return resolveTime(new Date());
    }

    /**
     * @return Returns the recentActions.
     */
    public List getRecentActions() {
        return recentActions;
    }

    /**
     * @param recentActions The recentActions to set.
     */
    public void setRecentActions(List recentActions) {
        this.recentActions = recentActions;
    }
    
    /*
     * 2006-06-25 reid@fivesticks.com 10 is an arbitrary limit.
     */
    public void addToRecentActions(DashboardRecentAction recentAction) {
        recentActions.add(0,recentAction);
        if (recentActions.size() > 10) {
            recentActions.remove(recentActions.size()-1);
        }
    }    
    
    public boolean isOwnerExpired() {
        return this.getSystemOwner().getExpirationDate().compareTo(new Date()) < 0;
    }
    
    public boolean isOwnerDemo() {
        return this.systemOwner.isDemo();
    }

    public boolean isFadeMessagePresent() {
        return fadeMessage != null;
    }
    
    /**
     * @return Returns the fadeMessage.
     */
    public String getFadeMessage() {
        
        String ret = fadeMessage;
        
        fadeMessage = null;
        
        return ret;
    }

    /**
     * @param fadeMessage The fadeMessage to set.
     */
    public void setFadeMessage(String fadeMessage) {
        this.fadeMessage = fadeMessage;
    }    
    
}
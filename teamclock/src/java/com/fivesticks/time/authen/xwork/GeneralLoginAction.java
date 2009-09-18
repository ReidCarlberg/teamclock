/*
 * Created on Aug 10, 2004 by Reid
 */
package com.fivesticks.time.authen.xwork;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.fivesticks.time.authen.events.AuthenticationEventPublisher;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.dashboard.util.DashboardMiscellaneousRecentAction;
import com.fivesticks.time.dashboard.xwork.DashboardType;
import com.fivesticks.time.externaluser.common.ExternalUserSessionContext;
import com.fivesticks.time.menu.MenuSection;
import com.fivesticks.time.systemowner.AccountType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.AuthenticatedUser;
import com.fstx.stdlib.authen.Authenticator;
import com.fstx.stdlib.authen.UnableToAuthenticateException;
import com.opensymphony.webwork.WebWorkStatics;
import com.opensymphony.xwork.ActionContext;

/**
 * <p>
 * Replaces the Struts dedicated login pages.
 * 
 * @author Reid modified by Shuji due to transfer strut to ww
 */
public class GeneralLoginAction extends SessionContextAwareSupport {

    private String username;

    private String password;

    private String loginButton;

    private String message;

    private String target;

    private String dashboardType;

    /**
     * @return Returns the message.
     */

    public String execute() throws Exception {

        this.getSessionContext().setMenuSection(MenuSection.SIGNIN);

        if (this.getSessionContext().getUser() != null
                && this.getSessionContext().getSystemOwner() != null) {
            return SUCCESS;
        }

        if (this.getLoginButton() == null) {
            /*
             * checks to see if we have an owner key to deal with.
             */
            if (this.getTarget() != null) {
                SystemOwner selected = SystemOwnerServiceDelegateFactory.factory
                        .build().get(this.getTarget());

                if (selected != null) {
                    if (!selected.isActivated())
                        return GlobalForwardStatics.GLOBAL_INACTIVE_ACCOUNT;

                    this.getSessionContext().setSystemOwner(selected);
                }
            }
            return INPUT;
        }

        if (this.getUsername() == null
                || this.getUsername().trim().length() == 0) {
            this.addFieldError("username", "Username is required.");
        }

        if (this.getPassword() == null
                || this.getPassword().trim().length() == 0) {
            this.addFieldError("password", "Password is required.");
        }

        if (this.hasErrors())
            return INPUT;

        AuthenticatedUser auser = null;

        try {
            auser = Authenticator.singleton.authenticate(getUsername(),
                    getPassword());

            // this.getSessionContext().setUser(auser);
        } catch (UnableToAuthenticateException e) {

            this
                    .addActionError("Could not authenticate that username and password.");
            return INPUT;
        }

        if (this.getLoginButton() != null) {
            SystemOwner asystemowner = null;
            try {
                asystemowner = SystemOwnerServiceDelegateFactory.factory
                        .build().get(auser.getUser());
            } catch (Exception e) {
                throw e;
            }
            if (this.getSessionContext().getSystemOwner() == null) {
                this.getSessionContext().setSystemOwner(asystemowner);
            } else {
                if (!this.getSessionContext().getSystemOwner().getKey().equals(
                        asystemowner.getKey())) {
                    this
                            .addActionError("Could not authenticate that username and password. (System Owner)");

                    return INPUT;
                }
            }

        }
        /*
         * validate against an inactive account
         */
        if (!this.getSessionContext().getSystemOwner().isActivated()) {
            HttpServletRequest request = (HttpServletRequest) ActionContext
                    .getContext().get(WebWorkStatics.HTTP_REQUEST);
            if (request != null) {
                HttpSession session = request.getSession();
                if (session != null) {
                    session.invalidate();
                }
            }
            return GlobalForwardStatics.GLOBAL_INACTIVE_ACCOUNT;
        }

        /*
         * log it.
         */
        AuthenticationEventPublisher.singleton.publishLogin(ActionContext
                .getContext(), this.getSessionContext(), auser.getUser());

        /*
         * This has to be the last thing we do. If we set this right away we run
         * the risk of having an invalid authenticated user in session. We do
         * additional validation, after the authen, if one of those fail we end
         * up with the user in session, thus the user will get a menu and be
         * able to view data for the system they are not auth for. -nah 11/30/04
         */
        this.getSessionContext().setUser(auser);

        // let's just see if they're timeclock only.
        if (this.getSessionContext().getUserTypeEnum() == UserTypeEnum.TIMECLOCK) {
            return GlobalForwardStatics.GLOBAL_TIMECLOCK_DASHBOARD;

            // 2004-12-29 ok now let's see if they're an external user.
        } else if (this.getSessionContext().getUserTypeEnum() == UserTypeEnum.EXTERNAL) {
            this.getSessionContext().setExternalUserSessionContext(
                    new ExternalUserSessionContext());
            return GlobalForwardStatics.GLOBAL_EXTERNAL_DASHBOARD;
            
            
        } 

        /*
         * RSC 2005-07-01 OK here were are, and now we need to see if they have
         * selected something interesting from the dashboard.
         */

        String destination = SUCCESS;

        if (StringUtils.hasText(this.getDashboardType())) {
            DashboardType type = DashboardType.getType(this.getDashboardType());

            if (type == DashboardType.DEFAULT) {
                if (this.getSessionContext().getUserSettings().getLoginDestination() != null) {
                    type = DashboardType.getType(this.getSessionContext().getUserSettings().getLoginDestination());
                }
            }
            if (type == DashboardType.DEFAULT) {
                type = DashboardType.STANDARD;
            }
            if (type != null && type != DashboardType.STANDARD) {
                destination = SUCCESS + "." + type.getName();
            }

        }
        
        if (this.getSessionContext().getSystemOwner().isRequiresAccountUpdate()) {
            if (this.getSessionContext().getUserTypeEnum() == UserTypeEnum.OWNERADMIN) {
                this.getSessionContext().setMessage("Your TeamClock.com account is out of date.  Please update your account below to continue.");
                destination = SUCCESS + ".accountUpdate";
            } else {
                this.getSessionContext().setMessage("Your TeamClock.com account is out of date.  Please have the account owner log in and update their information.");
            }
        } else if (this.getSessionContext().getSystemOwner().getAccountType() == AccountType.TIMECLOCK)  {
          destination = SUCCESS + ".TimeClockAdmin";  
        }
        
        /*
         * probably can refactor these out at some point.
         */
        DashboardMiscellaneousRecentAction a = new DashboardMiscellaneousRecentAction();
        a.setTypeName("Logged in.");
        a.setDescription("Welcome to TeamClock.com.");
        this.getSessionContext().addToRecentActions(a);

        
        
        // this.message= auser.getUser().toString();
        return destination;
    }

    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Returns the loginButton.
     */
    public String getLoginButton() {
        return loginButton;
    }

    /**
     * @param loginButton
     *            The loginButton to set.
     */
    public void setLoginButton(String loginButton) {
        this.loginButton = loginButton;
    }

//    /**
//     * @return Returns the sessionContext.
//     */
//    public SessionContext getSessionContext() {
//        return sessionContext;
//    }
//
//    /**
//     * @param sessionContext
//     *            The sessionContext to set.
//     */
//    public void setSessionContext(SessionContext sessionContext) {
//        this.sessionContext = sessionContext;
//    }

    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return Returns the target.
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(String target) {
        this.target = target;
    }

    public Collection getDashboardOptions() {
        return DashboardType.getAll();
    }

    /**
     * @return Returns the dashboardType.
     */
    public String getDashboardType() {
        return dashboardType;
    }

    /**
     * @param dashboardType
     *            The dashboardType to set.
     */
    public void setDashboardType(String dashboardType) {
        this.dashboardType = dashboardType;
    }
}
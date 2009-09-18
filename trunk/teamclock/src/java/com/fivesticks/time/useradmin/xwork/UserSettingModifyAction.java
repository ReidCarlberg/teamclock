/*
 * Created on May 26, 2005 by REID
 */
package com.fivesticks.time.useradmin.xwork;

import org.springframework.util.StringUtils;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.menu.MenuSection;
import com.fivesticks.time.useradmin.settings.UserSettingServiceDelegate;
import com.fivesticks.time.useradmin.settings.UserSettingServiceDelegateFactory;
import com.fivesticks.time.useradmin.settings.UserSettingVO;

/**
 * @author REID
 */
public class UserSettingModifyAction extends SessionContextAwareSupport
        implements UserModifyContextAware {

    private UserModifyContext userModifyContext;

    private String userSettingSubmit;

    private String userSettingCancel;

    private UserSettingServiceDelegate userSettingServiceDelegate;

    public String execute() throws Exception {

         this.getSessionContext().setMenuSection(MenuSection.PASSWORD);
        /*
         * If we are submiting setting...
         */
        if (StringUtils.hasText(this.getUserSettingSubmit())) {
            if (this.getUserSettingVO() == null) {
                return ERROR;
            }
            this.getUserSettingServiceDelegate().save(
                    this.getSessionContext().getUser().getUser(),
                    this.getUserSettingVO());
            
            this.getSessionContext().setUserSettings(null);
            
        } else if (StringUtils.hasText(this.getUserSettingCancel())) {
            this.setUserSettingVO(null);
        } else {
            /*
             * If we havn't submit yet...
             */
            UserSettingVO vo = this.getUserSettingServiceDelegate().find(
                    this.getSessionContext().getUser().getUser());

            this.setUserSettingVO(vo);
            return INPUT;
        }

        
        return SUCCESS;
    }

    public UserModifyContext getUserModifyContext() {
        return userModifyContext;
    }

    public void setUserModifyContext(UserModifyContext userModifyContext) {
        this.userModifyContext = userModifyContext;
    }

    public String getUserSettingSubmit() {
        return userSettingSubmit;
    }

    public void setUserSettingSubmit(String userSettingSubmit) {
        this.userSettingSubmit = userSettingSubmit;
    }

    public UserSettingServiceDelegate getUserSettingServiceDelegate() {
        if (this.userSettingServiceDelegate == null) {
            this.userSettingServiceDelegate = UserSettingServiceDelegateFactory.factory
                    .build(this.getSystemOwner());
        }

        return this.userSettingServiceDelegate;
    }

    public void setUserSettingServiceDelegate(
            UserSettingServiceDelegate userSettingServiceDelegate) {
        this.userSettingServiceDelegate = userSettingServiceDelegate;
    }

    public String getUserSettingCancel() {
        return userSettingCancel;
    }

    public void setUserSettingCancel(String userSettingCancel) {
        this.userSettingCancel = userSettingCancel;
    }

    public UserSettingVO getUserSettingVO() {
        return userModifyContext.getUserSettingVO();
        // return null;
    }

    public void setUserSettingVO(UserSettingVO userSettingVO) {
        userModifyContext.setUserSettingVO(userSettingVO);
    }
}

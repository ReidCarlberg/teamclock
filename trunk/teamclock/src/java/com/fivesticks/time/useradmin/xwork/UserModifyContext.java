/*
 * Created on Sep 13, 2004 by Reid
 */
package com.fivesticks.time.useradmin.xwork;

import com.fivesticks.time.useradmin.UserAndTypeVO;
import com.fivesticks.time.useradmin.settings.UserSettingVO;

/**
 * @author Reid
 */
public class UserModifyContext {

    public static final String KEY = "key.user.modify.context";

    private UserAndTypeVO target;

    private UserSettingVO userSettingVO;

    /**
     * @return Returns the target.
     */
    public UserAndTypeVO getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(UserAndTypeVO target) {
        this.target = target;
    }

    public UserSettingVO getUserSettingVO() {
        return userSettingVO;
    }

    public void setUserSettingVO(UserSettingVO userSettingVO) {
        this.userSettingVO = userSettingVO;
    }
}
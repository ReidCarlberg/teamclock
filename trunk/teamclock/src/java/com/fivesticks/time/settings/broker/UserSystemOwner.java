/*
 * Created on Nov 8, 2005
 *
 */
package com.fivesticks.time.settings.broker;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;

public class UserSystemOwner {

    private final User user;

    private final SystemOwner systemOwner;

    public UserSystemOwner(final User user, final SystemOwner systemOwner) {
        this.user = user;
        this.systemOwner = systemOwner;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object arg0) {

        boolean ret = false;

        if (arg0 instanceof UserSystemOwner) {
            UserSystemOwner test = (UserSystemOwner) arg0;
            if (test.user != null && this.user != null) {
                if (test.user.getId().equals(this.user.getId())
                        && test.systemOwner.getId().equals(
                                this.systemOwner.getId())) {
                    ret = true;
                }
            } else if (test.user == null && this.user == null) {
                
                if ( test.systemOwner.getId().equals(
                                this.systemOwner.getId())) {
                    ret = true;
                }
            }

        }
        return ret;

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {

        int ret = 0;

        if (this.user == null && this.systemOwner == null) {
            ret = -1;
        } else if (this.user == null) {
            ret = this.systemOwner.getId().intValue();
        } else {
            ret = this.user.getId().intValue()
                    * this.systemOwner.getId().intValue()
                    - this.user.getId().intValue();
        }
        return ret;
    }

    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    /**
     * @return Returns the user.
     */
    public User getUser() {
        return user;
    }

}

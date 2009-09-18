/*
 * Created on Nov 22, 2004 by Reid
 */
package com.fivesticks.time.accountactivity;

import com.fivesticks.time.activity.ActivityFilterVO;

/**
 * @author Reid
 */
public class AccountActivityFilter extends ActivityFilterVO {

    private Boolean unposted;

    /**
     * @return Returns the unposted.
     */
    public Boolean getUnposted() {
        return unposted;
    }

    /**
     * @param unposted
     *            The unposted to set.
     */
    public void setUnposted(Boolean unposted) {
        this.unposted = unposted;
    }
}
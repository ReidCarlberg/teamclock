/*
 * Created on Sep 17, 2005
 *
 */
package com.fivesticks.time.tokens;

import java.util.Date;

import com.fivesticks.time.common.dao.CriteriaParameters;

public class TokenCriteriaParameters extends Token implements CriteriaParameters {

    private Boolean activeSetting;
    private Date issuedRangeStart;
    private Date issuedRangeEnd;
    private Date revokedRangeStart;
    private Date revokedRangeEnd;
    /**
     * @return Returns the activeSetting.
     */
    public Boolean getActiveSetting() {
        return activeSetting;
    }
    /**
     * @param activeSetting The activeSetting to set.
     */
    public void setActiveSetting(Boolean activeSetting) {
        this.activeSetting = activeSetting;
    }
    /**
     * @return Returns the issuedRangeEnd.
     */
    public Date getIssuedRangeEnd() {
        return issuedRangeEnd;
    }
    /**
     * @param issuedRangeEnd The issuedRangeEnd to set.
     */
    public void setIssuedRangeEnd(Date issuedRangeEnd) {
        this.issuedRangeEnd = issuedRangeEnd;
    }
    /**
     * @return Returns the issuedRangeStart.
     */
    public Date getIssuedRangeStart() {
        return issuedRangeStart;
    }
    /**
     * @param issuedRangeStart The issuedRangeStart to set.
     */
    public void setIssuedRangeStart(Date issuedRangeStart) {
        this.issuedRangeStart = issuedRangeStart;
    }
    /**
     * @return Returns the revokedRangeEnd.
     */
    public Date getRevokedRangeEnd() {
        return revokedRangeEnd;
    }
    /**
     * @param revokedRangeEnd The revokedRangeEnd to set.
     */
    public void setRevokedRangeEnd(Date revokedRangeEnd) {
        this.revokedRangeEnd = revokedRangeEnd;
    }
    /**
     * @return Returns the revokedRangeStart.
     */
    public Date getRevokedRangeStart() {
        return revokedRangeStart;
    }
    /**
     * @param revokedRangeStart The revokedRangeStart to set.
     */
    public void setRevokedRangeStart(Date revokedRangeStart) {
        this.revokedRangeStart = revokedRangeStart;
    }
    
    
    
}

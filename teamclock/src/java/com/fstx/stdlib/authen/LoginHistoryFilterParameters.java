/*

 Five Sticks
 6957 W. North Ave., #202
 Chicago, IL 60302
 USA
 http://www.fivesticks.com
 mailto:info@fivesticks.com

 Copyright (c) 2003-2004, Five Sticks Publications, Inc.
 All rights reserved.

 Redistribution and use in source and binary forms, 
 with or without modification, are permitted provided
 that the following conditions are met:

 * Redistributions of source code must retain 
 the above copyright notice, this list of conditions 
 and the following disclaimer.
 * Redistributions in binary form must reproduce 
 the above copyright notice, this list of conditions 
 and the following disclaimer in the documentation 
 and/or other materials provided with the distribution.
 * Neither the name of the Five Sticks Publications, Inc.,
 nor the names of its contributors may be used to 
 endorse or promote products derived from this software 
 without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
 BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF 
 SUCH DAMAGE.

 license: http://www.opensource.org/licenses/bsd-license.php

 This software uses a variety of Open Source software as
 a foundation.  See the file 

 [your install]/WEB-INF/component-acknowledgement.txt
 
 For more information.
 */

package com.fstx.stdlib.authen;

import java.util.Date;

import com.fivesticks.time.common.dao.CriteriaParameters;

/**
 * @author Nick
 *  
 */
public class LoginHistoryFilterParameters implements CriteriaParameters {

    private String username = null;

    private Date dateFrom = null;

    private Date dateTo = null;

    private String type = null;

    private String ownerKey = null;

    private String ownerKeyForSuperUser = null;
    
    private Boolean groupByOwnerKey;
    
    private Boolean groupByUser;

    /**
     *  
     */
    public LoginHistoryFilterParameters() {
        super();

    }

    /**
     * @return
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * @return
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param date
     */
    public void setDateFrom(Date date) {
        dateFrom = date;
    }

    /**
     * @param date
     */
    public void setDateTo(Date date) {
        dateTo = date;
    }

    /**
     * @param string
     */
    public void setType(String string) {
        type = string;
    }

    /**
     * @param string
     */
    public void setUsername(String string) {
        username = string;
    }

    public String getOwnerKey() {
        return ownerKey;
    }

    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey;
    }

    /**
     * @return Returns the ownerKeyForSuperUser.
     */
    public String getOwnerKeyForSuperUser() {
        return ownerKeyForSuperUser;
    }

    /**
     * @param ownerKeyForSuperUser
     *            The ownerKeyForSuperUser to set.
     */
    public void setOwnerKeyForSuperUser(String ownerKeyForSuperUser) {
        this.ownerKeyForSuperUser = ownerKeyForSuperUser;
    }

    /**
     * @return Returns the groupByOwnerKey.
     */
    public Boolean getGroupByOwnerKey() {
        return groupByOwnerKey;
    }

    /**
     * @param groupByOwnerKey The groupByOwnerKey to set.
     */
    public void setGroupByOwnerKey(Boolean groupByOwnerKey) {
        this.groupByOwnerKey = groupByOwnerKey;
    }

    /**
     * @return Returns the groupByUser.
     */
    public Boolean getGroupByUser() {
        return groupByUser;
    }

    /**
     * @param groupByUser The groupByUser to set.
     */
    public void setGroupByUser(Boolean groupByUser) {
        this.groupByUser = groupByUser;
    }
}
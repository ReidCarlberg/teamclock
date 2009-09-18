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
/*
 * Created on Nov 13, 2003
 *
 */
package com.fivesticks.time.calendar;

import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Nick
 *  
 */
public class CalendarFilterParameters extends TcCalendar {

    private Long id;

    private String username;

    private SimpleDate start;

    private SimpleDate stop;

    private SimpleDate buildDate;

    private String description;

    private String ownerKey;

    public CalendarFilterParameters() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public SimpleDate getStart() {
        return start;
    }

    public SimpleDate getStop() {
        return stop;
    }

    public String getUsername() {
        return username;
    }

    public void setDescription(String string) {
        description = string;
    }

    public void setStart(SimpleDate date) {
        start = date;
    }

    public void setStop(SimpleDate date) {
        stop = date;
    }

    public void setUsername(String string) {
        username = string;
    }

    /**
     * @return
     */
    public SimpleDate getBuildDate() {
        return buildDate;
    }

    /**
     * @param date
     */
    public void setBuildDate(SimpleDate date) {
        buildDate = date;
    }

    /**
     * @return Returns the systemOwnerKey.
     */
    public String getOwnerKey() {
        return ownerKey;
    }

    /**
     * @param systemOwnerKey
     *            The systemOwnerKey to set.
     */
    public void setOwnerKey(String systemOwnerKey) {
        this.ownerKey = systemOwnerKey;
    }

    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }
}
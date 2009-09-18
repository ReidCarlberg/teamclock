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
 * Created on Nov 30, 2003
 *
 */
package com.fivesticks.time.timeclock;

import java.util.Date;

/**
 * @author REID
 *  
 */
public class TimeclockFilterParameters {

    private Timeclock timeclock = new Timeclock();

    private Date timestampRangeStart;

    private Date timestampRangeStop;

    private Date eventTimestampRangeStart;

    private Date eventTimestampRangeEnd;

    private String ownerKey;

    /**
     * @return
     */
    public String getComment() {
        return timeclock.getComment();
    }

    /**
     * @return
     */
    public String getEvent() {
        return timeclock.getEvent();
    }

    /**
     * @return
     */
    public Date getEventTimestamp() {
        return timeclock.getEventTimestamp();
    }

    public Date getEventTimestampRangeStart() {
        return eventTimestampRangeStart;
    }

    /**
     * @return
     */
    public Long getId() {
        return timeclock.getId();
    }

    /**
     * @return
     */
    public Date getTimestamp() {
        return timeclock.getTimestamp();
    }

    /**
     * @return
     */
    public String getUsername() {
        return timeclock.getUsername();
    }

    /**
     * @param comment
     */
    public void setComment(String comment) {
        timeclock.setComment(comment);
    }

    /**
     * @param event
     */
    public void setEvent(String event) {
        timeclock.setEvent(event);
    }

    /**
     * @param eventTimestamp
     */
    public void setEventTimestamp(Date eventTimestamp) {
        timeclock.setEventTimestamp(eventTimestamp);
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        timeclock.setId(id);
    }

    /**
     * @param timestamp
     */
    public void setTimestamp(Date timestamp) {
        timeclock.setTimestamp(timestamp);
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        timeclock.setUsername(username);
    }

    /**
     * @return
     */
    public Date getEventTimestampRangeEnd() {
        return eventTimestampRangeEnd;
    }

    /**
     * @param date
     */
    public void setEventTimestampRangeEnd(Date date) {
        eventTimestampRangeEnd = date;
    }

    /**
     * @param date
     */
    public void setEventTimestampRangeStart(Date date) {
        eventTimestampRangeStart = date;
    }

    /**
     * @return
     */
    public Date getTimestampRangeStart() {
        return timestampRangeStart;
    }

    /**
     * @return
     */
    public Date getTimestampRangeStop() {
        return timestampRangeStop;
    }

    /**
     * @param date
     */
    public void setTimestampRangeStart(Date date) {
        timestampRangeStart = date;
    }

    /**
     * @param date
     */
    public void setTimestampRangeStop(Date date) {
        timestampRangeStop = date;
    }

    public void setOwnerKey(String ownerKey) {

        this.ownerKey = ownerKey;
    }

    public String getOwnerKey() {
        return ownerKey;
    }
}
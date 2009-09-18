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
package com.fstx.stdlib.common.messages;

/**
 * @author Reid S. Carlberg
 * @version 1.0.0
 *  
 */
public class MessageImpl implements Message {

    private final String subject;

    private final String message;

    protected MessageImpl(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    /**
     * @see com.ess.messages.Token#getSubject()
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @see com.ess.messages.Token#getMessage()
     */
    public String getMessage() {
        return message;
    }

    public boolean equals(Object o) {
        boolean retValue = false;
        if (o instanceof MessageImpl) {
            MessageImpl test = (MessageImpl) o;
            while (true) {
                //				if (!(test.getFromName() == null && getFromName() == null)
                //					&& !(test.getFromName() != null
                //						&& test.getFromName().equals(getFromName()))) {
                //					break;
                //				}
                //				if (!(test.getFromAddress() == null && getFromAddress() ==
                // null)
                //					&& !(test.getFromAddress() != null
                //						&& test.getFromAddress().equals(getFromAddress()))) {
                //					break;
                //				}
                //				if (!(test.getReplyTo() == null && getReplyTo() == null)
                //					&& !(test.getReplyTo() != null
                //						&& test.getReplyTo().equals(getReplyTo()))) {
                //					break;
                //				}
                if (!(test.getSubject() == null && getSubject() == null)
                        && !(test.getSubject() != null && test.getSubject()
                                .equals(getSubject()))) {
                    break;
                }
                if (!(test.getMessage() == null && getMessage() == null)
                        && !(test.getMessage() != null && test.getMessage()
                                .equals(getMessage()))) {
                    break;
                }
                retValue = true;
                break;
            }

        }
        return retValue;
    }

    public String toString() {
        StringBuffer retValue = new StringBuffer();

        //		retValue.append("[" + getFromName() + "]");
        //		retValue.append("[" + getFromAddress() + "]");
        //		retValue.append("[" + getReplyTo() + "]");
        retValue.append("[" + getSubject() + "]");
        retValue.append("[" + getMessage() + "]");

        return retValue.toString();
    }

}
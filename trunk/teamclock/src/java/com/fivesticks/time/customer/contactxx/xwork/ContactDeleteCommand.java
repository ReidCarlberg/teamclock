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
 * Created on Feb 6, 2004
 *
 */
package com.fivesticks.time.customer.contactxx.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.delete.DeleteCommand;
import com.fivesticks.time.common.delete.DeleteCommandFailedException;
import com.fivesticks.time.customer.contactxx.Contact;
import com.fivesticks.time.customer.contactxx.ContactServiceDelegateException;
import com.fivesticks.time.customer.contactxx.ContactServiceDelegateFactory;

/**
 * @author REID
 *  
 */
public class ContactDeleteCommand extends DeleteCommand {

    private final Contact target;

    public ContactDeleteCommand(Contact target) {
        this.target = target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.delete.DeleteCommand#handleDelete()
     */
    protected void handleDelete(SessionContext sessionContext)
            throws DeleteCommandFailedException {
        try {
            ContactServiceDelegateFactory.factory.build(sessionContext.getSystemOwner()).delete(target);
        } catch (ContactServiceDelegateException e) {
            throw new DeleteCommandFailedException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.delete.DeleteCommand#getTargetDescription()
     */
    public String getTargetDescription() throws DeleteCommandFailedException {

        StringBuffer ret = new StringBuffer();

        ret.append("Contact entry for: " + target.getNameFirst() + " ");

        ret.append(target.getNameLast() + " ");

        

        return ret.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.delete.DeleteCommand#getEffectDescription()
     */
    public String getEffectDescription() throws DeleteCommandFailedException {
        return "This will permanently remove contact record..";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getXWorkSuccess()
     */
    public String getXWorkSuccess() throws DeleteCommandFailedException {

        return "delete-success-contact";
    }

}
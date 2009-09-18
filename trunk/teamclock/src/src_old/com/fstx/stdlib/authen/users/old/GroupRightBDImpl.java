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
 * Created on Nov 22, 2003
 *
 */
package com.fstx.stdlib.authen.users.old;

import java.util.Iterator;
import java.util.List;

import com.fivesticks.time.useradmin.FstxTimeSystemRights;
import com.fstx.stdlib.authen.users.UserGroup;
import com.fstx.stdlib.common.dao.todelete.DAOException;

/**
 * @author REID
 *  
 */
public class GroupRightBDImpl implements GroupRightBD {

    private GroupRightsDAO groupRightsDAO;

    public void add(FstxTimeSystemRights right, UserGroup group)
            throws GroupRightBDException {
        //be sure its not already a member
        List currentRights;
        try {
            currentRights = this.getGroupRightsDAO().find(
                    GroupRightsDAO.SELECT_BY_GROUP, group.getId().toString());

        } catch (DAOException e2) {
            throw new GroupRightBDException(e2);
        }
        Iterator i = currentRights.iterator();
        while (i.hasNext()) {
            GroupRights gr = (GroupRights) i.next();
            if (gr.getRightCode().equals(right.getName())) {
                throw new GroupRightBDException("already exists");
            }
        }

        //add it to the group
        GroupRights gr = new GroupRights();
        gr.setGroupId(group.getId());
        gr.setRightCode(right.getName());

        try {
            this.getGroupRightsDAO().save(gr);
        } catch (DAOException e1) {
            throw new GroupRightBDException(e1);
        }

    }

    public void remove(FstxTimeSystemRights right, UserGroup group)
            throws GroupRightBDException {
        throw new RuntimeException("not implmeneted!");
    }

    /**
     * @return
     */
    public GroupRightsDAO getGroupRightsDAO() {
        return groupRightsDAO;
    }

    /**
     * @param rightsDAO
     */
    public void setGroupRightsDAO(GroupRightsDAO rightsDAO) {
        groupRightsDAO = rightsDAO;
    }

}
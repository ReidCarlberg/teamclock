/*
 * Created on Feb 6, 2005 by REID
 */
package com.fivesticks.time.todo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author REID
 */
public class ToDoFilterBuilder {

    /*
     * Nick 2005-10-4 Added tag. --- Tag can contain markup(---). This will be
     * delt with at the bottem If the tag is null or empty we ignore it.
     * 
     * 2006-06-28 removed tag.
     */
    public ToDoCriteriaParameters buildIncompleteByAssignee(String username,
            String priority, String unprioritized, Boolean inSequence) {

        ToDoCriteriaParameters ret = new ToDoCriteriaParameters();

        ret.setTodoComplete("false");
        ret.setAssignedToUser(username);
//        ret.setOrderBySequenceAsc(inSequence);
        ret.setUnprioritized(new Boolean(unprioritized));
        ret.setPriority(priority);
//        ret.setTag(tag);
        return ret;
    }
    
    public ToDoCriteriaParameters buildIncompleteByAssigneeWithTags(String username,
            String priority, String unprioritized, Boolean inSequence) {

        ToDoCriteriaParameters ret = new ToDoCriteriaParameters();

        ret.setTodoComplete("false");
        ret.setAssignedToUser(username);
//        ret.setOrderBySequenceAsc(inSequence);
        ret.setUnprioritized(new Boolean(unprioritized));
        ret.setPriority(priority);
//        ret.setTag(tag);
        ret.setTagIsNotNull(Boolean.TRUE);
        return ret;
    }

    public ToDoCriteriaParameters buildAllByCustomer(SystemOwner systemOwner,
            Customer fstxCustomer) throws Exception {

        Collection projects = ProjectServiceDelegateFactory.factory.build(systemOwner)
                .getAllForCustomer(fstxCustomer);

        Collection ids = new ArrayList();

        for (Iterator iter = projects.iterator(); iter.hasNext();) {
            Project element = (Project) iter.next();
            ids.add(element.getId());
        }

        ToDoCriteriaParameters ret = new ToDoCriteriaParameters();

        ret.setProjectIdIn(ids);

        return ret;

    }
}
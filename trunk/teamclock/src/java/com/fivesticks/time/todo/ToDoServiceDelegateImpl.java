/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.todo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.common.AbstractServiceDelegateException;
import com.fivesticks.time.common.AbstractSessionContextAwareServiceDelegate;
import com.fivesticks.time.common.IdReadable;
import com.fivesticks.time.common.dao.GenericDAOFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectBDException;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.dashboard.xwork.DashboardContext;
import com.fivesticks.time.todo.util.ToDoProjectVO;
import com.fstx.stdlib.authen.users.User;

/**
 * @author REID
 * 
 */
public class ToDoServiceDelegateImpl extends AbstractSessionContextAwareServiceDelegate implements
        ToDoServiceDelegate {

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.ToDoServiceDelegate#findIncomplete()
     */
    public Collection findIncomplete() throws ToDoServiceDelegateException {
        ToDoCriteriaParameters params = new ToDoCriteriaParameters();

        params.setTodoComplete("false");

        return this.find(params);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.ToDoServiceDelegate#findIncomplete(com.fivesticks.time.customer.FstxCustomer)
     */
    public Collection findIncomplete(Customer fstxCustomer)
            throws ToDoServiceDelegateException {

        // throw new RuntimeException("not implemented");

        Collection projects;
        try {
            projects = ProjectServiceDelegateFactory.factory.build(
                    this.getSystemOwner()).getAllForCustomer(fstxCustomer);
        } catch (ProjectBDException e) {
            throw new ToDoServiceDelegateException(e);
        }
        ToDoCriteriaParameters params = new ToDoCriteriaParameters();
        params.setProjectIdIn(new ArrayList());
        for (Iterator iter = projects.iterator(); iter.hasNext();) {
            Project element = (Project) iter.next();
            params.getProjectIdIn().add(element.getId());
        }
        params.setTodoComplete("false");

        Collection ret = this.getDao().find(params);

        return ret;
    }



    public Collection findIncompleteByAssignee(User user1)
            throws ToDoServiceDelegateException {
        ToDoCriteriaParameters params = new ToDoCriteriaParameters();

        params.setTodoComplete("false");
        params.setAssignedToUser(user1.getUsername());
//        if (inSequence.booleanValue())
//            params.setOrderBySequenceAsc(inSequence);

        return this.find(params);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.ToDoServiceDelegate#find(com.fivesticks.time.todo.ToDoSearchParameters)
     */
    public Collection find(ToDoCriteriaParameters params)
            throws ToDoServiceDelegateException {

        try {
            this.handleDecorate(params);
        } catch (AbstractServiceDelegateException e) {
            throw new ToDoServiceDelegateException(e);
        }

        return this.getDao().find(params);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.ToDoServiceDelegate#get(java.lang.Long)
     */
    public ToDo get(Long id) throws ToDoServiceDelegateException {
        ToDo ret = (ToDo) this.getDao().get(id);

        try {
            this.handleValidate(ret);
        } catch (AbstractServiceDelegateException e) {
            throw new ToDoServiceDelegateException(e);
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.ToDoServiceDelegate#save(com.fivesticks.time.todo.ToDo)
     */
    public ToDo save(ToDo target) throws ToDoServiceDelegateException {

        try {
            this.handleDecorate(target);
        } catch (AbstractServiceDelegateException e) {
            throw new ToDoServiceDelegateException(e);
        }
        
        

        /*
         * 2006-06-15 not resolved.
         * 2006-07-05 resolved
         */
        if (target.getCreateTimestamp() == null) {
            target.setCreateTimestamp(this.getSessionContext().getResolvedNow());
        }
        target.setModifyDate(this.getSessionContext().getResolvedNow());
        target.setModifiedByUser(this.getSessionContext().getUser().getUsername());
        
        this.getDao().save(target);

        return target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.ToDoServiceDelegate#delete(com.fivesticks.time.todo.ToDo)
     */
    public void delete(ToDo target) throws ToDoServiceDelegateException {
        this.getDao().delete(target);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.ToDoServiceDelegate#findIncompleteAssignedToSomeoneElse(java.lang.String)
     */
    public Collection findIncompleteAssignedToSomeoneElse(String enteredby)
            throws ToDoServiceDelegateException {
        ToDoCriteriaParameters params = new ToDoCriteriaParameters();

        params.setTodoComplete("false");
        params.setEnteredByUser(enteredby);
        params.setNotAssignedTo(enteredby);

        return this.find(params);
    }

    public Collection findIncompleteAssignedToSomeoneElse(String enteredby,
            ToDoCriteriaParameters params) throws ToDoServiceDelegateException {

        ToDoCriteriaParameters paramsNew = new ToDoCriteriaParameters();

        paramsNew.setTodoComplete("false");
        // paramsNew.setEnteredByUser(enteredby);
        // paramsNew.setNotAssignedTo(enteredby);
        paramsNew.setEnteredByUser(params.getAssignedToUser());
        paramsNew.setNotAssignedTo(params.getAssignedToUser());

//        paramsNew.setTag(params.getTag());
        paramsNew.setProjectId(params.getProjectId());
        paramsNew.setPriority(params.getPriority());
        paramsNew.setOrderBy(params.getOrderBy());
        paramsNew.setSortDirection(params.getSortDirection());

        Collection col = this.find(paramsNew);

        return col;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.ToDoServiceDelegate#findIncompleteByAssignee(java.lang.String,
     *      java.lang.String)
     */
    public Collection findIncompleteByAssignee(String username, String priority)
            throws ToDoServiceDelegateException {
        ToDoCriteriaParameters params = new ToDoCriteriaParameters();

        params.setTodoComplete("false");
        params.setAssignedToUser(username);
        params.setPriority(priority);

        return this.find(params);
    }

    public Collection findIncompleteByAssignee(String username,
            String priority, String unprioritized)
            throws ToDoServiceDelegateException {
        ToDoCriteriaParameters params = new ToDoCriteriaParameters();

        params.setTodoComplete("false");
        params.setAssignedToUser(username);

        if (priority != null) {
            params.setPriority(priority);
//            params.setOrderBySequenceAsc(inSequence);
        } else if (unprioritized != null) {
            params.setUnprioritized(new Boolean(true));
        }

        return this.find(params);

        // return ret;
    }

    /*
     * basically just makes sure that we have a sequence. (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.ToDoServiceDelegate#resequenceIncomplete(java.lang.String,
     *      com.fivesticks.time.todo.ToDoPriorityTypeEnum)
     */
//    public void resequenceIncomplete(String username,
//            ToDoPriorityTypeEnum toDoPriorityTypeEnum)
//            throws ToDoServiceDelegateException {
//
//        this.resequenceIncomplete(username, toDoPriorityTypeEnum.getName());
//
//    }
//
//    public void resequenceIncomplete(String username, String priority)
//            throws ToDoServiceDelegateException {
//
//        ToDoCriteriaParameters params = new ToDoCriteriaParameters();
//
//        params.setTodoComplete("false");
//        params.setAssignedToUser(username);
//        params.setPriority(priority);
//        params.setOrderBySequenceAsc(new Boolean(true));
//        // decorate(params);
//
//        Collection ret = this.find(params);
//
//        // int i = 0;
//        // for (Iterator iter = ret.iterator(); iter.hasNext();) {
//        // i++;
//        // ToDo element = (ToDo) iter.next();
//        // element.setSequence(new Integer(i));
//        // this.save(element);
//        // }
//
//        SequenceSorter sorter = new SequenceSorter();
//        sorter.resequence(ret);
//        this.getDao().saveAll(ret);
//
//    }
//
//    public void resequenceIncompleteSooner(ToDo toDo)
//            throws ToDoServiceDelegateException {
//
//        ToDoCriteriaParameters params = new ToDoCriteriaParameters();
//
//        params.setOrderBySequenceAsc(new Boolean(true));
//
//        handleResequence(toDo, params);
//    }
//
//    public void resequenceIncompleteLater(ToDo toDo)
//            throws ToDoServiceDelegateException {
//
//        ToDoCriteriaParameters params = new ToDoCriteriaParameters();
//
//        params.setOrderBySequenceDesc(new Boolean(true));
//
//        handleResequence(toDo, params);
//
//    }
//
//    private void handleResequence(ToDo toDo, ToDoCriteriaParameters params)
//            throws ToDoServiceDelegateException {
//
//        params.setTodoComplete("false");
//        params.setAssignedToUser(toDo.getAssignedToUser());
//        params.setPriority(toDo.getPriority());
//
//        Collection ret = this.find(params);
//
//        SequenceSorter sorter = new SequenceSorter();
//
//        Collection sorted = sorter.reverse(ret, toDo);
//
//        this.getDao().saveAll(sorted);
//    }

    public void decorateWithTotalActivityTotals(ToDo targetToDo) throws ToDoServiceDelegateException  {
        
        StringBuffer sb = new StringBuffer();
        
        sb.append("select sum(act.elapsed), sum(act.chargeableElapsed) from ");
        sb.append(Activity.class.getName() + " as act where act.toDoId = " + targetToDo.getId());
        
        List results = GenericDAOFactory.factory.build().find(sb.toString(),null);
        
        Object[] o = (Object[])results.toArray()[0];
        
        Double elapsed = (Double) o[0];
        Double chargeableElapsed = (Double) o[1];
        
        targetToDo.setTotalElapsedHours(elapsed);
        targetToDo.setTotalChargeableElapsedHours(chargeableElapsed);
        
        this.save(targetToDo);
            
            
        
    }
    public List findDistinctToDoProjects(DashboardContext dashboardContext) {

        StringBuffer sb = new StringBuffer();

        sb.append("select new " + ToDoProjectVO.class.getName()
                + " (count(todo.id), proj.id, proj.shortName) from ");
        sb.append(ToDo.class.getName() + " as todo, ");
        sb.append(Project.class.getName() + " as proj ");
        sb.append(" where todo.projectId = proj.id ");

        if (dashboardContext.getToDoUsername() != null) {
            sb.append(" and  todo.assignedToUser = :toDoUsername ");
        }

        if (dashboardContext.getPriority() != null
                && !dashboardContext.getPriority().equals("all")) {
            sb.append(" and todo.priority = :priority ");
        }

        sb.append(" and todo.complete = false ");
        sb.append(" and todo.ownerKey = '" + this.getSystemOwner().getKey()
                + "' ");
        sb.append(" group by proj.id, proj.shortName ");
        sb.append(" order by proj.shortName ");

        List results = GenericDAOFactory.factory.build().find(sb.toString(),
                dashboardContext);

        return results;
    }

    public Collection findRelatedTodos(IdReadable linkedObject) throws ToDoServiceDelegateException {
        
        ToDoCriteriaParameters p = new ToDoCriteriaParameters();
        
        p.setLinkedObjectId(linkedObject.getId());
        p.setLinkedObjectType(linkedObject.getClass().getName());
        
        return this.find(p);
    }


}
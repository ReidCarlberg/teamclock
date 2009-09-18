/*
 * Created on Jun 17, 2004
 *
 */
package com.fivesticks.time.dashboard.xwork.legacy;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.dashboard.xwork.DashboardContext;
import com.fivesticks.time.dashboard.xwork.DashboardContextAware;
import com.fivesticks.time.todo.ToDoCriteriaParameters;
import com.fivesticks.time.todo.ToDoFilterBuilder;
import com.fivesticks.time.todo.ToDoPriorityTypeEnum;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateException;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.legacy.ToDoTagListBuilder;

/**
 * @author Reid
 * 
 */
public class TagListAJAX extends SessionContextAwareSupport implements
        DashboardContextAware {

//    private Log log = LogFactory.getLog(TagListAJAX.class);

    private static int RETURN_MAX = 249;
   

   
    private DashboardContext dashboardContext;

   

    public String execute() throws Exception {
      

        return SUCCESS;
    }

    /**
     * @return Returns the dashboardContext.
     */
    public DashboardContext getDashboardContext() {
        return dashboardContext;
    }

    /**
     * @param dashboardContext
     *            The dashboardContext to set.
     */
    public void setDashboardContext(DashboardContext dashboardContext) {
        this.dashboardContext = dashboardContext;
    }

   
    
    
    /*
     * Grab a list off all the tags currently in incomplete todos for the given system..
     */
    public Collection getTags() {
        Set set = new TreeSet();
        Collection todos = null;
        try {
            ToDoServiceDelegate todosd = ToDoServiceDelegateFactory.factory.build(this
                    .getSessionContext().getSystemOwner());

            ToDoCriteriaParameters filter = new ToDoFilterBuilder()
                    .buildIncompleteByAssignee(null, ToDoPriorityTypeEnum.Q1
                            .getName(), "true", new Boolean(true), null);
            filter.setReturnMaximum(RETURN_MAX);
            todos = todosd.find(filter);

        } catch (ToDoServiceDelegateException e) {

            e.printStackTrace();
        }
       
//        for (Iterator iter = todos.iterator(); iter.hasNext();) {
//            ToDo todo = (ToDo) iter.next();
//           
//            for (Iterator iterator = todo.getTags().iterator(); iterator
//                    .hasNext();) {
//                String element = (String) iterator.next();
//                
//                set.add(element);
//            }
//            // set.addAll(todo.getTags());
//        }

        set.addAll(new ToDoTagListBuilder().getTags(todos));
        
        return set;

    }

    
}
/*
 * Created on Jun 17, 2004
 *
 */
package com.fivesticks.time.dashboard.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.menu.MenuSection;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.util.ToDo2JSONConverter;
import com.fivesticks.time.useradmin.UserTypeEnum;

/**
 * 2006-06-13 updated to JSON
 * 
 * @author Reid
 * 
 */
public class GetToDoListAction extends AbstractDashboardJSONAction {

    // private Log log = LogFactory.getLog(ViewDashboardToDoAJAX.class);

    private Collection myIncompleteToDos;

    private Collection delegatedToDos;

    public String execute() throws Exception {
        this.getSessionContext().setMenuSection(MenuSection.HOME);

        // System.out.println("HIsss");
        String toDoUsername = this.getSessionContext().getUser().getUsername();
        
        /*
         * 2006-06-28 should be able to eliminate
         */
//        if (this.getDashboardContext().getToDoUsername() == null)
//            this.getDashboardContext().setToDoUsername(toDoUsername);


        // todos
        ToDoServiceDelegate todosd = ToDoServiceDelegateFactory.factory
                .build(this.getSessionContext());

        if (this.getSessionContext().getUserTypeEnum() == UserTypeEnum.STANDARD) {
            this.getDashboardContext().setToDoUsername(toDoUsername);
        }

        this.getDashboardContext().getTodoFilter().setReturnMaximum(249);

        Collection rawToDos = todosd.find(this.getDashboardContext()
                .getTodoFilter());



        this.getSessionContext().setSuccessOverride(
                GlobalForwardStatics.GLOBAL_DASHBOARD);

        this.setJsonDataAsString(new ToDo2JSONConverter(this
                .getSessionContext()).convert(rawToDos)
                .toString());

        return SUCCESS;
    }

    /**
     * @return
     */
    public Collection getMyIncompleteToDos() {
        return myIncompleteToDos;
    }

    /**
     * @param collection
     */
    public void setMyIncompleteToDos(Collection collection) {
        myIncompleteToDos = collection;
    }

    /**
     * @return Returns the delegatedToDos.
     */
    public Collection getDelegatedToDos() {
        return delegatedToDos;
    }

    /**
     * @param delegatedToDos
     *            The delegatedToDos to set.
     */
    public void setDelegatedToDos(Collection delegatedToDos) {
        this.delegatedToDos = delegatedToDos;
    }

}
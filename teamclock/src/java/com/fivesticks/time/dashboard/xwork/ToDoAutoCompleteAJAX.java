/*
 * Created on Jun 17, 2004
 *
 */
package com.fivesticks.time.dashboard.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.json.JSONArray;
import org.springframework.util.StringUtils;

import com.fivesticks.time.customer.Project;
import com.fivesticks.time.useradmin.UserAndTypeVO;
import com.fivesticks.time.useradmin.UserServiceDelegate;
import com.fivesticks.time.useradmin.UserServiceDelegateException;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fivesticks.time.useradmin.xwork.UserListBroker;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 * 
 */
public class ToDoAutoCompleteAJAX extends AbstractDashboardJSONAction {

//    private Log log = LogFactory.getLog(ToDoAutoCompleteAJAX.class);

    

    int maxAutoCompleteOptions = 26;

    private Collection projects;

    private String searchTerm;

    private UserServiceDelegate userServiceDelegate;

    private ArrayList buildAutoCompleteOptionsList() throws Exception {
        ArrayList ret = new ArrayList();

        //2006-06-14 reid nothing in there then return quick.
        if (!StringUtils.hasText(this.getSearchTerm())) {
            return ret;
        }
        /*
         * Options project--- invalid project--- project---user
         * 
         * project---user---
         */
        if (this.getSearchTerm().indexOf(" ") > 0) {
            String[] tok = this.getSearchTerm().split(" ");
            if (tok.length > 0) {

                Project project = this.getProject(tok[0]);
                if (project == null) {
                    return ret;
                }
                /*
                 * We must have a project and a user
                 */
                /*
                 * 2006-07-14 rsc checking for space
                 */
                if (tok.length > 2) {
                    return ret;
                }
//                String userSearch = "";
//                if (tok.length == 2) {
//                    userSearch = tok[1];
//                }
                String userSearch = "";
                if (tok.length > 1) {
                    userSearch = tok[1];
                }
                Collection potentialusers = this.getPotentialUsers(userSearch);
                for (Iterator iter = potentialusers.iterator(); iter.hasNext();) {
                    User userElement = (User) iter.next();
                    ToDoAutoCompleteOptionVo vo = new ToDoAutoCompleteOptionVo(
                            project, userElement);
                    ret.add(vo);
                }

            } else {
                return ret;

            }

            // if(tok.length!= 2){
            // return ret;
            // }

            return ret;
        }

        if (projects == null) {
            projects = new ArrayList();
            projects.addAll(this.getProjects());
        }

        for (Iterator iter = projects.iterator(); iter.hasNext();) {

            Project element = (Project) iter.next();

            if (element.getLongName().toLowerCase().startsWith(
                    this.getSearchTerm().toLowerCase())) {
                ToDoAutoCompleteOptionVo vo = new ToDoAutoCompleteOptionVo(
                        element, null);
                ret.add(vo);
            } else if (element.getShortName().toLowerCase().startsWith(
                    this.getSearchTerm().toLowerCase())) {
                ToDoAutoCompleteOptionVo vo = new ToDoAutoCompleteOptionVo(
                        element, null);
                ret.add(vo);
            }

        }

        return ret;
    }

    public String execute() throws Exception {

        JSONArray array = new JSONArray();
        
        Collection c = this.getAutoCompleteOptions();
        
        for (Iterator iter = c.iterator(); iter.hasNext();) {
            ToDoAutoCompleteOptionVo element = (ToDoAutoCompleteOptionVo) iter.next();
            
            StringBuffer b = new StringBuffer();
            
            b.append(element.getProject().getShortName());
            b.append(" ");
            
            
            if (element.getUser() != null) {
                
                b.append(element.getUser().getUsername());
                b.append(" ");
            }
            
            array.put(b.toString());
            
        }
        
        this.setJsonDataAsString(array.toString());
        
        return SUCCESS;
    }

    public Collection getActiveUsers() throws Exception {
        return UserListBroker.singleton.getActiveUserList(this
                .getSessionContext().getSystemOwner());
    }

    public Collection getAutoCompleteOptions() throws Exception {
        ArrayList all = this.buildAutoCompleteOptionsList();

        return this.limitResults(all);
    }



    private Collection getPotentialUsers(String userSearch)
            throws UserServiceDelegateException {
        Collection ret = new ArrayList();

        Collection col = this.getUserServiceDelegate().listUserAndType();

        for (Iterator iter = col.iterator(); iter.hasNext();) {
            // Object element = (Object) iter.next();

            UserAndTypeVO element = (UserAndTypeVO) iter.next();

            if (!element.getUserTypeEnum().getPublicName().equals(
                    UserTypeEnum.EXTERNAL.getPublicName())
                    && !element.getUser().isBooleanInactive()) {

                if (element.getUser().getUsername().startsWith(userSearch)) {
                    ret.add(element.getUser());

                }
            }

        }
        return ret;
    }

    private Project getProject(String target) throws Exception {
        for (Iterator iter = this.getProjects().iterator(); iter.hasNext();) {
            Project element = (Project) iter.next();

            if (element.getLongName().toLowerCase()
                    .equals(target.toLowerCase())) {
                return element;
            }
            if (element.getShortName().toLowerCase().equals(
                    target.toLowerCase())) {
                return element;
            }

        }
        return null;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    private UserServiceDelegate getUserServiceDelegate() {
        if (userServiceDelegate == null) {
            this.userServiceDelegate = UserServiceDelegateFactory.factory.build(this
                    .getSystemOwner());

        }
        return userServiceDelegate;

    }

    private Collection limitResults(ArrayList all) {

        return all.subList(0,
                all.size() > maxAutoCompleteOptions ? maxAutoCompleteOptions
                        : all.size());

    }



    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void setUserServiceDelegate(UserServiceDelegate userServiceDelegate) {
        this.userServiceDelegate = userServiceDelegate;
    }

}
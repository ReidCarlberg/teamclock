/*
 * Created on May 17, 2006
 *
 */
package com.fivesticks.time.todo.legacy;


public class ToDoTagListBuilder {

//    public Collection getTags(Collection todos) {
//        
//        Collection ret = new ArrayList();
//        
//        for (Iterator iter = todos.iterator(); iter.hasNext();) {
//            Object element = (Object) iter.next();
//            
//            if (element instanceof ToDo) {
//                ret.addAll(this.getTags((ToDo)element));
//            } else if (element instanceof ToDoDisplayWrapper) {
//                ret.addAll(this.getTags((ToDoDisplayWrapper)element));
//            }
//            
//        }
//        
//        return ret;
//        
//    }
//    
//    public Collection getTags(ToDoDisplayWrapper toDoDisplayWrapper) {
//        return this.getTags(toDoDisplayWrapper.getTarget());
//    }
//    public Collection getTags(ToDo todo) {
//        
//        Collection ret = new ArrayList();
//        
//        if (todo.getTag() != null && todo.getTag().trim().length() > 0) {
//            String[] tags = todo.getTag().split(" ");
//            for (int i = 0; i < tags.length; i++) {
//                ret.add(tags[i]);
//            }
//        }
//        
//        return ret;
//    }
}

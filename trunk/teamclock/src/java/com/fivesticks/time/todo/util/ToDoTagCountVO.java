/*
 * Created on Jun 27, 2006
 *
 */
package com.fivesticks.time.todo.util;

public class ToDoTagCountVO implements Comparable {

    private int count;
    
    private String tag;

    /**
     * @return Returns the count.
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count The count to set.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return Returns the label.
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param label The label to set.
     */
    public void setTag(String label) {
        this.tag = label;
    }

    public int compareTo(Object o) {
        
        if (!(o instanceof ToDoTagCountVO)) {
            return -1;
        }
        
        ToDoTagCountVO c = (ToDoTagCountVO) o;
        
        //return c.getLabel().compareTo(this.getLabel());
        
        return this.getTag().compareTo(c.getTag());
    }
    
    
    
}

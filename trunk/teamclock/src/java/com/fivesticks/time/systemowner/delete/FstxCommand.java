/*
 * Created on Dec 2, 2004
 *
 * 
 */
package com.fivesticks.time.systemowner.delete;

/**
 * @author Nick
 * 
 *  
 */
public interface FstxCommand { //extends SessionContextAware {

    public void execute() throws Exception;

    public void setTarget(Object target);

    public Object getTarget();
}
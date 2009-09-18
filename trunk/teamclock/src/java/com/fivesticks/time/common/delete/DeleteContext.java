/*
 * Created on Sep 29, 2004 by Reid
 */
package com.fivesticks.time.common.delete;

/**
 * @author Reid
 */
public interface DeleteContext {

    public DeleteCommand getCommand();

    public String getDescription();

    public String getEffect();

    public String getSuccess();

    public boolean isValid();

}
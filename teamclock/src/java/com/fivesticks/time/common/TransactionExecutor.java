/*
 * Created on Dec 7, 2005 by Reid
 */
package com.fivesticks.time.common;

public interface TransactionExecutor {

    public void execute(Command command);
}

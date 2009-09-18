/*
 * Created on Sep 30, 2004 by Reid
 */
package com.fivesticks.time.common.delete;

/**
 * @author Reid
 */
public class DeleteContextFactory {

    public static final DeleteContextFactory factory = new DeleteContextFactory();

    public DeleteContext build(DeleteCommand deleteCommand) {
        DeleteContextImpl ret = new DeleteContextImpl(deleteCommand);

        return ret;
    }

    public DeleteContext build(DeleteCommand deleteCommand,
            String successOverride) {
        DeleteContextImpl ret = new DeleteContextImpl(deleteCommand);
        ret.setSuccessOverride(successOverride);
        return ret;
    }
}
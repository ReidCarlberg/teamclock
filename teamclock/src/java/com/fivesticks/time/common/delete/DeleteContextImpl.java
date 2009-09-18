/*
 * Created on Sep 30, 2004 by Reid
 */
package com.fivesticks.time.common.delete;

/**
 * @author Reid
 */
class DeleteContextImpl implements DeleteContext {

    private final DeleteCommand deleteCommand;

    private String successOverride;

    /**
     *  
     */
    public DeleteContextImpl(DeleteCommand deleteCommand) {
        this.deleteCommand = deleteCommand;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteContext#getCommand()
     */
    public DeleteCommand getCommand() {

        return deleteCommand;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteContext#getDescription()
     */
    public String getDescription() {

        try {
            return deleteCommand.getTargetDescription();
        } catch (DeleteCommandFailedException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteContext#getSuccess()
     */
    public String getSuccess() {

        if (this.getSuccessOverride() == null) {
            try {
                return deleteCommand.getXWorkSuccess();
            } catch (DeleteCommandFailedException e) {

                throw new RuntimeException(e);
            }
        } else {
            return this.getSuccessOverride();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteContext#getEffect()
     */
    public String getEffect() {

        try {
            return deleteCommand.getEffectDescription();
        } catch (DeleteCommandFailedException e) {
            throw new RuntimeException(e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteContext#isValid()
     */
    public boolean isValid() {

        return !deleteCommand.executed;
    }

    /**
     * @return Returns the successOverride.
     */
    public String getSuccessOverride() {
        return successOverride;
    }

    /**
     * @param successOverride
     *            The successOverride to set.
     */
    public void setSuccessOverride(String successOverride) {
        this.successOverride = successOverride;
    }
}
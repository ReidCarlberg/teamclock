/*
 * Created on Apr 30, 2006
 *
 */
package com.fivesticks.time.tokens.xwork;

import com.fivesticks.time.tokens.TokenServiceDelegate;
import com.fivesticks.time.tokens.TokenServiceDelegateFactory;

public abstract class AbstractTokenGenerateAction extends AbstractTokenAction {

    private String generateCancel;
    private String generateConfirm;

    public String execute() throws Exception {
    
        if (this.getGenerateCancel() != null)
            return SUCCESS;
        
        if (this.getGenerateConfirm() == null)
            return INPUT;
        
        TokenServiceDelegate sd =  TokenServiceDelegateFactory.factory.build();
        
        return this.handleExecute(sd);
        
    }
    
    protected abstract String handleExecute(TokenServiceDelegate sd) throws Exception;
    
    /**
     * @return Returns the cancelGenerate.
     */
    public String getGenerateCancel() {
        return generateCancel;
    }

    /**
     * @return Returns the confirmGenerate.
     */
    public String getGenerateConfirm() {
        return generateConfirm;
    }

    /**
     * @param cancelGenerate The cancelGenerate to set.
     */
    public void setGenerateCancel(String cancelGenerate) {
        this.generateCancel = cancelGenerate;
    }

    /**
     * @param confirmGenerate The confirmGenerate to set.
     */
    public void setGenerateConfirm(String confirmGenerate) {
        this.generateConfirm = confirmGenerate;
    }

}

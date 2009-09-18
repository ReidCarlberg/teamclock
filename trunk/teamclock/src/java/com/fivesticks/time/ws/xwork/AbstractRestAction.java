/*
 * Created on Apr 26, 2006
 *
 */
package com.fivesticks.time.ws.xwork;

import com.fivesticks.time.common.ParamValidator;
import com.fivesticks.time.ws.AbstractAuthBasedAuthenticationService;
import com.opensymphony.xwork.ActionSupport;

public abstract class AbstractRestAction extends
        AbstractAuthenticationBasedServiceSupportAware {

    private void handlePrepare() {

        String r = this.getAuthenticationBasedServiceSupport()
                .handleCommonPrep(this.getToken(), this.getUsername(),
                        this.getPassword());

        if (r != null) {
            this.setResult(r);
            throw new RuntimeException("rest failed " + r);
        }

    }
    
    protected void handleValidate() throws Exception {
//        if (!ParamValidator.isSearchable(this.getUsername())
//                || !ParamValidator.isSearchable(this.getPassword())
//                || !ParamValidator.isSearchable(this.getToken())
//                || !ParamValidator.isSearchable(this.getAction())) {
//            throw new RuntimeException();
//        }

        if ( !ParamValidator.isSearchable(this.getToken())
                || !ParamValidator.isSearchable(this.getAction())) {
            throw new RuntimeException();
        }

        
        this.handleActionTypeValidate();
        
    }
    
    public String execute() throws Exception {

        try {
            this.handleExecute();
        } catch (RuntimeException e) {
            
            this.setVerboseResult("Action aborted early. " + e.getMessage());
        }

        return ActionSupport.SUCCESS;
    }
    
    private void handleExecute() throws Exception {

        // validate
        try {
            handleValidate();
        } catch (Exception e) {
            this
                    .setResult(AbstractAuthBasedAuthenticationService.FAILED_VALIDATION);
            throw e;
        }

        // prepare
        handlePrepare();

        // action
        try {
            handleAction();
        } catch (Exception e) {
            e.printStackTrace();
        }


        // deal with the result
        /*
         * if we don't have a result yet, we can assume SUCCESS
         */
        if (this.getResult() == null)
            this.setResult(AbstractAuthBasedAuthenticationService.SUCCESS);

    }
    
    protected abstract void handleActionTypeValidate() throws Exception;
    
    protected abstract void handleAction() throws Exception;
    
}

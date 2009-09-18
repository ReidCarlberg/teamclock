/*
 * Created on Sep 2, 2006
 *
 */
package com.fivesticks.time.contactv2.forms;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

import com.fivesticks.time.common.AbstractServiceDelegateException;
import com.fivesticks.time.common.AbstractSessionContextAwareServiceDelegate;
import com.fivesticks.time.common.util.RandomStringBuilder;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorException;

public class WebFormServiceDelegateImpl extends
        AbstractSessionContextAwareServiceDelegate implements
        WebFormServiceDelegate {

    private Random random = new Random(new Date().getTime());

    private static int MINIMUM_LENGTH = 40;

    private static int MAXIMUM_LENGTH = 50;
    
    public WebForm get(Long id) throws WebFormServiceDelegateException {

        WebForm ret = (WebForm) this.getDao().get(id);

        try {
            this.handleValidate(ret);
        } catch (AbstractServiceDelegateException e) {
            throw new WebFormServiceDelegateException(e);
        }

        return ret;
    }

    public Collection findAll() throws WebFormServiceDelegateException {
        return this.find(new WebFormCriteriaParameters());
    }
    public Collection find(WebFormCriteriaParameters params)
            throws WebFormServiceDelegateException {

        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(params,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new WebFormServiceDelegateException(e);
        }

        return this.getDao().find(params);

    }

    public WebForm get(String key) throws WebFormServiceDelegateException {

        WebFormCriteriaParameters params = new WebFormCriteriaParameters();
        params.setKey(key);

        Collection forms = this.find(params);

        if (forms.size() != 1) {
            throw new WebFormServiceDelegateException("couldn't find " + key);
        }

        WebForm ret = (WebForm) forms.toArray()[0];

        return ret;
    }

    public void save(WebForm webForm) throws WebFormServiceDelegateException {

        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(webForm,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new WebFormServiceDelegateException(e);
        }

        this.decorateDates(webForm);

        /*
         * 2006-09-03 handle the key issue
         */
        
        if (webForm.getId() == null) {
            
            String randomKey = generateUniqueKey();
            
            webForm.setKey(randomKey);
        }
        
        try {
            this.getDao().save(webForm);
        } catch (Exception e) {
            throw new WebFormServiceDelegateException("failed to save webform",
                    e);
        }
    }

    public void delete(WebForm webForm) throws WebFormServiceDelegateException {

        try {
            this.getOwnerKeyValidatorAndDecorator().validate(webForm,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new WebFormServiceDelegateException(e);
        }

        this.getDao().delete(webForm);

    }

    private String generateUniqueKey() throws WebFormServiceDelegateException {
        String ret = null;

        // try 15 times.
        for (int i = 0; i < 15; i++) {
            String temp = this.generateTrialKey();
            if (!isUsed(temp)) {
                ret = temp;
                break;
            }
        }

        if (ret == null)
            throw new WebFormServiceDelegateException("couldn't find unique key.");

        return ret;
    }
    
    private boolean isUsed(String key) {
        boolean ret = false;
        
        try {
            this.get(key);
            ret = true;
        } catch (WebFormServiceDelegateException e) {
            //no found
            ret = false;
        }
        
        
        return ret;
        
    }
    private String generateTrialKey() {

        int keyLength = MINIMUM_LENGTH
                + Math
                        .abs(random.nextInt()
                                % (MAXIMUM_LENGTH - MINIMUM_LENGTH));

        return new RandomStringBuilder().buildRandomString(keyLength);

    }
}

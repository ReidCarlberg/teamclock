/*
 * Created on May 11, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.boxes.xwork;

import java.util.Collection;

import com.fivesticks.time.common.delete.DeleteContext;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.CommonPrompts;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.ebay.setup.boxes.Box;
import com.fivesticks.time.ebay.setup.boxes.BoxDeleteCommand;
import com.fivesticks.time.ebay.setup.boxes.BoxServiceDelegate;

/**
 * @author Reid
 */
public class BoxModifyAction extends SessionContextAwareSupport implements
        BoxModifyContextAware {
    
    private BoxModifyContext boxModifyContext;
    
//    private Collection booleanSurrogate;
    
    private Box targetBox;
    
//    private String surrogateActive;
    
    private Long target;
    
    private String saveBox;
    
    private String deleteBox;
    
    private String cancelBox;
    
    private String copyBox;
    
    public String execute() throws Exception {
        
        if (this.getCancelBox() != null)
            return SUCCESS;
        
//        if (this.getDeleteBox() != null)
//            return ERROR;
        
        BoxServiceDelegate sd = BoxServiceDelegate.factory.build(this.getSystemOwner());
        
        if (this.getTarget() != null) {
            Box box = sd.getBox(this.getTarget());
            targetBox = box;
            this.getBoxModifyContext().setTarget(box);
            return INPUT;
        } else if (this.getCopyBox() != null) {
            this.setTargetBox(this.getBoxModifyContext().getTarget());
            this.getTargetBox().setName("COPY OF " + this.getTargetBox().getName());
            this.getTargetBox().setId(null);
            this.getBoxModifyContext().setTarget(null);
            return INPUT;
        } else if (this.getDeleteBox() != null) {
            BoxDeleteCommand cmd = new BoxDeleteCommand(this.getBoxModifyContext().getTarget());
            this.getSessionContext().setDeleteContext(
                    DeleteContext.factory.build(cmd, this
                            .getSessionContext().getSuccessOverride()));
            return GlobalForwardStatics.GLOBAL_COMMON_DELETE;            
        } else if (this.getSaveBox() == null) {
            this.getBoxModifyContext().setTarget(null);
            this.setTargetBox(new Box());
            this.getTargetBox().setActive(true);
            return INPUT;
        }
        
        validate();
        
        if (this.hasErrors()) {
            return INPUT;
        }
        
        if (this.getBoxModifyContext().getTarget() != null) {
            this.getTargetBox().setId(this.getBoxModifyContext().getTarget().getId());
        }
        
        sd.save(this.getTargetBox());
        
        return SUCCESS;
    }
    
    public void validate() {
        
        ValidationHelper helper = new ValidationHelper();
        
        if (!helper.isNonZero(this.getTargetBox().getLength())) {
            this.addFieldError("targetBox.length", "Length must be greater than 0.");
        }

        if (!helper.isNonZero(this.getTargetBox().getWidth())) {
            this.addFieldError("targetBox.width", "Width must be greater than 0.");
        }

        if (!helper.isNonZero(this.getTargetBox().getHeight())) {
            this.addFieldError("targetBox.height", "Height must be greater than 0.");
        }

        if (!helper.isNonZero(this.getTargetBox().getWeight())) {
            this.addFieldError("targetBox.weight", "Weight must be greater than 0.");
        }
        if (helper.isStringEmpty(this.getTargetBox().getName())) {
            this.addFieldError("targetBox.name", "Name must not be blank.");
        }
        
    }
    

    /**
     * @return Returns the boxModifyContext.
     */
    public BoxModifyContext getBoxModifyContext() {
        return boxModifyContext;
    }
    /**
     * @param boxModifyContext The boxModifyContext to set.
     */
    public void setBoxModifyContext(BoxModifyContext boxModifyContext) {
        this.boxModifyContext = boxModifyContext;
    }
    /**
     * @return Returns the target.
     */
    public Box getTargetBox() {
        return targetBox;
    }
    /**
     * @param target The target to set.
     */
    public void setTargetBox(Box target) {
        this.targetBox = target;
    }
    /**
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }
    /**
     * @param target The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }
    /**
     * @return Returns the cancelBox.
     */
    public String getCancelBox() {
        return cancelBox;
    }
    /**
     * @param cancelBox The cancelBox to set.
     */
    public void setCancelBox(String cancelBox) {
        this.cancelBox = cancelBox;
    }
    /**
     * @return Returns the deleteBox.
     */
    public String getDeleteBox() {
        return deleteBox;
    }
    /**
     * @param deleteBox The deleteBox to set.
     */
    public void setDeleteBox(String deleteBox) {
        this.deleteBox = deleteBox;
    }
    /**
     * @return Returns the saveBox.
     */
    public String getSaveBox() {
        return saveBox;
    }
    /**
     * @param saveBox The saveBox to set.
     */
    public void setSaveBox(String saveBox) {
        this.saveBox = saveBox;
    }
    /**
     * @return Returns the copyBox.
     */
    public String getCopyBox() {
        return copyBox;
    }
    /**
     * @param copyBox The copyBox to set.
     */
    public void setCopyBox(String copyBox) {
        this.copyBox = copyBox;
    }
    /**
     * @return Returns the booleanSurrogate.
     */
    public Collection getBooleanSurrogate() {
        return CommonPrompts.singleton.getBooleanSurrogates();
    }
//    /**
//     * @param booleanSurrogate The booleanSurrogate to set.
//     */
//    public void setBooleanSurrogate(Collection booleanSurrogate) {
//        this.booleanSurrogate = booleanSurrogate;
//    }
    /**
     * @return Returns the surrogateActive.
     */
    public String getSurrogateActive() {
        return "" + this.getTargetBox().isActive();
    }
    /**
     * @param surrogateActive The surrogateActive to set.
     */
    public void setSurrogateActive(String surrogateActive) {
        this.getTargetBox().setActive(new Boolean(surrogateActive).booleanValue());
    }
}

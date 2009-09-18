/*
 * Created on Mar 30, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.Settings;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.ebay.EbayItemImage;
import com.fivesticks.time.ebay.EbayItemImageServiceDelegate;
import com.fivesticks.util.images.ImageScaler;
import com.fivesticks.util.net.FtpHandler;
import com.opensymphony.xwork.ActionContext;

/**
 * @author REID
 */
public class EbayImageUploadAction extends SessionContextAwareSupport implements EbayItemModifyContextAware {

    
    private Log log = LogFactory.getLog(EbayImageUploadAction.class);
    
    private EbayItemModifyContext ebayItemModifyContext;
    
    private int imageCount = 0;

    protected File picture;
    protected File picture1;
    protected File picture2;
    protected File picture3;
    protected File picture4;
    protected File picture5;
    protected File picture6;
    protected File picture7;
    protected File picture8;
    protected File picture9;
    
    public String submitUpload;

    public void setPicture(File picture) {
        this.picture = picture;
        /*
         * commentss
         */
        log.info("this is for cruisecontrol");
    }

    /**
     */
    public String execute() throws Exception {

        picture = (File) ActionContext.getContext().getParameters().get(
                "picture");
        log.info("Starting execute");

        if (this.getSubmitUpload() == null) {
            log.info("Returning INPUT");
            return INPUT;
        }
        
        imageCount = this.getEbayItemModifyContext().getImages().size();
        
        handlePicture(this.getPicture());
        handlePicture(this.getPicture1());
        handlePicture(this.getPicture2());
        handlePicture(this.getPicture3());
        handlePicture(this.getPicture4());
        handlePicture(this.getPicture5());
        handlePicture(this.getPicture6());
        handlePicture(this.getPicture7());
        handlePicture(this.getPicture8());
        handlePicture(this.getPicture9());

        this.getEbayItemModifyContext().setImages(null);
        log.info("About to return success");
        return SUCCESS;
    }

    private void handlePicture(File localPicture) throws Exception {
        if (localPicture != null && localPicture.isFile()) {
            
            imageCount++;
            
            log.info("Have a picture.");
            final File target = new File(System
                    .getProperty(Settings.CONTEXT_PATH)
                    + "WEB-INF/tempimages/" + localPicture.getName());
            if (target.exists()) {

                target.delete();
            }
            log.info("moving the picture.");
            localPicture.renameTo(target);

            /*
             * resize
             */
            ImageScaler scaler = ImageScaler.factory.getJpegJai();

            String name600 = target.getName() + "600.jpg";
            String name100 = target.getName() + "100.jpg";

            log.info("Name600 " + target.getName());

            log.info("Absolute path " + target.getAbsolutePath());

            log.info("New path " + target.getParent() + "/" + name600);

            String file600 = scaler.scale(target.getAbsolutePath(), this
                    .getSessionContext().getSettings().getEbayImagesWidthFull(), target.getParent()
                    + "/" + name600);
            File full = new File(file600);
            log.info("Full: " + full.getAbsolutePath());
            

            log.info("600 is " + file600);

            String file100 = scaler.scale(target.getAbsolutePath(), this
                    .getSessionContext().getSettings().getEbayImagesWidthThumb(), target
                    .getParent()
                    + "/" + name100);
            log.info("100 is " + file100);
            File thumb = new File(file100);
            log.info("Thumb: " + thumb.getAbsolutePath());

            /*
             * move via FTP
             */

            FtpHandler ftp = new FtpHandler();

            ftp
                    .handleFtp(full.getAbsolutePath(),
                            this.getSessionContext().getSettings()
                            .getEbayImagesFtpServer(), this.getSessionContext().getSettings().getEbayImagesFtpUsername(), this.getSessionContext().getSettings().getEbayImagesFtpPassword(),
                            this.getSessionContext().getSettings().getEbayImagesFtpPath(), full.getName());

            ftp
                    .handleFtp(thumb.getAbsolutePath(),
                            this.getSessionContext().getSettings()
                            .getEbayImagesFtpServer(), this.getSessionContext().getSettings().getEbayImagesFtpUsername(), this.getSessionContext().getSettings().getEbayImagesFtpPassword(),
                            this.getSessionContext().getSettings().getEbayImagesFtpPath(), thumb.getName());

            /*
             * delete the files
             */
            full.delete();
            thumb.delete();
            target.delete();
            
            /*
             * add as an image file
             */
            EbayItemImage image = new EbayItemImage();
            image.setEbayId(this.getEbayItemModifyContext().getTarget().getId());
            image.setImageFull(full.getName());
            image.setImageSmall(thumb.getName());
            image.setSequence(new Integer(imageCount));
            EbayItemImageServiceDelegate.factory.build(this.getSessionContext().getSystemOwner()).save(image);
        }
        
    }
    public String getSubmitUpload() {
        return submitUpload;
    }

    public void setSubmitUpload(String submitUpload) {
        this.submitUpload = submitUpload;
    }
    /**
     * @return Returns the ebayItemModifyContext.
     */
    public EbayItemModifyContext getEbayItemModifyContext() {
        return ebayItemModifyContext;
    }
    /**
     * @param ebayItemModifyContext The ebayItemModifyContext to set.
     */
    public void setEbayItemModifyContext(
            EbayItemModifyContext ebayItemModifyContext) {
        this.ebayItemModifyContext = ebayItemModifyContext;
    }
    /**
     * @return Returns the log.
     */
    public Log getLog() {
        return log;
    }
    /**
     * @param log The log to set.
     */
    public void setLog(Log log) {
        this.log = log;
    }
    /**
     * @return Returns the picture1.
     */
    public File getPicture1() {
        return picture1;
    }
    /**
     * @param picture1 The picture1 to set.
     */
    public void setPicture1(File picture1) {
        this.picture1 = picture1;
    }
    /**
     * @return Returns the picture2.
     */
    public File getPicture2() {
        return picture2;
    }
    /**
     * @param picture2 The picture2 to set.
     */
    public void setPicture2(File picture2) {
        this.picture2 = picture2;
    }
    /**
     * @return Returns the picture3.
     */
    public File getPicture3() {
        return picture3;
    }
    /**
     * @param picture3 The picture3 to set.
     */
    public void setPicture3(File picture3) {
        this.picture3 = picture3;
    }
    /**
     * @return Returns the picture4.
     */
    public File getPicture4() {
        return picture4;
    }
    /**
     * @param picture4 The picture4 to set.
     */
    public void setPicture4(File picture4) {
        this.picture4 = picture4;
    }
    /**
     * @return Returns the picture5.
     */
    public File getPicture5() {
        return picture5;
    }
    /**
     * @param picture5 The picture5 to set.
     */
    public void setPicture5(File picture5) {
        this.picture5 = picture5;
    }
    /**
     * @return Returns the picture6.
     */
    public File getPicture6() {
        return picture6;
    }
    /**
     * @param picture6 The picture6 to set.
     */
    public void setPicture6(File picture6) {
        this.picture6 = picture6;
    }
    /**
     * @return Returns the picture7.
     */
    public File getPicture7() {
        return picture7;
    }
    /**
     * @param picture7 The picture7 to set.
     */
    public void setPicture7(File picture7) {
        this.picture7 = picture7;
    }
    /**
     * @return Returns the picture8.
     */
    public File getPicture8() {
        return picture8;
    }
    /**
     * @param picture8 The picture8 to set.
     */
    public void setPicture8(File picture8) {
        this.picture8 = picture8;
    }
    /**
     * @return Returns the picture9.
     */
    public File getPicture9() {
        return picture9;
    }
    /**
     * @param picture9 The picture9 to set.
     */
    public void setPicture9(File picture9) {
        this.picture9 = picture9;
    }
    /**
     * @return Returns the picture.
     */
    public File getPicture() {
        return picture;
    }
}
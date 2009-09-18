/*
 * Created on Mar 30, 2005 by Reid
 */
package com.fivesticks.util.net.legacy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * @author Reid
 */
public class FtpHandler {

    Log log = LogFactory.getLog(FtpHandler.class);
    
    private boolean successfulResponse;
    
    public void handleFtp(String localFileName, String server, String username,
            String pass, String dir, String remoteFileName) throws FtpHandlerException {

                
        FTPClient ftp = new FTPClient();
        
        

        try {
            handleOpen(ftp, server);
        } catch (Exception e) {
            log.error("Error during open.");
            throw new FtpHandlerException(e);
        }
        
        

        try {
            handleLogin(ftp, username, pass);
        } catch (Exception e1) {
            log.error("Error during loging.");
            throw new FtpHandlerException(e1);
        }
        
        

        try {
            handlePut(ftp, localFileName, dir, remoteFileName);
        } catch (Exception e2) {
            log.error("Error during put.");
            throw new FtpHandlerException(e2);
        }
        
        

        handleLogout(ftp);
        
        



    }

    /**
     * @param ftp
     * @param server
     */
    private void handleOpen(FTPClient ftp, String server) throws Exception {

        try {
            ftp.connect(server);
        } catch (Exception e) {
            if (ftp.isConnected())
                handleLogout(ftp);
            throw e;
        }

    }

    /**
     * @param ftp
     * @param username
     * @param pass
     */
    private void handleLogin(FTPClient ftp, String username, String pass) throws Exception {
        try {
            ftp.login(username, pass);
        } catch (IOException e) {
            handleLogout(ftp);
            throw e;
        }
    }

    /**
     * @param fileName
     * @param dir
     */
    private void handlePut(FTPClient ftp, String fileName, String dir, String remoteFileName) throws Exception {

        try {
            ftp.changeWorkingDirectory(dir);
            
//            ftp.set

            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            //ftp.remoteStore(fileName);
            
            InputStream local = new FileInputStream(new File(fileName));
            ftp.storeFile(remoteFileName, local);
            
            int reply = ftp.getReplyCode();
            
            this.setSuccessfulResponse(FTPReply.isPositiveCompletion(reply));
            
            System.out.println("rep " + reply);
            
            System.out.println("pwd " + ftp.printWorkingDirectory());
            
            local.close();
            
            
            

        } catch (IOException e) {
            handleLogout(ftp);
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * @param ftp
     */
    private void handleLogout(FTPClient ftp) {
        
        try {
            ftp.logout();
        } catch (IOException e) {
            
//            e.printStackTrace();
            log.error(e);
        }
        
        try {
            ftp.disconnect();
        } catch (IOException e1) {
            
//            e1.printStackTrace();
            log.error(e1);
        }
    }

    /**
     * @return Returns the successfulResponse.
     */
    public boolean isSuccessfulResponse() {
        return successfulResponse;
    }
    /**
     * @param successfulResponse The successfulResponse to set.
     */
    public void setSuccessfulResponse(boolean successfulResponse) {
        this.successfulResponse = successfulResponse;
    }
}
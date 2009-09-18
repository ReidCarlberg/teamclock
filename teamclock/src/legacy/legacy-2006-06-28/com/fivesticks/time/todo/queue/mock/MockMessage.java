/*
 * Created on Mar 29, 2005 by REID
 */
package com.fivesticks.time.todo.queue.mock;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;

/**
 * @author REID
 */
public class MockMessage extends Message {

    private String subject;
    private String content;
    private Date sentDate;
    private Date receivedDate;
//    private String contentType;
    private String disposition;
    private String description;
    
    /* (non-Javadoc)
     * @see javax.mail.Message#getFrom()
     */
    public Address[] getFrom() throws MessagingException {
        
        return null;
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#setFrom()
     */
    public void setFrom() throws MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#setFrom(javax.mail.Address)
     */
    public void setFrom(Address arg0) throws MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#addFrom(javax.mail.Address[])
     */
    public void addFrom(Address[] arg0) throws MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#getRecipients(javax.mail.Message.RecipientType)
     */
    public Address[] getRecipients(RecipientType arg0) throws MessagingException {
        
        return null;
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#setRecipients(javax.mail.Message.RecipientType, javax.mail.Address[])
     */
    public void setRecipients(RecipientType arg0, Address[] arg1) throws MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#addRecipients(javax.mail.Message.RecipientType, javax.mail.Address[])
     */
    public void addRecipients(RecipientType arg0, Address[] arg1) throws MessagingException {
    }





    /* (non-Javadoc)
     * @see javax.mail.Message#getFlags()
     */
    public Flags getFlags() throws MessagingException {
        
        return null;
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#setFlags(javax.mail.Flags, boolean)
     */
    public void setFlags(Flags arg0, boolean arg1) throws MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#reply(boolean)
     */
    public Message reply(boolean arg0) throws MessagingException {
        
        return null;
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#saveChanges()
     */
    public void saveChanges() throws MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#getSize()
     */
    public int getSize() throws MessagingException {
        
        return 0;
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#getLineCount()
     */
    public int getLineCount() throws MessagingException {
        
        return 0;
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#getContentType()
     */
    public String getContentType() throws MessagingException {
        
        return null;
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#isMimeType(java.lang.String)
     */
    public boolean isMimeType(String arg0) throws MessagingException {
        
        return false;
    }





    /* (non-Javadoc)
     * @see javax.mail.Message#getFileName()
     */
    public String getFileName() throws MessagingException {
        
        return null;
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#setFileName(java.lang.String)
     */
    public void setFileName(String arg0) throws MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#getInputStream()
     */
    public InputStream getInputStream() throws IOException, MessagingException {
        
        return null;
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#getDataHandler()
     */
    public DataHandler getDataHandler() throws MessagingException {
        
        return null;
    }



    /* (non-Javadoc)
     * @see javax.mail.Message#setDataHandler(javax.activation.DataHandler)
     */
    public void setDataHandler(DataHandler arg0) throws MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#setContent(java.lang.Object, java.lang.String)
     */
    public void setContent(Object arg0, String arg1) throws MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#setText(java.lang.String)
     */
    public void setText(String arg0) throws MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#setContent(javax.mail.Multipart)
     */
    public void setContent(Multipart arg0) throws MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#writeTo(java.io.OutputStream)
     */
    public void writeTo(OutputStream arg0) throws IOException, MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#getHeader(java.lang.String)
     */
    public String[] getHeader(String arg0) throws MessagingException {
        
        return null;
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#setHeader(java.lang.String, java.lang.String)
     */
    public void setHeader(String arg0, String arg1) throws MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#addHeader(java.lang.String, java.lang.String)
     */
    public void addHeader(String arg0, String arg1) throws MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#removeHeader(java.lang.String)
     */
    public void removeHeader(String arg0) throws MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#getAllHeaders()
     */
    public Enumeration getAllHeaders() throws MessagingException {
        
        return null;
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#getMatchingHeaders(java.lang.String[])
     */
    public Enumeration getMatchingHeaders(String[] arg0) throws MessagingException {
        
        return null;
    }

    /* (non-Javadoc)
     * @see javax.mail.Message#getNonMatchingHeaders(java.lang.String[])
     */
    public Enumeration getNonMatchingHeaders(String[] arg0) throws MessagingException {
        
        return null;
    }

    public Object getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDisposition() {
        return disposition;
    }
    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }
    public Date getReceivedDate() {
        return receivedDate;
    }
    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }
    public Date getSentDate() {
        return sentDate;
    }
    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
//    public void setContentType(String contentType) {
//        this.contentType = contentType;
//    }
}

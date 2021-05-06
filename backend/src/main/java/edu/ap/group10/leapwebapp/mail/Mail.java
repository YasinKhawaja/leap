package edu.ap.group10.leapwebapp.mail;

import java.util.Date;
import java.util.List;

public class Mail {

    private String sender;
    private String receiver;
    private String cc;
    private String bcc;
    private String subject;
    private String content;
    private String contentType;
    private List<Object> attachments;

    public Mail(){
        contentType = "text/plain";
    }
    
    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getCc() {
        return this.cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return this.bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public List <Object> getAttachments()
    {
		return attachments;
	}

    public void setAttachments(List<Object> attachments)
    {
		this.attachments = attachments;
	}

    public Date getMailSendDate() {
        return new Date();
    }
}

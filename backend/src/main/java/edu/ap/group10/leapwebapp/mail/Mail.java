package edu.ap.group10.leapwebapp.mail;

import java.util.List;

import lombok.Data;

@Data
public class Mail {

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
}

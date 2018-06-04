/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class goEmail {

    public static void send(String to, String sub,String msg) {
        
        String user = "dontenedorrestaurant@gmail.com";
        String pass = "donTenedoremail";
        
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            
            String structureEmail="";
            structureEmail +="<div>";
            structureEmail +="<div style='display:inline-block;'>"+msg+"</div>";
            structureEmail +="</div>";
            
            message.setText(structureEmail);
            message.setContent(structureEmail, "text/html; charset=utf-8");
            /* Transport class is used to deliver the message to the recipients */
            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

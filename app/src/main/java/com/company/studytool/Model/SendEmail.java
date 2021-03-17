package com.company.studytool.Model;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SendEmail {
    Random rand = new Random();

    int randomCode = rand.nextInt(999999);

    void send(String sendTo) throws MessagingException {

    String Host = "smtp.gmail.com";
        String User = "confirmstudytool@gmail.com";
        String pass = "study_tool10";
      /*  String User = "nursingHomeManagement@gmail.com";
        String pass = "nursingHome10";
        */    String Subject = "Confirmation code";
    String message = "Your confirmation code is " + randomCode;
    boolean sessionDebug = false;

  Properties props = System.getProperties();

       props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", Host);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.user", User);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.socketFactory.port" , "587");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");


        //Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

    /*    Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");

        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
*/
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("username",
                                "password");
                    }
                });

        session.setDebug(true);


        Transport transport = session.getTransport();
        InternetAddress addressFrom = new InternetAddress(User);

        MimeMessage msg = new MimeMessage(session);
        msg.setSender(addressFrom);
        msg.setSubject(Subject);
        msg.setText(message);
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
                sendTo));

        transport.connect();
        Transport.send(msg);
        transport.close();
    }
    }








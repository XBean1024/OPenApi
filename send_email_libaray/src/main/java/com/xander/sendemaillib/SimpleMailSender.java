package com.xander.sendemaillib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/** 简单邮件（不带附件的邮件）发送器 */
public class SimpleMailSender {
  private MyAuthenticator authenticator = null;
  private Properties pro = null;

  public void initMailPro(MailSenderInfo mailSenderInfo) {
    pro = mailSenderInfo.getProperties();
    // 设置property
    pro.put("username", mailSenderInfo.getUserName());
    int timeout = 25000;
    pro.setProperty("mail.smtp.timeout", timeout + "");
    pro.setProperty("mail.smtp.auth", "true");

    if (mailSenderInfo.isValidate()) {
      // 如果需要身份认证，则创建一个密码验证器
      authenticator =
          new MyAuthenticator(mailSenderInfo.getUserName(), mailSenderInfo.getPassword());
    }

    // 根据邮件会话属性和密码验证器构造一个发送邮件的session
    Session sendMailSession = Session.getDefaultInstance(pro, authenticator);

    // 根据session创建一个邮件消息
    Message mailMessage = new MimeMessage(sendMailSession);

    try {
      Address from = new InternetAddress(mailSenderInfo.getFromAddress());
      mailMessage.setFrom(from);
      String usr = pro.getProperty("username");
//      mailMessage.addRecipient(MimeMessage.RecipientType.CC, new InternetAddress(usr));

      // 创建邮件的接收者地址，并设置到邮件消息中

      List<String> list = mailSenderInfo.getAddrs();
      int count = list.size();
      if (count < 1) {
        return;
      }
      Address[] to = new InternetAddress[count];
      for (int i = 0; i < count; i++) {
        to[i] = new InternetAddress(list.get(i));
      }
      //      mailMessage.setRecipient(Message.RecipientType.TO, to);
      mailMessage.setRecipients(Message.RecipientType.TO, to);
      // 设置邮件消息的主题
      mailMessage.setSubject(mailSenderInfo.getSubject());
      // 设置邮件消息发送的时间
      mailMessage.setSentDate(new Date());
      // 设置邮件消息的主要内容
      String mailContent = mailSenderInfo.getContent();
      mailMessage.setText(mailContent);
      // 发送邮件
      Transport.send(mailMessage);
    } catch (AddressException e) {
      e.printStackTrace();
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
  /**
   * 以文本格式发送邮件
   *
   * @param mailInfo 待发送的邮件的信息
   */
  public boolean sendTextMail(MailSenderInfo mailInfo) {
    // 判断是否需要身份认证
    MyAuthenticator authenticator = null;
    Properties pro = mailInfo.getProperties();
    pro.setProperty("username", mailInfo.getUserName());
    if (mailInfo.isValidate()) {
      // 如果需要身份认证，则创建一个密码验证器
      authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
    }
    // 根据邮件会话属性和密码验证器构造一个发送邮件的session
    Session sendMailSession = Session.getDefaultInstance(pro, authenticator);

    // 根据session创建一个邮件消息
    Message mailMessage = new MimeMessage(sendMailSession);
    try {
      // 创建邮件发送者地址
      Address from = new InternetAddress(mailInfo.getFromAddress());
      // 设置邮件消息的发送者
      mailMessage.setFrom(from);

      String usr = pro.getProperty("username");
      mailMessage.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(usr));
      // 创建邮件的接收者地址，并设置到邮件消息中
      Address to = new InternetAddress(mailInfo.getToAddress());
      mailMessage.setRecipient(Message.RecipientType.TO, to);
      // 设置邮件消息的主题
      mailMessage.setSubject(mailInfo.getSubject());
      // 设置邮件消息发送的时间
      mailMessage.setSentDate(new Date());
      // 设置邮件消息的主要内容
      String mailContent = mailInfo.getContent();
      mailMessage.setText(mailContent);
      // 发送邮件
      Transport.send(mailMessage);
      return true;
    } catch (MessagingException ex) {
      ex.printStackTrace();
    }
    return false;
  }

  /**
   * 以HTML格式发送邮件
   *
   * @param mailInfo 待发送的邮件信息
   */
  public static boolean sendHtmlMail(MailSenderInfo mailInfo) {
    // 判断是否需要身份认证
    MyAuthenticator authenticator = null;
    Properties pro = mailInfo.getProperties();
    // 如果需要身份认证，则创建一个密码验证器
    if (mailInfo.isValidate()) {
      authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
    }
    // 根据邮件会话属性和密码验证器构造一个发送邮件的session
    Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
    try {
      // 根据session创建一个邮件消息
      Message mailMessage = new MimeMessage(sendMailSession);
      // 创建邮件发送者地址
      Address from = new InternetAddress(mailInfo.getFromAddress());
      // 设置邮件消息的发送者
      mailMessage.setFrom(from);
      // 创建邮件的接收者地址，并设置到邮件消息中
      Address to = new InternetAddress(mailInfo.getToAddress());
      // Message.RecipientType.TO属性表示接收者的类型为TO
      mailMessage.setRecipient(Message.RecipientType.TO, to);
      // 设置邮件消息的主题
      mailMessage.setSubject(mailInfo.getSubject());
      // 设置邮件消息发送的时间
      mailMessage.setSentDate(new Date());
      // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
      Multipart mainPart = new MimeMultipart();
      // 创建一个包含HTML内容的MimeBodyPart
      BodyPart html = new MimeBodyPart();
      // 设置HTML内容
      html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
      mainPart.addBodyPart(html);
      // 将MiniMultipart对象设置为邮件内容
      mailMessage.setContent(mainPart);
      // 发送邮件
      Transport.send(mailMessage);
      return true;
    } catch (MessagingException ex) {
      ex.printStackTrace();
    }
    return false;
  }
}

package com.xander.sendemaillib;

import android.util.Log;

import java.util.List;

/*
 * 创建时间：${date} ${time}
 * @author: ouyangmuyuan
 *  用途：发送邮件
 */
public class SendEmail {

  /**
   * @param toAddress 可以接受一组邮箱
   * @param emailTitle
   * @param emailContent
   */
  public static void send(List<String>toAddress, String emailTitle, String emailContent) {
    // 这个类主要是设置邮件
    MailSenderInfo mailInfo = new MailSenderInfo();
    mailInfo.setMailServerHost("smtp.163.com");
    mailInfo.setMailServerPort("25");
    mailInfo.setValidate(true);
    mailInfo.setFromAddress("xu596928539@163.com");
    mailInfo.setUserName("xu596928539@163.com"); // 用户名
    mailInfo.setPassword("liux1228"); // 邮箱密码
    mailInfo.setSubject(emailTitle);
    mailInfo.setContent(emailContent);
    // 这个类主要来发送邮件
    SimpleMailSender sms = new SimpleMailSender();
    mailInfo.setToAddress(toAddress);
    sms.initMailPro(mailInfo);

  }
}

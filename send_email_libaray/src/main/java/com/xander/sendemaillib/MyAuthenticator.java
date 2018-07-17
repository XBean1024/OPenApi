package com.xander.sendemaillib;

import javax.mail.*;
/*
* 创建时间：${date} ${time}
* @author: ouyangmuyuan
*  用途：
*/
  
public class MyAuthenticator extends Authenticator{
	String userName=null;
	String password=null;
	public MyAuthenticator(String username, String password) {
		this.userName = username; 
		this.password = password; 
	} 
	protected PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(userName, password);
	}
}
 


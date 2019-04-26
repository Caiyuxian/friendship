package com.friendship.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
*邮件发送类 
 */
public class SentEmail {
	/**
	 * 邮件发送类，需要用户传入收件人地址和邮件内容
	 * @param emailto	收件人地址
	 * @param content	邮件内容
	 */
	public static boolean sendEmail(String emailto,String content){
		String smtphost = "smtp.163.com";		//发送的邮箱服务器
		String user = "";			//邮件服务器登录名
		String password = "";	//邮件服务器密码
		String biaoti ="联谊平台系统通知";			//邮件标题
		String from ="caiyuxian94@163.com";		//发件者地址
			
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", smtphost);
			props.put("mail.smtp.auth", "true");	//设置权限验证
			
			Session session = Session.getInstance(props, null);
			//获取邮件主体对象
			MimeMessage message = new MimeMessage(session); 
			//设置邮件发送人地址
			InternetAddress fromAddress = new InternetAddress(from);
			message.setFrom(fromAddress);
			//设置邮件收件人地址
			InternetAddress toAddress  = new InternetAddress(emailto);
			message.setRecipient(Message.RecipientType.TO, toAddress);
			//设置邮件标题
			message.setSubject(biaoti);		
			
			// MiniMultipart类是一个容器类，包含MimeBodyPar	类型的对象
			Multipart mainPart = new MimeMultipart(); 
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart(); 

			// 设置HTML内容
			html.setContent(content, "text/html;charset=utf-8");  
			mainPart.addBodyPart(html); 
			// 	将MiniMultipart对象设置为邮件内容
			message.setContent(mainPart); 
			
			//设置邮件发送的时间
			message.setSentDate(new Date());    
			//获取邮件服务器
			Transport transport = session.getTransport("smtp");
			//连接服务器，传入用户名密码
			transport.connect(smtphost, user, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

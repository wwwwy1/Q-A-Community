package cn.Ideal.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtil {
	private static String fromMail = "usx16147220@163.com";
	@Autowired
	private JavaMailSender tMailSender;
	private static JavaMailSender mailSender;
	@PostConstruct
	public void init(){
		mailSender = tMailSender;
	}
	public static void sendMail(String toMail,String title,String body,String mailAttachment) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		//发件人
		helper.setFrom(fromMail);
		//收件人
		helper.setTo(toMail);
		//标题
		helper.setSubject(title);
		//文本
		helper.setText(body);
		//附件
		helper.addAttachment("downFile",new File(mailAttachment));
		mailSender.send(mimeMessage);
	}

	public static void sendMail(String toMail,String title,String body) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		//发件人
		helper.setFrom(fromMail);
		//收件人
		helper.setTo(toMail);
		//标题
		helper.setSubject(title);
		//文本
		helper.setText(body);
		//附件
		//helper.addAttachment("downFile",new File(mailAttachment));
		mailSender.send(mimeMessage);
	}
}

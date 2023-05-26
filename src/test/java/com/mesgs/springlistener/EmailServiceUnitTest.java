package com.mesgs.springlistener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.mesgs.springlistener.entity.EmailDetails;
import com.mesgs.springlistener.serviceImpl.EmailServiceImpl;

@ExtendWith(MockitoExtension.class)
class EmailServiceUnitTest {

	@InjectMocks
	EmailServiceImpl emailServiceImpl;
	
	@Mock
	JavaMailSender javaMailSender;
	
	@Test
	void sendSimpleEmailTest() {
		String message = "Hi";
		assertEquals("Mail Sent Successfully...", 
			emailServiceImpl.sendSimpleMail(new EmailDetails("snehamayees@kpmg.com",message,"Message Notification from Azure Service Bus")));
	}
	
	@Test
	void sendSimpleEmailTestWithException() {
		String message = "Hi";
		JavaMailSender mailSender = mock(JavaMailSender.class);
		SimpleMailMessage mailMessage = mock(SimpleMailMessage.class);
		doThrow(MailException.class).when(mailSender).send(mailMessage);
		assertThrows(MailException.class, () -> {
			emailServiceImpl.sendSimpleMail(new EmailDetails("snehamayees@kpmg.com",message,"Message Notification from Azure Service Bus"));
		});
	}
	
}

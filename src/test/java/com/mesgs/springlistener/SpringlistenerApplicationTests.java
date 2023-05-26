package com.mesgs.springlistener;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import com.mesgs.springlistener.controller.ListenerController;
import com.mesgs.springlistener.entity.EmailDetails;
import com.mesgs.springlistener.repository.MessagingRepo;
import com.mesgs.springlistener.service.EmailService;
import com.mesgs.springlistener.serviceImpl.EmailServiceImpl;

@ExtendWith(MockitoExtension.class)
class SpringlistenerApplicationTests {

	@InjectMocks
	ListenerController controller;
	
	@Mock
	MessagingRepo messagingRepo;
	
	@Mock
	EmailService emailService;
	
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
	void receiveMessageTest() {
		controller.receiveMessage("Hello world");
	}
	
	
}

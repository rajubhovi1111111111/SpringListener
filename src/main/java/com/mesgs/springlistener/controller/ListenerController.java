package com.mesgs.springlistener.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mesgs.springlistener.entity.EmailDetails;
import com.mesgs.springlistener.entity.Messages;
import com.mesgs.springlistener.repository.MessagingRepo;
import com.mesgs.springlistener.service.EmailService;


@RestController
public class ListenerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ListenerController.class);
	private static final String QUEUE_NAME = "spring-queue";

	@Autowired
	private MessagingRepo messageRepo;
	
	@Autowired
	private EmailService emailService;
	
	@JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
	public void receiveMessage(String message) {
		LOGGER.info("Message received:: {}", message);
		
		messageRepo.save(new Messages(message));
		LOGGER.info("Message stored to DB:: {}", message);
		
		emailService.sendSimpleMail(new EmailDetails("snehamayees@kpmg.com",message,"Message Notification from Azure Service Bus")); 
		LOGGER.info("Email Sent Successfully!!!");
	}
	
	
	@GetMapping("/allDataFromDb")
	public List<Messages> fetchAllDatafromDB(){
		return messageRepo.findAll();
	}
}

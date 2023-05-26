package com.mesgs.springlistener;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.mesgs.springlistener.controller.ListenerController;
import com.mesgs.springlistener.repository.MessagingRepo;
import com.mesgs.springlistener.service.EmailService;

@ExtendWith(MockitoExtension.class)
class SpringlistenerApplicationTests {

	@InjectMocks
	ListenerController controller;
	
	@Mock
	MessagingRepo messagingRepo;
	
	@Mock
	EmailService emailService;
	
	@Test
	void receiveMessageTest() {
		controller.receiveMessage("Hello world");
	}
	
	
}

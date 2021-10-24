package com.example.demo;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class QueueReceiveController {
    

    private static final String QUEUE_NAME = "gopi-queue";
    
    private final Logger logger = LoggerFactory.getLogger(QueueReceiveController.class);

    @JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(User user) throws URISyntaxException {
        logger.info("Received message: {}", user.getName());
        sendMessage(user.getName());
    }
  
    public void sendMessage(String message) throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();
	     
	    final String url = "http://sendmessagetoazure-git-akchakraborty7-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/sendmessagetoazure";
	    URI uri = new URI(url);
	     
	    Message azuremesssage = new Message();
	 
	    restTemplate.postForEntity(uri, azuremesssage, String.class);
	    
	    System.out.println("Sent a message to IBM MQ: " );        
	}

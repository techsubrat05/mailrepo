package com.sandvik.mailapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailResult;
import com.sandvik.mailapp.dto.MessageInfo;

@Service
public class MailService {
	
	@Value("${accessKey}")
	private String accessKey;

	@Value("${secretKey}")
	private String secretKey;

	@Value("${region}")
	private String region;



	public String sendEmail(MessageInfo messageInfo) {

		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder
				.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();

		Destination destination = new Destination();
		List<String> toAddresses = new ArrayList<String>();
		String[] Emails = messageInfo.getTo();

		for (String email : Emails) {
			toAddresses.add(email);
		}

		destination.setToAddresses(toAddresses);
		SendTemplatedEmailRequest templatedEmailRequest = new SendTemplatedEmailRequest();
		templatedEmailRequest.withDestination(destination);
		templatedEmailRequest.withTemplate(messageInfo.getTemplateName());
		templatedEmailRequest.withTemplateData(messageInfo.getTemplateData());
		templatedEmailRequest.withSource(messageInfo.from);
		SendTemplatedEmailResult data = client.sendTemplatedEmail(templatedEmailRequest);
		
		return data.getMessageId();
	}

}

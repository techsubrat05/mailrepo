package com.sandvik.mailapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandvik.mailapp.dto.MessageInfo;
import com.sandvik.mailapp.service.MailService;

@RestController
@RequestMapping("/api")
public class MailController {
	
	@Autowired
	private MailService mailService;

	@PostMapping(value = "/email")
	public String sendEmail(@RequestBody MessageInfo messageInfo) {
		return mailService.sendEmail(messageInfo);
	}

}

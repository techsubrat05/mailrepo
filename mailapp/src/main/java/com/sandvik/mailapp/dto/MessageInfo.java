package com.sandvik.mailapp.dto;

import lombok.Data;

@Data
public class MessageInfo {

	public String from;
	public String[] to;
	private String templateName;
	private String templateData;

}

package org.sunil.javarest.messanger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	private String erroMessage;
	private int errorCode;
	private String documentation;
	
	public ErrorMessage(){
		
	}
	
	
	public ErrorMessage(String erroMessage, int errorCode, String documentation) {
		super();
		this.erroMessage = erroMessage;
		this.errorCode = errorCode;
		this.documentation = documentation;
	}


	public String getErroMessage() {
		return erroMessage;
	}
	public void setErroMessage(String erroMessage) {
		this.erroMessage = erroMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	
}

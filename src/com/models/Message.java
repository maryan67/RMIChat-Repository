package com.models;

import java.io.Serializable;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User recipient;
	private User receiver;
	private String message;

	public Message(User recipient, User receiver, String message){
		super();
		this.recipient = recipient;
		this.receiver = receiver;
		this.message = message;
	}

	public User getRecipient() {
		return recipient;
	}

	public String getMessage() {
		return message;
	}

	public User getReceiver() {
		return receiver;
	}

}

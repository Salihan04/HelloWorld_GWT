package com.han.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.han.client.Message;
import com.han.client.MessageService;

public class MessageServiceImpl extends RemoteServiceServlet implements MessageService {

	@Override
	public Message getMessage(String input) {
		String messageString = "Hello " + input + "!";
		Message message = new Message();
		message.setMessage(messageString);
		
		return message;
	}
	
}
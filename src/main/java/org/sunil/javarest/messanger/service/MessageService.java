package org.sunil.javarest.messanger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.sunil.javarest.messanger.database.DatabaseClass;
import org.sunil.javarest.messanger.exception.DataNotFoundException;
import org.sunil.javarest.messanger.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService(){
		
		messages.put(1L, new Message(1L,"hello world1","sunil"));
		messages.put(2L, new Message(2L,"hello world2","sunil2"));
		
	}
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	
	public List<Message> getAllMessageforYear(int year){
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year){
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
		
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size){
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if(start+size > list.size()) return new ArrayList<Message>();
		return list.subList(start, start+size);
	}
	
	
	
	
	
	public Message getMessage(long id){
		Message message = messages.get(id);
		if(message == null){
			throw new DataNotFoundException("message with id" + id + "not found");
		}
		return message;
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId()<= 0){
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(Long id){
		return messages.remove(id);
	}
}

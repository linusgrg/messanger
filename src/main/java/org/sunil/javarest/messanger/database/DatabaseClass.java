package org.sunil.javarest.messanger.database;

import java.util.HashMap;
import java.util.Map;

import org.sunil.javarest.messanger.model.Comment;
import org.sunil.javarest.messanger.model.Message;
import org.sunil.javarest.messanger.model.Profile;

public class DatabaseClass {
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();
	
	private static Map<String, Comment> comments = new HashMap<>();
	public static Map<Long,Message> getMessages(){
		return messages;
	}
	
	public static Map<String, Profile> getProfiles(){
		return profiles;
		
	}
	
	public static Map<String, Comment> getComments(){
		return comments;
	}
}

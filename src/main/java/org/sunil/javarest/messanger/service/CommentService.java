package org.sunil.javarest.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sunil.javarest.messanger.database.DatabaseClass;
import org.sunil.javarest.messanger.model.Comment;
import org.sunil.javarest.messanger.model.Message;

public class CommentService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	public List<Comment> getAllComments(Long messageId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
		
	}
	
	public Comment getComment(Long messageId, Long commentId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.get(commentId);
		
	}
	
	public Comment addComment(Long messageId, Comment comment){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(Long messageId, Comment comment){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if(comment.getId()<=0){
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(Long messageId, Long commentId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}

}

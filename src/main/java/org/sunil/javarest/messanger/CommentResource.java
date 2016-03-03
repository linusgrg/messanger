package org.sunil.javarest.messanger;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sunil.javarest.messanger.model.Comment;
import org.sunil.javarest.messanger.service.CommentService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	private CommentService commentService = new CommentService();
	
	@GET
	public List<Comment> getAllComments(@PathParam ("messageId") Long messageId){
		return commentService.getAllComments(messageId);
	}

	@POST
	public Comment addMessage(@PathParam("messageId") Long messageId, Comment comment){
		return commentService.addComment(messageId, comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateMessage(@PathParam("messageId") Long messageId, @PathParam("commentId") Long id, Comment comment){
		comment.setId(id);
		return commentService.updateComment(messageId, comment);	
	}
	
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("messaggeId") Long messageId, @PathParam("commentId") Long commentId){
		commentService.removeComment(messageId, commentId);
	}
	@GET
	@Path("/{commentId}")
	public Comment getMessage(@PathParam("messageId") Long messageId, @PathParam("commentId") Long commentId){
		return commentService.getComment(messageId, commentId);
	}
}

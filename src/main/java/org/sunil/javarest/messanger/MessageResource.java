package org.sunil.javarest.messanger;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.sunil.javarest.messanger.model.Message;
import org.sunil.javarest.messanger.service.MessageService;

@Path("messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessage(/*@QueryParam("year") int year,
									@QueryParam("start") int start,
									@QueryParam("size") int size*/){
		/*if(year > 0){
			return messageService.getAllMessageforYear(year);
		}
		
		if(start>=0 && size>=0){
			return messageService.getAllMessagesPaginated(start, size);
		}*/
		
		
		return messageService.getAllMessages();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message){
		return messageService.addMessage(message);
	}
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessageById(@PathParam("messageId") Long messageId,@Context UriInfo uriInfo ){
		Message message = messageService.getMessage(messageId);
		
		String uri = uriInfo(uriInfo, message);
		
		String uriProfile = uriProfileLink(uriInfo, message);
		
		String uriComment = uriCommentLink(uriInfo, message);
		message.addLink(uri,"self");
		message.addLink(uriProfile,"profile");
		message.addLink(uriComment,"comment");
		
		return message;
	}
	//url for the  messages self
	private String uriInfo(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
		.path(MessageResource.class)
		.path(Long.toString(message.getId()))
		.build()
		.toString();
		return uri;
	}
	//url for the profile
	private String uriProfileLink(UriInfo uriInfo, Message message){
		URI uri = uriInfo.getBaseUriBuilder()
		.path(ProfileResource.class)
		.path(message.getAuthor())
		.build();
		return uri.toString();
	}
	
	//url for the comment
	
	private String uriCommentLink(UriInfo uriInfo, Message message){
		URI uri = uriInfo.getBaseUriBuilder()
		.path(MessageResource.class)
		.path(MessageResource.class, "getCommentResource")
		.path(CommentResource.class)
		.resolveTemplate("messageId", message.getId())
		.build();
		
		return uri.toString();
		
	}
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") Long messageId, Message message){
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") Long messageId){
		messageService.removeMessage(messageId);
	}
	

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
}

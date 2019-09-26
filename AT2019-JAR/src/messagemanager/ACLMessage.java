package messagemanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agentmanager.AID;

public class ACLMessage implements Serializable {
	
	private static final long serialVersionUID = 9089809346282605606L;

	public Performative performative;

	public AID sender;
	
	public AID reciever;

	public List<AID> receivers;

	public AID replyTo;

	public String content;

	public Object contentObj;

	public Map<String, Serializable> userArgs;
	
	public String language;

	public String encoding;

	public String ontology;

	public String protocol;
	
	public String conversationId;
	
	public String replyWith;
	
	public String inReplyTo;
	
	public long replyBy;
	
	public ACLMessage() {
		
	}

	public ACLMessage(Performative performative) {
		this.performative = performative;
		receivers = new ArrayList<>();
		userArgs = new HashMap<>();
	}
	
	public ACLMessage(AID sender, AID reciever, Performative performative, String content) {
		this.sender = sender;
		this.reciever = reciever;
		this.performative = performative;
		this.content = content;
		this.receivers = new ArrayList<>();
	}

	public boolean canReplyTo() {
		return sender != null || replyTo != null;
	}

	public ACLMessage makeReply(Performative performative) {
		if (!canReplyTo()) {
			throw new IllegalArgumentException("There's no-one to receive the reply.");
		}

		ACLMessage reply = new ACLMessage(performative);
	
		reply.receivers.add(replyTo != null ? replyTo : sender);
		reply.language = language;
		reply.ontology = ontology;
		reply.encoding = encoding;
		reply.protocol = protocol;
		reply.conversationId = conversationId;
		reply.inReplyTo = replyWith;
		return reply;
	}
}
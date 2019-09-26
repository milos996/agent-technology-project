package agentmanager;

import java.io.Serializable;

import agents.AgentPing;
import agents.AgentPong;
import agents.ContractNetInitiator;
import agents.ContractNetParticipant;

public class AgentType implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String module;
	
	public AgentType() {}
	
	public AgentType(String name, String module) {
		this.name = name;
		this.module = module;
	}

	public String getName() {
		return name;
	}

	public String getModule() {
		return module;
	}
	
	public static AgentType build(String type) {
		
		if (type.equals(AgentTypes.AGENT_PING.toString())) {
			return new AgentType(AgentPing.class.getSimpleName(), "AgentPing");
		}
		
		if (type.equals(AgentTypes.AGENT_PONG.toString())) {
			return new AgentType(AgentPong.class.getSimpleName(), "AgentPong");
		}
		
		if (type.equals(AgentTypes.CONTRACT_NET_INITIATOR.toString())) {
			return new AgentType(ContractNetInitiator.class.getSimpleName(), "ContractNetInitiator");
		}
		
		if (type.equals(AgentTypes.CONTRACT_NET_PARTICIPANT.toString())) {
			return new AgentType(ContractNetParticipant.class.getSimpleName(), "ContractNetParticipant");
		}
		
		return new AgentType("", "");
	}
}

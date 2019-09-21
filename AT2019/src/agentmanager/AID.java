package agentmanager;

import java.io.Serializable;

import connectionmanager.AgentCenter;

public final class AID implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String name;
	private final AgentCenter host;
	private final AgentType type; 
	
	public AID(String name, AgentType type, AgentCenter host) {
		this.name = name;
		this.host = host;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public AgentCenter getHost() {
		return host;
	}

	public AgentType getType() {
		return type;
	}

	public String getHostName() {
		return name + "@" + host.getAlias();
	}
}

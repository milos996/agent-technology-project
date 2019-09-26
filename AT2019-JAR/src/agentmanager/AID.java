package agentmanager;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import connectionmanager.AgentCenter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public  class AID implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("name")
	private  String name;
	@JsonProperty("host")
	private  AgentCenter host;
	@JsonProperty("type")
	private  AgentType type;
	
	
	
	public AID() {
	}

	public AID(String name, AgentType type, AgentCenter host) {
		this.name = name;
		this.host = host;
		this.type = type;
	}
	
	public AID(AID aid) {
		this.name = aid.name;
		this.host = aid.host;
		this.type = aid.type;
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
		if (host == null) {
			return "localhost";
		}
		
		return name + "@" + host.getAlias();
	}
	
	
}

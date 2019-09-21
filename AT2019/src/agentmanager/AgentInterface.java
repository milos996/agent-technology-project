package agentmanager;

import java.io.Serializable;

import messagemanager.ACLMessage;

public interface AgentInterface extends Serializable {
	
	void handleMessage(ACLMessage msg);
}

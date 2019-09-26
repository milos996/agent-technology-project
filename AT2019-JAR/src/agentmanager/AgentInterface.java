package agentmanager;

import java.io.Serializable;

import messagemanager.ACLMessage;
import messagemanager.MessageManager;

public interface AgentInterface extends Serializable {
	
	void handleMessage(ACLMessage aclMessage);
	
	void init(AID aid);
	
	MessageManager messageManager();
	
	AID getAid();
}

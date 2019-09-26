package agents;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import agentmanager.AID;
import agentmanager.Agent;
import agentmanager.AgentInterface;
import messagemanager.ACLMessage;
import messagemanager.Performative;

@Stateful
public class AgentPing extends Agent {
	
	private static final long serialVersionUID = 1L;
//
//	@EJB
//	WebSocketClient webSocketClient;
	
	@Override
	public void handleMessage(ACLMessage aclMessage) {
		if (aclMessage.performative == Performative.REQUEST) { 
			messageManager().post(aclMessage);
			return;
		} 
		
		if (aclMessage.performative == Performative.INFORM) {
			System.out.println(aclMessage.content);
			return;
		}
	}

	@Override
	public void init(AID aid) {
		System.out.println("Tu sam");
//		webSocketClient.sendMessageToClient("Kreirao se agent");
	}
	
	
}

package agents;

import javax.ejb.Stateful;

import agentmanager.Agent;
import messagemanager.ACLMessage;
import messagemanager.Performative;

@Stateful
public class AgentPong extends Agent {

	private static final long serialVersionUID = -8913458083105208623L;
	
	private int count = 0;
	//  @EJB
	//	WebSocketClient webSocketClient;
	
	
	@Override
	public void handleMessage(ACLMessage aclMessage) {
		if (aclMessage.performative == Performative.REQUEST) {
			count = count + 1;
			// webSocketClient.sendMessageToClient("GET REQUEST performative from agent " + aclMessage.sender.getName() + "in" + aclMessage.reciever.getName());
			ACLMessage responseMessage = new ACLMessage(Performative.INFORM);
			responseMessage.reciever = aclMessage.sender;
			responseMessage.sender = this.myAid ;
			responseMessage.content = "Primljen request, saljem nazad inform";
			messageManager().post(responseMessage);
		}
	}

}

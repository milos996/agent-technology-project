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

//	  @EJB(mappedName = "java:global/AT2019-EAR/AT2019/WebSocketClient")
//	  WebSocketClient webSocketClient;
	
	@Override
	public void handleMessage(ACLMessage aclMessage) {
		if (aclMessage.performative == Performative.REQUEST) { 
			// webSocketClient.sendMessageToClient("Send REQUEST performative from agent " + aclMessage.sender.getName() + "to" + aclMessage.reciever.getName());
			messageManager().post(aclMessage);
			return;
		} 
		
		if (aclMessage.performative == Performative.INFORM) {
			System.out.println(aclMessage.content);
			// webSocketClient.sendMessageToClient("Get INFROM performative from agent " + aclMessage.sender.getName() + "in" + aclMessage.reciever.getName());
			return;
		}
	}

	@Override
	public void init(AID aid) {
		this.myAid = aid;
//		webSocketClient.sendMessageToClient("Kreirao se agent" + aid.getName());
	}
	
	
}

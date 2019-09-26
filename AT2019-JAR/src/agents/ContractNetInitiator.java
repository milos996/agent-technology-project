package agents;

import java.util.Random;

import javax.ejb.Stateful;

import agentmanager.Agent;
import messagemanager.ACLMessage;
import messagemanager.Performative;

@Stateful
public class ContractNetInitiator extends Agent {

	private static final long serialVersionUID = 1L;
	

//	@EJB
//	WebSocketClient webSocketClient;
	
	@Override
	public void handleMessage(ACLMessage aclMessage) {
		if (aclMessage.performative.equals(Performative.CALL_FOR_PROPOSAL)) {
			// webSocketClient.sendMessageToClient("SEND CALL_FOR_PROPOSAL from agent " + aclMessage.sender.getName() + "to" + aclMessage.reciever.getName());
			messageManager().post(aclMessage);
			return;
		}
		
		if (aclMessage.performative.equals(Performative.REFUSE)) {
			// webSocketClient.sendMessageToClient("GET REFUSE from agent " + aclMessage.sender.getName() + "in" + aclMessage.reciever.getName());
			return;
		}
		
		if (aclMessage.performative.equals(Performative.PROPOSE)) {
			// webSocketClient.sendMessageToClient("GET PROPOSE from agent " + aclMessage.sender.getName() + "in" + aclMessage.reciever.getName());
			
			Random  random = new Random();
			ACLMessage message = new ACLMessage();
			message.sender = this.getAid();
			message.reciever = aclMessage.sender;

			int result = random.nextInt(2);
			if (result == 0) {
				message.performative = Performative.REJECT_PROPOSAL;
				message.content = "REJECT_PROPOSAL MESSAGE ";
				messageManager().post(message);
				return;
			}
			
			message.performative = Performative.ACCEPT_PROPOSAL;
			message.content = "ACCEPT_PROPOSAL MESSAGE";
			messageManager().post(message);
			return;
		}
		
		if(aclMessage.performative.equals(Performative.FAILURE) ||
			aclMessage.performative.equals(Performative.INFORM_DONE) ||
			aclMessage.performative.equals(Performative.INFORM_RESULT)) {
			// webSocketClient.sendMessageToClient("GET " +  aclMessage.performative.toString() + " from agent " + aclMessage.sender.getName() + "in" + aclMessage.reciever.getName());
			
			if (aclMessage.performative.equals(Performative.INFORM_DONE) || aclMessage.performative.equals(Performative.INFORM_RESULT)) {
				// webSocketClient.sendMessageToClient("GET object" + aclMessage.contentObj.toString());	
			}
		}
	}

}

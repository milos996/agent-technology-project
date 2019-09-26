package agents;

import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import agentmanager.Agent;
import messagemanager.ACLMessage;
import messagemanager.Performative;

@Stateful
public class ContractNetParticipant extends Agent {

	private static final long serialVersionUID = 1L;
	
//	@EJB
//	WebSocketClient webSocketClient;
	
	@Override
	public void handleMessage(ACLMessage aclMessage) {
		if (aclMessage.performative.equals(Performative.CALL_FOR_PROPOSAL)) {

			// webSocketClient.sendMessageToClient("GET CALL_FOR_PROPOSAL from agent " + aclMessage.sender.getName() + "to" + aclMessage.reciever.getName());
			
			Random  random = new Random();
			ACLMessage message = new ACLMessage();
			message.sender = this.getAid();
			message.reciever = aclMessage.sender;

			int result = random.nextInt(2);
			if (result == 0) {
				message.performative = Performative.REFUSE;
				message.content = "REFUSE sasd ";
				messageManager().post(message);
				return;
			}
			
			message.performative = Performative.PROPOSE;
			message.content = "PROPSOSE MESSAGE";
			messageManager().post(message);
			return;
		} 
		
		if (aclMessage.performative.equals(Performative.REJECT_PROPOSAL)) {
			// webSocketClient.sendMessageToClient("GET REJECT_PROPOSAL from agent " + aclMessage.sender.getName() + "to" + aclMessage.reciever.getName());
		return;
		} 
		
		if (aclMessage.performative.equals(Performative.ACCEPT_PROPOSAL)) {
			// webSocketClient.sendMessageToClient("GET ACCEPT_PROPOSAL from agent " + aclMessage.sender.getName() + "to" + aclMessage.reciever.getName());
			Random  random = new Random();
			ACLMessage message = new ACLMessage();
			message.sender = this.getAid();
			message.reciever = aclMessage.sender;

			int result = random.nextInt(3);
			
			//FAILURE
			if (result == 0) {
				message.performative = Performative.FAILURE;
				messageManager().post(message);
				return;
			}
			
			//INFORM_DONE
			if (result == 1) {
				message.performative = Performative.INFORM_DONE;
				message.contentObj = new Object();
				messageManager().post(message);
				return;
			}
			
			//INFORM_RESULT
			message.performative = Performative.INFORM_RESULT;
			message.contentObj = new Object();
			messageManager().post(message);
			return;
		}
	}
}

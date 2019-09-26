package agents;

import agentmanager.Agent;
import messagemanager.ACLMessage;
import messagemanager.Performative;

public class AgentPong extends Agent {

	private static final long serialVersionUID = -8913458083105208623L;

	@Override
	public void handleMessage(ACLMessage aclMessage) {
		if (aclMessage.performative == Performative.REQUEST) {
			ACLMessage responseMessage = new ACLMessage(Performative.INFORM);
		}
	}

}

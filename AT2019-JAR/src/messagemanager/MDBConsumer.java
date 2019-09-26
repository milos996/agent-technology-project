package messagemanager;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import agentmanager.AID;
import agentmanager.Agent;
import agentmanager.AgentInterface;
import agentmanager.AgentManager;


@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/at_queue")
})
public class MDBConsumer implements MessageListener {
	@EJB
	private AgentManager agm;

	@Override
	public void onMessage(Message msg) {
		try {
			processMessage(msg);
		} catch (JMSException ex) {
			ex.printStackTrace();
		}
	}

	private void processMessage(Message msg) throws JMSException {
		ACLMessage acl = (ACLMessage) ((ObjectMessage) msg).getObject();
		AID aid = acl.reciever;
		sendToAgent(acl, aid);
	}

	private AID getAid(Message msg, ACLMessage acl) throws JMSException {
		int i = msg.getIntProperty("AIDIndex");
		return acl.receivers.get(i);
	}

	private void sendToAgent(ACLMessage msg, AID aid) {
//		AgentInterface agent =  agm.getAgentReference(aid);
		Agent agent = null;
		if (agent != null) {
			agent.handleMessage(msg);
		} else {
			System.out.println("There is not such agent!");
		}
	}
}

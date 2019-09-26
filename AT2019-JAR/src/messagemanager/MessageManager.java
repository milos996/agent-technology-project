package messagemanager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueSession;
import javax.jms.Session;

import agentmanager.AID;
import agentmanager.Agent;

@Stateless
@Remote(MessageManagerInterface.class)
@LocalBean
public class MessageManager implements MessageManagerInterface {
	private Session session;
	
	private Connection connection;
	
	@Resource(lookup = "java:jboss/exported/jms/RemoteConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	private MessageProducer producer;
	
	@Resource(lookup = "java:jboss/exported/jms/queue/at_queue")
	private Queue defaultQueue;
	
	
	@PostConstruct
	public void postConstruct() {
		createConnection();
		createSessionAndProducer();
	}

	@PreDestroy
	public void preDestroy() {
		try {
			session.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<String> getPerformatives() {
		final Performative[] arr = Performative.values();
		List<String> list = new ArrayList<>(arr.length);
		for (Performative p : arr)
			list.add(p.toString());
		return list;
	}

	@Override
	public void post(ACLMessage message) {
		for (int i = 0; message.receivers != null && i < message.receivers.size(); i++) {
			// Check if agent exist in memory ?
			AID messageRecieverAID = message.receivers.get(i);
			
			if (messageRecieverAID == null) {
				throw new IllegalArgumentException("AID cannot be null.");
			}
			
			try {
				ObjectMessage jmsMsg = session.createObjectMessage(message);
				setupJmsMsg(jmsMsg, messageRecieverAID, i);
				producer.send(jmsMsg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		if (message.reciever != null) {
			AID messageRecieverAID = message.reciever;
			
			try {
				ObjectMessage jmsMsg = session.createObjectMessage(message);
				setupJmsMsg(jmsMsg, messageRecieverAID, 1);
				producer.send(jmsMsg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}
	
	private void setupJmsMsg(ObjectMessage jmsMsg, AID aid, int index) {
		try {
			jmsMsg.setStringProperty("JMSXGroupID", aid.getHostName());
			jmsMsg.setIntProperty("AIDIndex", index);
			jmsMsg.setStringProperty("_HQ_DUPL_ID", UUID.randomUUID().toString());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	private void createConnection() {
		try {
			connection = connectionFactory.createConnection("guest", "guest.guest.1");
			connection.setClientID(Agent.MODULE);
			connection.start();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createSessionAndProducer() {
		try {
			session =  connection.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(defaultQueue);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}

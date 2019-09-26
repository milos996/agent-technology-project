package agentmanager;

import javax.ejb.Lock;
import javax.ejb.LockType;

import messagemanager.ACLMessage;
import messagemanager.MessageManager;
import util.ObjectFactory;

@Lock(LockType.READ)
public abstract class Agent implements AgentInterface {
	private static final long serialVersionUID = 1L;
	
	public static final String MODULE = "AT2019-JAR";
	public static final String EAR = "AT2019-EAR";
	
	protected AID myAid;

	@Override
	public void init(AID aid) {
		myAid = aid;
	}
	
	@Override
	public void handleMessage(ACLMessage aclMessage) {
	
	}
	
	@Override
	public AID getAid() {
		return myAid;
	}
	
	@Override
	public MessageManager messageManager() {
		return ObjectFactory.getMessageManager();
	}
	
}
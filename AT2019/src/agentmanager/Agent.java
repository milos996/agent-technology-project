package agentmanager;

import messagemanager.ACLMessage;

public abstract class Agent implements AgentInterface {
	private static final long serialVersionUID = 1L;
	
	public static final String MODULE = "AT2019";
	public static final String EAR = "AT2019-EAR";
	
	protected AID myAid;

	public void init(AID aid) {
		myAid = aid;
	}
	
	@Override
	public void handleMessage(ACLMessage msg) {
	
	}

	public AID getAid() {
		return myAid;
	}
}
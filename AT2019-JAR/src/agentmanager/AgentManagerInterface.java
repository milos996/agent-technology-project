package agentmanager;

import java.io.Serializable;
import java.util.List;

public interface AgentManagerInterface extends Serializable {

	public AID startServerAgent(String type, String name);

	public void stopAgent(AID aid);

	public List<AID> getRunningAgents();
	
	public List<String> getAgentTypes();
	
	public Agent getAgentReference(AID aid);
}

package agentmanager;

import java.io.Serializable;
import java.util.List;

public interface AgentManagerInterface extends Serializable {

	public void startServerAgent(AID aid);

	public void stopAgent(AID aid);

	public List<AID> getRunningAgents();

	public AID getAIDByRuntimeName(String runtimeName);
	
	public Agent getAgentReference(AID aid);
}

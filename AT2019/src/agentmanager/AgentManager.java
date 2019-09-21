package agentmanager;

import java.util.List;

import org.infinispan.Cache;

import util.GlobalCache;


public class AgentManager implements AgentManagerInterface {

	private Cache<AID, Agent> agents;

	@Override
	public void startServerAgent(AID aid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopAgent(AID aid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AID> getRunningAgents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AID getAIDByRuntimeName(String runtimeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Agent getAgentReference(AID aid) {
		return getCache().get(aid);
	}
	
	private Cache<AID, Agent> getCache() {
		if (agents == null)
			agents = GlobalCache.get().getRunningAgents();
		return agents;
	}


}

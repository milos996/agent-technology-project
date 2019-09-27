package agentmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import org.infinispan.Cache;
import util.GlobalCache;
import util.ObjectFactory;

@Stateless
@Remote(AgentManagerInterface.class)
@LocalBean
public class AgentManager implements AgentManagerInterface {
	private static final long serialVersionUID = -9020911734020895415L;
	private Cache<AID, AgentInterface> agents;
	
	
	@Override
	public AID startServerAgent(String type, String name) {
		AID aid = new AID(name, AgentType.build(type), null);

		if (getCache().containsKey(aid)) {
			stopAgent(aid);
		}
		
		Agent agent = null;
		
		try {
			agent =  ObjectFactory.lookup(getAgentLookup(aid, true), Agent.class);
		} catch (IllegalStateException ex) {
			agent =  ObjectFactory.lookup(getAgentLookup(aid, false), Agent.class);
		}
		
		initAgent(agent, aid);
		
		return aid;
	}

	@Override
	public void stopAgent(AID aid) {
		for (AID item: getCache().keySet()) {
			if (item.getName().equals(aid.getName())) {
				getCache().remove(item);
				break;
			}
		}
	}
	
	@Override
	public Agent getAgentReference(AID aid) {
		for (AID item : getCache().keySet()) {
			if (item.getName().equals(aid.getName())) {
				return (Agent) getCache().get(item);
			}
		}
		
		return null;
	}
	
	@Override
	public List<AID> getRunningAgents() {
		Set<AID> set = getCache().keySet();
		if (set.size() > 0) {
			try {
				AID aid = set.iterator().next();
				try {
					ObjectFactory.lookup(getAgentLookup(aid, true), AgentInterface.class);
				} catch (Exception ex) {
					ObjectFactory.lookup(getAgentLookup(aid, false), AgentInterface.class);
				}
			} catch (Exception ex) {
				getCache().clear();
				return new ArrayList<AID>();
			}
		}
		return new ArrayList<AID>(set);
	}

	
	@Override
	public List<String> getAgentTypes() {
		final AgentTypes[] arr = AgentTypes.values();
		List<String> list = new ArrayList<>(arr.length);
		for (AgentTypes at : arr)
			list.add(at.toString());
		return list;
	}
	
	private Cache<AID, AgentInterface> getCache() {
		if (agents == null) {
			agents = GlobalCache.get().getRunningAgents();
		}
		
		return agents;
	}	
	
	private String getAgentLookup(AID aid, boolean stateful) {
		return "java:module/" + aid.getType().getModule() + "!agents." + aid.getType().getModule();
	}
	 
	private void initAgent(AgentInterface agent, AID aid) {
		agent.init(aid);
		getCache().put(aid, agent);	
	}
}

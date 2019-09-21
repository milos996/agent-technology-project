package rest;

import java.util.List;

import javax.ejb.EJB;

import agentmanager.AID;
import agentmanager.Agent;
import agentmanager.AgentManager;
import agentmanager.AgentType;
import messagemanager.ACLMessage;
import messagemanager.MessageManager;

public class RestAt2019Impl implements RestAt2019 {
	
	@EJB
	AgentManager agm;

	@EJB
	MessageManager msm;

	@Override
	public List<AgentType> getAgentTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Agent> getRunningAgents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean startAgent(String type, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean stopAgent(AID aid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendMessage(ACLMessage aclMessage) {
		msm.post(aclMessage);
		return true;
	}

	@Override
	public List<String> getPerformatives() {
		return msm.getPerformatives();
	}

}

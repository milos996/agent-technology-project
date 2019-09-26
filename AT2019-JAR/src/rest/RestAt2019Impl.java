package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import agentmanager.AID;
import agentmanager.AgentManager;
import messagemanager.ACLMessage;
import messagemanager.MessageManager;

@Stateless
@LocalBean
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Remote(RestAt2019.class)
@Path("/managers")
public class RestAt2019Impl implements RestAt2019 {
	
	@EJB
	AgentManager agm;

	@EJB
	MessageManager msm;

	@Override
	public List<String> getAgentTypes() {
		return agm.getAgentTypes();
	}

	@Override
	public List<AID> getRunningAgents() {
		return agm.getRunningAgents();
	}

	@Override
	public AID startAgent(String type, String name) {	
		return agm.startServerAgent(type, name);
	}

	@Override
	public void stopAgent(AID aid) {
		agm.stopAgent(aid);
	}

	@Override
	public void sendMessage(ACLMessage aclMessage) {
		msm.post(aclMessage);
	}

	@Override
	public List<String> getPerformatives() {
		return msm.getPerformatives();
	}

}

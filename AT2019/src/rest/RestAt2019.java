package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import agentmanager.AID;
import agentmanager.Agent;
import agentmanager.AgentType;
import messagemanager.ACLMessage;
import messagemanager.Performative;

public interface RestAt2019 {
	
	@GET
	@Path("/agents/classes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<AgentType> getAgentTypes();
	
	@GET
	@Path("/agents/running")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Agent> getRunningAgents();
	
	@PUT
	@Path("/agents/running/{type}/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean startAgent(@PathParam("type") String type,  @PathParam("name") String name);
	
	@DELETE
	@Path("/agents/running/{aid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean stopAgent(AID aid);

	@POST
	@Path("/messages")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean sendMessage(ACLMessage aclMessage);
	
	@GET
	@Path("/messages")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getPerformatives();
}

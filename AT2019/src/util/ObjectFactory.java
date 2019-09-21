package util;

import javax.ejb.SessionContext;
import javax.naming.NamingException;

import agentmanager.Agent;
import agentmanager.AgentManager;
import messagemanager.MessageManager;
import rest.RestAt2019;
import rest.RestAt2019Impl;

public abstract class ObjectFactory {
	public static final String AgentManagerLookup = "ejb:" + Agent.EAR + "/" + Agent.MODULE + "//"
			+ AgentManager.class.getSimpleName() + "!" + AgentManager.class.getName();
	public static final String MessageManagerLookup = "ejb:" + Agent.EAR + "/" + Agent.MODULE + "//"
			+ MessageManager.class.getSimpleName() + "!" + MessageManager.class.getName();
	public static final String WebClientManagerLookup = "ejb:" + Agent.EAR + "/" + Agent.MODULE + "//"
			+ RestAt2019Impl.class.getSimpleName() + "!" + RestAt2019.class.getName()
			+ "?stateful";
//	public static final String JMSFactoryLookup = "java:app/" + Agent.MODULE + "/"
//			+ JMSFactory.class.getSimpleName();

//	public static AgentManager getAgentManager(SiebogNode remote) {
//		return lookup(AgentManagerLookup, AgentManager.class, remote);
//	}
//
//	public static MessageManager getMessageManager(SiebogNode remote) {
//		return lookup(MessageManagerLookup, MessageManager.class, remote);
//	}
//
//	public static SiebogRest getWebClientManager() {
//		return lookup(WebClientManagerLookup, SiebogRestBean.class, SiebogNode.LOCAL);
//	}
//
//	public static SessionContext getSessionContext() {
//		return lookup("java:comp/EJBContext", SessionContext.class, SiebogNode.LOCAL);
//	}
//
//	public static JMSFactory getJMSFactory() {
//		return lookup(JMSFactoryLookup, JMSFactory.class, SiebogNode.LOCAL);
//	}

	@SuppressWarnings("unchecked")
	public static <T> T lookup(String name, Class<T> c) {
		try {
			return (T) ContextFactory.get().lookup(name);
		} catch (NamingException ex) {
			throw new IllegalStateException("Failed to lookup " + name, ex);
		}
	}
}

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

	public static MessageManager getMessageManager() {
		return lookup(MessageManagerLookup, MessageManager.class);
	}

	@SuppressWarnings("unchecked")
	public static <T> T lookup(String name, Class<T> c) {
		try {
			return (T) ContextFactory.get().lookup(name);
		} catch (NamingException ex) {
			throw new IllegalStateException("Failed to lookup " + name, ex);
		}
	}
}

package util;

import javax.naming.NamingException;

import messagemanager.MessageManager;

public abstract class ObjectFactory {
	public static final String AgentManagerLookup = "java:module/AgentManager!agentmanager.AgentManagerInterface";
	public static final String MessageManagerLookup = "java:module/MessageManager!messagemanager.MessageManager";
	
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

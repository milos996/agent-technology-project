package agentmanager;

public class AgentType {
	private String name;
	private String module;
	
	public AgentType(String name, String module) {
		this.name = name;
		this.module = module;
	}

	public String getName() {
		return name;
	}

	public String getModule() {
		return module;
	}
}

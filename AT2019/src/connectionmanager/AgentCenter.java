package connectionmanager;

public class AgentCenter {
	
	private String alias;
	
	private String address;
	
	public AgentCenter(String alias, String address) {
		super();
		this.alias = alias;
		this.address = address;
	}

	public String getAlias() {
		return alias;
	}
	
	public String getAddress() {
		return address;
	}
}

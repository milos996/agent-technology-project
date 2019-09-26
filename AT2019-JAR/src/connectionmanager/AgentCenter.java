package connectionmanager;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@Remote(AgentCenter.class)
public class AgentCenter {
	
	private String alias;
	
	private String address;
	
	public AgentCenter() {
		this.address = "localhost";
		this.alias = "";
	}
	
	public AgentCenter(String alias, String address) {
		this.alias = alias;
		this.address = address;
	}
	
	@PostConstruct
	private void init() {}

	public String getAlias() {
		return alias;
	}
	
	public String getAddress() {
		return address;
	}
}

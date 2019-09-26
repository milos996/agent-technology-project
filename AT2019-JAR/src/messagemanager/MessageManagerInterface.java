package messagemanager;

import java.util.List;

public interface MessageManagerInterface {
	
	List<String> getPerformatives();

	void post(ACLMessage message);
}

package thesis.mobilis.api;

import java.util.List;

public interface SimpleComponent {

	List<String> getServiceNames();
	
	String getService(String serviceName);
	
	void startup();
	
	void shutdown();
	
}

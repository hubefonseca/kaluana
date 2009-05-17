package mobilis.impl.adaptation;

import java.util.HashMap;

public class ServiceState {

	private HashMap<String, String> componentReceptacles;
	
	public ServiceState() {
		componentReceptacles = new HashMap<String, String>();
	}
	
	public void addService(String componentName, String receptacleName) {
		componentReceptacles.put(componentName, receptacleName);
	}
	
}


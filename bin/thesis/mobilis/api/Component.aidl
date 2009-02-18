package thesis.mobilis.api;

import thesis.mobilis.api.Service;

interface Component {

	void getServiceNames(out List<String> serviceNames);
	
	Service getService(in String serviceName);
	
	void startup();
	
	void shutdown();
	
}
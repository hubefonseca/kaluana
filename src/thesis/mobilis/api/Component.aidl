package thesis.mobilis.api;

interface Component {

	void getServiceNames(out List<String> serviceNames);
	
	String getService(String serviceName);
	
	void startup();
	
	void shutdown();
	
}
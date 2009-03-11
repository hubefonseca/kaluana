package thesis.mobilis.api;

interface IComponent {

	void registerService(String serviceName);
	
	void getServiceNames(out List<String> serviceNames);
	
	String getService(String serviceName);
	
	void startup();
	
	void shutdown();
	
}
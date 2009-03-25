package thesis.mobilis.api.adaptation;

interface IAdaptable {
	
	void registerDependencies();
	
	void start();
	
	void stop();
	
}
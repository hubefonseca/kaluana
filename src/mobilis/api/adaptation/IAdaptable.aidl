package mobilis.api.adaptation;

interface IAdaptable {
	
	void registerDependencies();
	
	void getDependencies();
	
	void start();
	
	void stop();
	
}
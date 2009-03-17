package thesis.mobilis.api;

interface IBindListener {
	
	void connected(String componentName);
	
	void disconnected(String componentName);
	
}
package thesis.mobilis.api;

interface IBinderCallbackListener {
	
	void bound(String componentName);
	
	void unbound(String componentName);
	
}
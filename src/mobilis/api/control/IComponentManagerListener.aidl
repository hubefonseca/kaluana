package mobilis.api.control;

interface IComponentManagerListener {
	
	/**
	 * This method is called when the component manager is completely
	 * loaded
	 **/
	void start();
	
	/**
	 * This method is called when a request to load one or more components
	 * is finished
	 **/
	void componentsLoaded(in long callId);
	
}
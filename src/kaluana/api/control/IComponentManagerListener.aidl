package kaluana.api.control;

interface IComponentManagerListener {
	
	/**
	 * This method is called when a request to load one or more components
	 * is finished
	 **/
	void componentsLoaded(in List<String> components);
	
}
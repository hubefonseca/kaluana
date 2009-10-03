package kaluana.api.control;

import kaluana.api.IComponent;
import kaluana.api.IContainer;
import kaluana.api.control.IComponentManagerListener;

interface IComponentManager {
	
	boolean isLoaded(in String componentName);
	
	void loadComponents(in List<String> categories, in IComponentManagerListener listener);
	
	void unloadComponent(in String componentName);
	
	IContainer getComponent(in String componentName);

	List<String> getLoadedComponentNames();
	
	/**
	 * Called by component's developer
	 */
	void registerComponent(String componentName);
	
	void unregisterComponent(String componentName);
	
	/**
	 * Callback interfaces to notify application
	 */
	void loaded(IContainer component);
	
	void unloaded(in String componentName);
	
}
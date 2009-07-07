package kaluana.api.control;

import kaluana.api.IComponent;
import kaluana.api.control.ILocalLoader;
import kaluana.api.control.IComponentManagerListener;

interface IComponentManager {
	
	boolean isLoaded(in String componentName);
	
	void loadComponents(in List<String> categories, in IComponentManagerListener listener);
	
	void unloadComponent(in String componentName);
	
	ILocalLoader getComponent(in String category);

	List<String> getLoadedComponentNames();
	
	ILocalLoader getByName(String name);
	
	/**
	 * Callback interfaces
	 */
	void loaded(ILocalLoader component);
	
	void unloaded(in String componentName);
	
}
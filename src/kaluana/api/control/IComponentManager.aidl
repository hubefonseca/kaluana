package kaluana.api.control;

import kaluana.api.IComponent;
import kaluana.api.control.IConfigService;
import kaluana.api.control.IComponentManagerListener;

interface IComponentManager {
	
	boolean isLoaded(in String componentName);
	
	void loadComponents(in List<String> categories, in IComponentManagerListener listener);
	
	void unloadComponent(in String componentName);
	
	IConfigService getComponent(in String category);

	List<String> getLoadedComponentNames();
	
	IConfigService getByName(String name);
	
	/**
	 * Callback interfaces
	 */
	void loaded(IConfigService component);
	
	void unloaded(in String componentName);
	
}
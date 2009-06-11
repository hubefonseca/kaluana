package mobilis.api.control;

import mobilis.api.IComponent;
import mobilis.api.control.ILocalLoader;
import mobilis.api.control.IComponentManagerListener;

interface IComponentManager {
	
	boolean isLoaded(in String componentName);
	
	void loadComponents(in List<String> categories, in long callId);
	
	void unloadComponent(in String componentName);
	
	ILocalLoader getComponent(in String category);

	void init(IComponentManagerListener listener);
	
	List<String> getLoadedComponentNames();
	
	ILocalLoader getByName(String name);
	
	/**
	 * Callback interfaces
	 */
	void loaded(ILocalLoader component);
	
	void unloaded(in String componentName);
	
	void addListener(IComponentManagerListener listener);
	
}
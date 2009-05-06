package mobilis.api.control;

import mobilis.api.IComponent;
import mobilis.api.control.ILocalLoader;
import mobilis.api.control.IComponentManagerListener;

interface IComponentManager {
	
	void getLoadedComponents(out List<String> componentNames);
	
	void loadComponents(in List<String> componentNames, in long callId);
	
	ILocalLoader getComponent(in String componentName);

	void init(IComponentManagerListener listener);
	
	/**
	 * Callback interfaces
	 */
	void loaded(ILocalLoader component);
	
	void unloaded(in String componentName);
	
}
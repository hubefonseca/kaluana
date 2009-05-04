package mobilis.api.control;

import mobilis.api.IComponent;
import mobilis.api.control.IComponentManagerListener;

interface IComponentManager {
	
	void getLoadedComponents(out List<String> componentNames);
	
	void loadComponents(in List<String> componentNames, in long callId);
	
	IComponent getComponent(in String componentName);

	void init(IComponentManagerListener listener);
	
	/**
	 * Callback interfaces
	 */
	void loaded(IComponent component);
	
	void unloaded(in String componentName);
	
}
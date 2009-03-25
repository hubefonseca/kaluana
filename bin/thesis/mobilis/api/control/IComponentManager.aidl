package thesis.mobilis.api.control;

import thesis.mobilis.api.IComponent;
import thesis.mobilis.api.control.IComponentLoaderListener;

interface IComponentManager {
	
	void getComponent(String componentName, IComponentLoaderListener listener);
	
	void getLoadedComponents(out List<String> componentNames);
	
}
package thesis.mobilis.api.control;

import thesis.mobilis.api.IComponent;

interface IComponentLoaderListener {
	
	void loaded(IComponent component);
	
	void unloaded(in String componentName);
	
}
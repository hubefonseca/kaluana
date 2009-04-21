package mobilis.api.control;

import mobilis.api.IComponent;

interface IComponentLoaderListener {
	
	void loaded(IComponent component);
	
	void unloaded(in String componentName);
	
}
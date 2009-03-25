package thesis.mobilis.api.control;

import thesis.mobilis.api.IComponent;

interface IComponentCollection {
	
	IComponent getByName(String componentName);
	
	void add(IComponent component);
	
	void list(out List<String> componentNames);
	
}
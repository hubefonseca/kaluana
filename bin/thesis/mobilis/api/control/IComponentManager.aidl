package thesis.mobilis.api.control;

import thesis.mobilis.api.IComponent;

interface IComponentManager {
	
	void loadComponents(in List<String> componentNames, in long callId);
	
	IComponent getComponent(in String componentName);
	
}
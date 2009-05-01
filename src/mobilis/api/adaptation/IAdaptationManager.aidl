package mobilis.api.adaptation;

import mobilis.api.IComponent;

interface IAdaptationManager {
	
	void notifyContextChange();
	
	void registerComponent(String scope);
	
}
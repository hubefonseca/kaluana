package mobilis.context;

import mobilis.api.adaptation.IAdaptationManager;

interface IContextListener {
	
	void registerAdaptationManager(IAdaptationManager adaptationManager);
	
}
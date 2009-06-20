package kaluana.context;

import kaluana.api.adaptation.IAdaptationManager;

interface IContextListener {
	
	/**
	 * Real context information are provided by Android interfaces 
	 * and are not described at this level, only at the implementation.
	**/
	
	void registerAdaptationManager(IAdaptationManager adaptationManager);
	
}
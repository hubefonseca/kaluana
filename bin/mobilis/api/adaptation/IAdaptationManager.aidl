package mobilis.api.adaptation;

import mobilis.api.IComponent;
import mobilis.context.Context;

interface IAdaptationManager {
	
	void onContextChange(in Context context);
	
}
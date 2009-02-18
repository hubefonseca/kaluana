package thesis.mobilis.api.examples.helloworld.services;

import thesis.mobilis.api.examples.helloworld.services.iAnotherService;

interface iAdapter {

	void register(IBinder service);
	
	void unregister(IBinder service);
	
}
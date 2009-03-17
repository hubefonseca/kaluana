package thesis.mobilis.examples.helloworld.services;

import thesis.mobilis.examples.helloworld.services.iAnotherService;

interface iAdapter {

	void register(IBinder service);
	
	void unregister(IBinder service);
	
}
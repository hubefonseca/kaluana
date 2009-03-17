package thesis.mobilis.api;

import thesis.mobilis.api.IReceptacle;

interface IComponent {
	
	
	// tem que oferecer m�todos para que o component manager
	// registre as facetas e recept�culos ao chamar o componente
	
	// fazer o bind no Receptacle impede recept�culos m�ltiplos?
	
	
	void registerService(String serviceName, String interfaceName);
	
	void registerReceptacle(String receptacleName, String interfaceName);
	
	void getServiceNames(out List<String> serviceNames);
	
	void getReceptacleNames(out List<String> receptacleNames);
	
	IBinder getService(String serviceName);
	
	IReceptacle getReceptacle(String receptacleName);
	
	void start();
	
	void stop();
	
}
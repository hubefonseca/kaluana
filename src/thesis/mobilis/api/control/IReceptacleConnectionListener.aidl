package thesis.mobilis.api.control;

interface IReceptacleConnectionListener {
	
	void connected(String receptacleName);
	
	void disconnected(String receptacleName);
	
}
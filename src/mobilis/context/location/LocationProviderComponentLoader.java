package mobilis.context.location;

import java.util.List;

import mobilis.api.IReceptacle;
import mobilis.api.control.ILocalLoader;
import mobilis.impl.Component;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class LocationProviderComponentLoader extends Service {

	private Component component;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return mComponentManager;
	}

	private final ILocalLoader.Stub mComponentManager = new ILocalLoader.Stub() {

		@Override
		public String getName() throws RemoteException {
			return component.getName();
		}
		
		@Override
		public void buildComponent() throws RemoteException {
			component = new LocationProviderComponent();			
		}

		@Override
		public IReceptacle getReceptacle(String arg0) throws RemoteException {
			return component.getReceptacle(arg0);
		}

		@Override
		public void getReceptacleNames(List<String> arg0)
				throws RemoteException {
			component.getReceptacleNames(arg0);
		}

		@Override
		public IBinder getService(String arg0) throws RemoteException {
			return component.getService(arg0);
		}

		@Override
		public void getServiceNames(List<String> arg0) throws RemoteException {
			component.getServiceNames(arg0);
		}

		@Override
		public void registerDependency(String arg0) throws RemoteException {
			component.registerDependency(arg0);
		}

		@Override
		public void registerReceptacle(String arg0, String arg1)
				throws RemoteException {
			component.registerReceptacle(arg0, arg1);			
		}

		@Override
		public void registerReceptacles() throws RemoteException {
			component.registerReceptacles();
		}

		@Override
		public void registerService(String arg0, String arg1)
				throws RemoteException {
			component.registerService(arg0, arg1);
		}

		@Override
		public void registerServices() throws RemoteException {
			component.registerServices();
		}

		@Override
		public void start() throws RemoteException {
			component.setContextWrapper(getThis());
			component.start();
		}
		
	};
	
	public LocationProviderComponentLoader getThis() {
		return this;
	}
}

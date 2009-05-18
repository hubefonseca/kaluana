package mobilis.impl.control;

import java.util.List;

import mobilis.api.ReceptacleInfo;
import mobilis.api.ServiceInfo;
import mobilis.api.control.ILocalLoader;
import mobilis.impl.Component;
import android.content.ContextWrapper;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * This class represents the Stub implementation on each LocalLoader service 
 * @author Hubert
 *
 */
public class LocalLoader extends ILocalLoader.Stub {
	
	private Component component;
	
	public LocalLoader(Component component, ContextWrapper contextWrapper) {
		component.setContextWrapper(contextWrapper);
		this.component = component;
	}
	
	@Override
	public String getName() throws RemoteException {
		return component.getName();
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
	public void registerReceptacles() throws RemoteException {
		component.registerReceptacles();
	}

	@Override
	public void registerServices() throws RemoteException {
		component.registerServices();
	}
	
	@Override
	public String getCategory() throws RemoteException {
		return component.getCategory();
	}

	@Override
	public void start() throws RemoteException {
		component.start();
	}
	
	@Override
	public void stop() throws RemoteException {
		component.stop();
	}

	@Override
	public void bindService(String serviceName, IBinder binder)
			throws RemoteException {
		component.bindService(serviceName, binder);
	}
	
	@Override
	public ServiceInfo getServiceInfo(String serviceName)
			throws RemoteException {
		return component.getServiceInfo(serviceName);
	}

	@Override
	public void bindReceptacle(ReceptacleInfo receptacleInfo, IBinder service,
			ServiceInfo serviceInfo) throws RemoteException {
		component.bindReceptacle(receptacleInfo, service, serviceInfo);
	}

	@Override
	public ReceptacleInfo getReceptacleInfo(String receptacleName)
			throws RemoteException {
		ReceptacleInfo receptacleInfo = component.getReceptacleInfo(receptacleName);
		return receptacleInfo;
	}
	
}

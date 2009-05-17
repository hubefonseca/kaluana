package mobilis.impl.control;

import java.util.List;

import mobilis.api.IReceptacle;
import mobilis.api.IService;
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
	private ContextWrapper contextWrapper;
	
	public LocalLoader(Component component, ContextWrapper contextWrapper) {
		this.component = component;
		this.contextWrapper = contextWrapper;
	}
	
	@Override
	public String getName() throws RemoteException {
		return component.getName();
	}

	@Override
	public IReceptacle getReceptacle(String arg0)
			throws RemoteException {
		IReceptacle receptacle = component.getReceptacle(arg0);
		return receptacle;
	}
	
	@Override
	public void getReceptacleNames(List<String> arg0)
			throws RemoteException {
		component.getReceptacleNames(arg0);
	}

	@Override
	public IBinder getService(String arg0) throws RemoteException {
		return component.getService(arg0).getServiceImpl();
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
		component.setContextWrapper(contextWrapper);
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
	public String getServiceInterface(String arg0)
			throws RemoteException {
		return component.getServiceInterface(arg0);
	}

	@Override
	public IService getBoundService(String receptacleName)
			throws RemoteException {
		return component.getBoundService(receptacleName);
	}

	@Override
	public void bindReceptacle(String receptacleName, IBinder binder, String serviceName, String providerComponentName) 
			throws RemoteException {
		IService service = new mobilis.impl.Service();
		service.setComponentName(providerComponentName);
		service.setName(serviceName);
		service.setServiceImpl(binder);
		component.bindReceptacle(receptacleName, service);
	}
	
}

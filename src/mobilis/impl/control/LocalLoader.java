package mobilis.impl.control;

import java.util.List;

import mobilis.api.IReceptacle;
import mobilis.api.control.ILocalLoader;
import mobilis.impl.Component;
import android.content.ContextWrapper;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * This class represents the Stub implementation on every Local Loader service 
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
	public void bind(String serviceName, IBinder service)
			throws RemoteException {
		component.bind(serviceName, service);
	}
	
}

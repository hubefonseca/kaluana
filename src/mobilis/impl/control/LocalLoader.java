package mobilis.impl.control;

import java.util.List;

import mobilis.api.IComponent;
import mobilis.api.IReceptacle;
import mobilis.api.control.ILocalLoader;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * This class represents the Stub implementation on every Local Loader service 
 * @author Hubert
 *
 */
public class LocalLoader extends ILocalLoader.Stub {
	
	private IComponent component;
	
	public LocalLoader(IComponent component) {
		this.component = component;
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
		Log.d(this.getClass().getName(), "Starting component...");
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

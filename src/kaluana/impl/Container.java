package kaluana.impl;

import java.lang.reflect.Field;
import java.util.List;

import kaluana.api.IContainer;
import kaluana.api.ReceptacleInfo;
import kaluana.api.ServiceInfo;
import kaluana.api.annotations.Receptacle;
import kaluana.api.annotations.Service;
import kaluana.impl.adaptation.InternalState;
import android.content.ContextWrapper;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * The Stub implementation on each component's container 
 * @author hubertfonseca
 *
 */
public class Container extends IContainer.Stub {
	
	private Component component;
	
	public Container(Component component, ContextWrapper contextWrapper) {
		component.setContextWrapper(contextWrapper);
		this.component = component;
	}
	
	@Override
	public String getSimpleName() throws RemoteException {
		return component.getSimpleName();
	}
	
	@Override
	public String getFullName() throws RemoteException {
		return component.getFullName();
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
		Field[] fields = component.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Receptacle.class)) {
				component.registerReceptacle(field.getName(), field.getType().getName());
			}
		}
	}

	@Override
	public void registerServices() throws RemoteException {
		Field[] fields = component.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Service.class)) {
				component.registerService(field.getName(), field.getType().getName());
			}
		}
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
	public InternalState getInternalState() throws RemoteException {
		return component.getInternalState();
	}

	@Override
	public void setInternalState(InternalState componentInfo) throws RemoteException {
		component.setInternalState(componentInfo);
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
		return component.getReceptacleInfo(receptacleName);
	}
	
}

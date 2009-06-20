package kaluana.impl.control;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import kaluana.api.ReceptacleInfo;
import kaluana.api.ServiceInfo;
import kaluana.api.annotations.Receptacle;
import kaluana.api.annotations.Service;
import kaluana.api.control.ILocalLoader;
import kaluana.impl.Component;
import kaluana.impl.adaptation.InternalState;
import android.content.ContextWrapper;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * This class represents the Stub implementation on each LocalLoader service 
 * @author hubertfonseca
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
	public String getCategory() throws RemoteException {
		Annotation annotation = component.getClass().getAnnotation(kaluana.api.annotations.Component.class);
		if (annotation == null) {
			Log.e(this.getClass().getName(), "Component should be annotated with @Component annotation");
		}	
		return ((kaluana.api.annotations.Component)annotation).category();
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
		ReceptacleInfo receptacleInfo = component.getReceptacleInfo(receptacleName);
		return receptacleInfo;
	}
	
}

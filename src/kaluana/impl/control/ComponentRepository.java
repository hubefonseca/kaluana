package kaluana.impl.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kaluana.api.IContainer;
import kaluana.api.control.IComponentManager;
import kaluana.api.control.IComponentRepository;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 *  
 * @author hubertfonseca
 *
 */
public class ComponentRepository implements IComponentRepository {

	private IComponentManager listener;
	private Context applicationContext;

	// Available component names
	private List<String> componentNames;
	
	// Services offered by each component
	private HashMap<String, List<ServiceConnection>> serviceConnections;
	
	public ComponentRepository(Context applicationContext, IComponentManager listener) {
		this.applicationContext = applicationContext;
		this.listener = listener;
		
		serviceConnections = new HashMap<String, List<ServiceConnection>>();
		componentNames = new ArrayList<String>();
	}
	
	// TODO: load component by other criteria
	
	@Override
	public int loadComponent(String componentName) throws RemoteException {
		Log.d(this.getClass().getName(), "searching for component " + componentName + "...");

		RemoteServiceConnection mRemoteServiceConnection = new RemoteServiceConnection();
		
		// Load the component by its name
		Intent intent = new Intent(componentName);
		if (applicationContext.getPackageManager().queryIntentServices(intent, 0).size() > 0) {
			applicationContext.bindService(intent, mRemoteServiceConnection, Context.BIND_AUTO_CREATE);
		} else {
			// No component found
			Log.e(this.getClass().getName(), "Bug. Component is registered but does not exist");
			return 1;
		}
		
		return 0;
	}

	@Override
	public void registerComponent(String componentName) throws RemoteException {
		componentNames.add(componentName);
	}
	
	@Override
	public void unregisterComponent(String componentName) throws RemoteException {
		componentNames.remove(componentName);
	}
	
	@Override
	public int unloadComponent(String componentName) throws RemoteException {
		List<ServiceConnection> services = serviceConnections.get(componentName);
		for (ServiceConnection serviceConnection : services) {
			Log.i(this.getClass().getName(), "unloading service: " + serviceConnection);
			applicationContext.unbindService(serviceConnection);
		}
		return 0;
	}
	
	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This class corresponds to the connection to each container.
	 * A component is achieved by other processes through this connection
	 * @author hubertfonseca
	 *
	 */
	class RemoteServiceConnection implements ServiceConnection {

		IContainer container;
		int serviceCounter = 0;

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			try {				
				container = IContainer.Stub.asInterface(service);

				Log.i(this.getClass().getName(), "remote loader: " + container.getFullName());

				// Stores the connection to this loader, so its possible to unbind it
				addService(container.getFullName(), this);
				
				container.registerReceptacles();
				container.registerServices();

				List<String> serviceNames = new ArrayList<String>();
				container.getServiceNames(serviceNames);
				serviceCounter = serviceNames.size();
				
				Log.i(this.getClass().getName(), container.getFullName() + ": Remote loader bound! " + serviceCounter + " services to bind on this component");
				
				if (serviceCounter == 0) {
					// No services to bind
					listener.loaded(container);
				}

				for (String serviceName : serviceNames) {
					ContainerConnection mLocalServiceConnection = new ContainerConnection(); 
					
					String serviceInterface = container.getServiceInfo(serviceName).getInterfaceName();
					
					Intent intent = new Intent(serviceInterface);
					mLocalServiceConnection.setServiceName(serviceName);
					applicationContext.bindService(intent, mLocalServiceConnection, Context.BIND_AUTO_CREATE);
				}

			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i(this.getClass().getName(), "Component stopped!");
		}

		/**
		 * This class corresponds to a container connection to each service
		 * provided by a Kaluana component
		 * @author hubertfonseca
		 *
		 */
		class ContainerConnection implements ServiceConnection {

			private String serviceName = null;

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				try {
					container.bindService(serviceName, service);
					
					// Stores the connection to the Mobilis service as well, so its possible to unbind it
					addService(container.getFullName(), this);
					
					Log.i(this.getClass().getName(), "local loader: " + container.getFullName());
					
					if (--serviceCounter == 0) {
						// All component's services are bound
						listener.loaded(container);
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {
				Log.i(this.getClass().getName(), "Service stopped: " + serviceName);
			}

			public void setServiceName(String name) {
				this.serviceName = name;
			}
		};
	}

	private void addService(String componentName, ServiceConnection serviceConnection) {
		List<ServiceConnection> services = serviceConnections.get(componentName);
		if (services == null) {
			services = new ArrayList<ServiceConnection>();
		}
		services.add(serviceConnection);
		serviceConnections.put(componentName, services);
	}
	
}
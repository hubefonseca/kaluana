package mobilis.impl.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import mobilis.api.control.IComponentManager;
import mobilis.api.control.ILocalLoader;
import mobilis.api.control.IRemoteLoader;
import mobilis.impl.Component;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 *  
 * @author Hubert
 *
 */
public class ComponentLoader implements IRemoteLoader {

	private ContextWrapper contextWrapper;

	public ComponentLoader(ContextWrapper contextWrapper) {
		this.contextWrapper = contextWrapper;
	}

	@Override
	public void loadBestComponent(String contextRepresentation,
			IComponentManager listener) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public int loadComponent(String componentName,
			IComponentManager listener) throws RemoteException {
		Class clazz = null;

		synchronized (this) {
			// Search inside this package
			Log.d(this.getClass().getName(), "searching for component on this package...");
			try {
				clazz = Class.forName(componentName);
				Component component = null;

				component = (Component)clazz.newInstance();
				component.start();
				return 0;

			} catch (ClassNotFoundException e) {
				// This component does not exist on this package
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Intent intent = new Intent("mobilis.component.Navigator");
			if (clazz == null) {
				// Search in other packages on the device
				Log.d(this.getClass().getName(), "no success. trying on other packages...");

				if (contextWrapper.getPackageManager().queryIntentServices(intent, 0).size() > 0) {
					contextWrapper.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
				} else {
					// If no service found, there isn`t any component like
					// that on the device. Then, try to download it from web
					
					Log.d(this.getClass().getName(), "no success. trying to download component...");
					try {
						URL url = new URL("http://10.0.2.2:8080/android/install");
						HttpURLConnection conn = (HttpURLConnection)url.openConnection();
						conn.setDoOutput(true);

						OutputStreamWriter out = new OutputStreamWriter(
								conn.getOutputStream());
						out.write("componentName=" + componentName);
						out.close();

						BufferedReader in = new BufferedReader(
								new InputStreamReader(
										conn.getInputStream()));

						StringBuffer response = new StringBuffer();
						int c = in.read();
						while (c > -1) {
							response.append((char)c);
							c = in.read();
						}
						in.close();

						
						if (contextWrapper.getPackageManager().queryIntentServices(intent, 0).size() > 0) {
							contextWrapper.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
						} else {
							// No component found
							Log.d(this.getClass().getName(), "component not found");
							return 1;
						}
						
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return 0;
		}
	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}

	public ServiceConnection mServiceConnection = new ServiceConnection() {

		ILocalLoader localLoader;

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			try {
				Log.d(this.getClass().getName(), "remote loader bound");

				localLoader = ILocalLoader.Stub.asInterface(service);

				Log.d(this.getClass().getName(), "starting component");

				localLoader.startComponent("mobilis.examples.navigator.NavigatorComponent");

			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

	};

}
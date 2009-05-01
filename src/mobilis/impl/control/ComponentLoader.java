package mobilis.impl.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import mobilis.api.control.IComponentLoader;
import mobilis.api.control.IComponentManager;
import mobilis.impl.Component;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 *  
 * @author Hubert
 *
 */
public class ComponentLoader implements IComponentLoader {
	
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
	public void loadComponent(String componentName,
			IComponentManager listener) throws RemoteException {
		Class clazz = null;
		
		synchronized (this) {
			// Search inside this package
			Log.d(this.getClass().getName(), "trying on this package...");
			try {
				clazz = Class.forName(componentName);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			if (clazz == null) {
				// Search inside other packages
				Log.d(this.getClass().getName(), "no success. trying on another package...");
				try {
					Context ctx = contextWrapper.createPackageContext("mobilis.examples.navigator", 0);
					Log.d(this.getClass().getName(), "found package: " + ctx.getPackageName());
					String className = "NavigatorActivity";
					ClassLoader classLoader = ctx.getClassLoader();
					Log.d(this.getClass().getName(), "me: " + classLoader);
					Log.d(this.getClass().getName(), "my parent: " + classLoader.getParent());
					try {
						clazz = classLoader.loadClass(className);
					}
					catch (ClassNotFoundException e) {
						Log.d(this.getClass().getName(), className + " doesn`t exist");
						className = "mobilis.examples.navigator.NavigatorActivity";
						try {
							clazz = classLoader.loadClass(className);
						}
						catch (ClassNotFoundException e1) {
							Log.d(this.getClass().getName(), className + " doesn`t exist either");
						}
					}
					
					((Component)clazz.newInstance()).start();
					
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			Log.d(this.getClass().getName(), "component: " + componentName);
			
			if (clazz == null) {
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
					
					Log.d(this.getClass().getName(), "server response: " + response);

					//List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
					//Iterator<PackageInfo> iterator = packages.iterator();
					//while (iterator.hasNext()) {
					//	PackageInfo packageInfo = iterator.next();
					//	Log.d(this.getClass().getName(), "pacote: " + packageInfo.packageName);
					//}

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		Component component = null;
		try {
			component = (Component)clazz.newInstance();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		component.setName(componentName);
		listener.loaded(component);
	}

	@Override
	public IBinder asBinder() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
package kaluana.api;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceInfo implements Parcelable, Comparable<ServiceInfo> {

	private String name;
	private String interfaceName;
	private String componentName;

	public static final Parcelable.Creator<ServiceInfo> CREATOR = new Parcelable.Creator<ServiceInfo>() {
		public ServiceInfo createFromParcel(Parcel in) {
			return new ServiceInfo(in);
		}

		public ServiceInfo[] newArray(int size) {
			return new ServiceInfo[size];
		}
	};
	
	public ServiceInfo(String name, String interfaceName, String componentName) {
		this.name = name;
		this.interfaceName = interfaceName;
		this.componentName = componentName;
	}
	
	public ServiceInfo(Parcel in) {
		readFromParcel(in);
	}

	public void readFromParcel(Parcel in) {
		this.name = in.readString();
		this.interfaceName = in.readString();
		this.componentName = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeString(name);
		arg0.writeString(interfaceName);
		arg0.writeString(componentName);
	}

	@Override
	public int compareTo(ServiceInfo another) {
		return name.compareTo(another.toString());
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

}
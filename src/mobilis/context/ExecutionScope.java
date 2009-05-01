package mobilis.context;

import android.os.Parcel;
import android.os.Parcelable;

public class ExecutionScope implements Parcelable {

	private double latitude;
	private double longitude;
	private double radius;

	public static final Parcelable.Creator<ExecutionScope> CREATOR = new Parcelable.Creator<ExecutionScope>() {
		public ExecutionScope createFromParcel(Parcel in) {
			return new ExecutionScope(in);
		}

		public ExecutionScope[] newArray(int size) {
			return new ExecutionScope[size];
		}
	};

	public ExecutionScope() {
		
	}
	
	public ExecutionScope(Parcel in) {
		readFromParcel(in);
	}

	public void readFromParcel(Parcel in) {
		this.latitude = in.readDouble();
		this.longitude = in.readDouble();
		this.radius = in.readDouble();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeDouble(latitude);
		arg0.writeDouble(longitude);
		arg0.writeDouble(radius);
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

}
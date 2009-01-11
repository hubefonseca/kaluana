package thesis.mobilis.services;

import android.os.Parcel;
import android.os.Parcelable;

public class MobObject implements Parcelable {

	private int id;

	public static final Parcelable.Creator<MobObject> CREATOR = new Parcelable.Creator<MobObject>() {
        public MobObject createFromParcel(Parcel in) {
            return new MobObject(in);
        }
        
        public MobObject[] newArray(int size) {
            return new MobObject[size];
        }
    };

    public MobObject(int id) {
    	this.id = id;
    }
    
    public MobObject(Parcel in) {
    	readFromParcel(in);
    }
    
    public void readFromParcel(Parcel in) {
    	this.id = in.readInt();
    }
    
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		arg0.writeInt(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

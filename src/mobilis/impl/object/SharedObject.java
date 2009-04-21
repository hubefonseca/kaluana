package mobilis.impl.object;
 
import android.os.Parcel;
import android.os.Parcelable;
 
public class SharedObject implements Parcelable {
 
  private int id;
 
  public static final Parcelable.Creator<SharedObject> CREATOR = new Parcelable.Creator<SharedObject>() {
        public SharedObject createFromParcel(Parcel in) {
            return new SharedObject(in);
        }
        
        public SharedObject[] newArray(int size) {
            return new SharedObject[size];
        }
    };
 
    public SharedObject(int id) {
      this.id = id;
    }
    
    public SharedObject(Parcel in) {
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
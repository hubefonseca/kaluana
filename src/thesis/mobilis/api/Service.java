/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\java\\android\\workspace\\thesis\\src\\thesis\\mobilis\\api\\Service.aidl
 */
package thesis.mobilis.api;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
public interface Service extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements thesis.mobilis.api.Service
{
private static final java.lang.String DESCRIPTOR = "thesis.mobilis.api.Service";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an Service interface,
 * generating a proxy if needed.
 */
public static thesis.mobilis.api.Service asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
thesis.mobilis.api.Service in = (thesis.mobilis.api.Service)obj.queryLocalInterface(DESCRIPTOR);
if ((in!=null)) {
return in;
}
return new thesis.mobilis.api.Service.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements thesis.mobilis.api.Service
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
}
}
}

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/hubertfonseca/java/android/workspace/mobilis/src/mobilis/context/IContextListener.aidl
 */
package mobilis.context;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
import mobilis.api.adaptation.IAdaptationManager;
public interface IContextListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements mobilis.context.IContextListener
{
private static final java.lang.String DESCRIPTOR = "mobilis.context.IContextListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IContextListener interface,
 * generating a proxy if needed.
 */
public static mobilis.context.IContextListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof mobilis.context.IContextListener))) {
return ((mobilis.context.IContextListener)iin);
}
return new mobilis.context.IContextListener.Stub.Proxy(obj);
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
case TRANSACTION_registerAdaptationManager:
{
data.enforceInterface(DESCRIPTOR);
mobilis.api.adaptation.IAdaptationManager _arg0;
_arg0 = mobilis.api.adaptation.IAdaptationManager.Stub.asInterface(data.readStrongBinder());
this.registerAdaptationManager(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements mobilis.context.IContextListener
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
/**
	 * Real context information are provided by Android interfaces 
	 * and are not described at this level, only at the implementation.
	*/
public void registerAdaptationManager(mobilis.api.adaptation.IAdaptationManager adaptationManager) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((adaptationManager!=null))?(adaptationManager.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerAdaptationManager, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_registerAdaptationManager = (IBinder.FIRST_CALL_TRANSACTION + 0);
}
/**
	 * Real context information are provided by Android interfaces 
	 * and are not described at this level, only at the implementation.
	*/
public void registerAdaptationManager(mobilis.api.adaptation.IAdaptationManager adaptationManager) throws android.os.RemoteException;
}

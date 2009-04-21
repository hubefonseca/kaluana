/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\java\\android\\workspace\\mobilis\\src\\mobilis\\api\\control\\IComponentManagerListener.aidl
 */
package mobilis.api.control;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
public interface IComponentManagerListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements mobilis.api.control.IComponentManagerListener
{
private static final java.lang.String DESCRIPTOR = "mobilis.api.control.IComponentManagerListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IComponentManagerListener interface,
 * generating a proxy if needed.
 */
public static mobilis.api.control.IComponentManagerListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
mobilis.api.control.IComponentManagerListener in = (mobilis.api.control.IComponentManagerListener)obj.queryLocalInterface(DESCRIPTOR);
if ((in!=null)) {
return in;
}
return new mobilis.api.control.IComponentManagerListener.Stub.Proxy(obj);
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
case TRANSACTION_start:
{
data.enforceInterface(DESCRIPTOR);
this.start();
reply.writeNoException();
return true;
}
case TRANSACTION_componentsLoaded:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
this.componentsLoaded(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements mobilis.api.control.IComponentManagerListener
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
	 * This method is called when the component manager is completely
	 * loaded
	 */
public void start() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_start, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * This method is called when a request to load one or more components
	 * is finished
	 */
public void componentsLoaded(long callId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(callId);
mRemote.transact(Stub.TRANSACTION_componentsLoaded, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_start = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_componentsLoaded = (IBinder.FIRST_CALL_TRANSACTION + 1);
}
/**
	 * This method is called when the component manager is completely
	 * loaded
	 */
public void start() throws android.os.RemoteException;
/**
	 * This method is called when a request to load one or more components
	 * is finished
	 */
public void componentsLoaded(long callId) throws android.os.RemoteException;
}

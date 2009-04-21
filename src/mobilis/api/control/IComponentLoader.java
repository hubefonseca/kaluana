/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\java\\android\\workspace\\mobilis\\src\\mobilis\\api\\control\\IComponentLoader.aidl
 */
package mobilis.api.control;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
public interface IComponentLoader extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements mobilis.api.control.IComponentLoader
{
private static final java.lang.String DESCRIPTOR = "mobilis.api.control.IComponentLoader";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IComponentLoader interface,
 * generating a proxy if needed.
 */
public static mobilis.api.control.IComponentLoader asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
mobilis.api.control.IComponentLoader in = (mobilis.api.control.IComponentLoader)obj.queryLocalInterface(DESCRIPTOR);
if ((in!=null)) {
return in;
}
return new mobilis.api.control.IComponentLoader.Stub.Proxy(obj);
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
case TRANSACTION_loadComponent:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
mobilis.api.control.IComponentLoaderListener _arg1;
_arg1 = mobilis.api.control.IComponentLoaderListener.Stub.asInterface(data.readStrongBinder());
this.loadComponent(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_loadBestComponent:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
mobilis.api.control.IComponentLoaderListener _arg1;
_arg1 = mobilis.api.control.IComponentLoaderListener.Stub.asInterface(data.readStrongBinder());
this.loadBestComponent(_arg0, _arg1);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements mobilis.api.control.IComponentLoader
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
public void loadComponent(java.lang.String componentName, mobilis.api.control.IComponentLoaderListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(componentName);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_loadComponent, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void loadBestComponent(java.lang.String contextRepresentation, mobilis.api.control.IComponentLoaderListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(contextRepresentation);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_loadBestComponent, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_loadComponent = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_loadBestComponent = (IBinder.FIRST_CALL_TRANSACTION + 1);
}
public void loadComponent(java.lang.String componentName, mobilis.api.control.IComponentLoaderListener listener) throws android.os.RemoteException;
public void loadBestComponent(java.lang.String contextRepresentation, mobilis.api.control.IComponentLoaderListener listener) throws android.os.RemoteException;
}

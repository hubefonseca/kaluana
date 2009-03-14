/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\java\\android\\workspace\\thesis\\src\\thesis\\mobilis\\api\\loader\\IComponentLoader.aidl
 */
package thesis.mobilis.api.loader;
import thesis.mobilis.api.IComponent;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
public interface IComponentLoader extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements thesis.mobilis.api.loader.IComponentLoader
{
private static final java.lang.String DESCRIPTOR = "thesis.mobilis.api.loader.IComponentLoader";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IComponentLoader interface,
 * generating a proxy if needed.
 */
public static thesis.mobilis.api.loader.IComponentLoader asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
thesis.mobilis.api.loader.IComponentLoader in = (thesis.mobilis.api.loader.IComponentLoader)obj.queryLocalInterface(DESCRIPTOR);
if ((in!=null)) {
return in;
}
return new thesis.mobilis.api.loader.IComponentLoader.Stub.Proxy(obj);
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
case TRANSACTION_getComponent:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
thesis.mobilis.api.IComponent _result = this.getComponent(_arg0);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_getComponentVersioned:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
thesis.mobilis.api.IComponent _result = this.getComponentVersioned(_arg0, _arg1);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements thesis.mobilis.api.loader.IComponentLoader
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
public thesis.mobilis.api.IComponent getComponent(java.lang.String componentName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
thesis.mobilis.api.IComponent _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(componentName);
mRemote.transact(Stub.TRANSACTION_getComponent, _data, _reply, 0);
_reply.readException();
_result = thesis.mobilis.api.IComponent.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public thesis.mobilis.api.IComponent getComponentVersioned(java.lang.String componentName, java.lang.String componentVersion) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
thesis.mobilis.api.IComponent _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(componentName);
_data.writeString(componentVersion);
mRemote.transact(Stub.TRANSACTION_getComponentVersioned, _data, _reply, 0);
_reply.readException();
_result = thesis.mobilis.api.IComponent.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getComponent = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getComponentVersioned = (IBinder.FIRST_CALL_TRANSACTION + 1);
}
public thesis.mobilis.api.IComponent getComponent(java.lang.String componentName) throws android.os.RemoteException;
public thesis.mobilis.api.IComponent getComponentVersioned(java.lang.String componentName, java.lang.String componentVersion) throws android.os.RemoteException;
}

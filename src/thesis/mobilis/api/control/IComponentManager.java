/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\java\\android\\workspace\\thesis\\src\\thesis\\mobilis\\api\\control\\IComponentManager.aidl
 */
package thesis.mobilis.api.control;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
import thesis.mobilis.api.IComponent;
import java.util.List;
public interface IComponentManager extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements thesis.mobilis.api.control.IComponentManager
{
private static final java.lang.String DESCRIPTOR = "thesis.mobilis.api.control.IComponentManager";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IComponentManager interface,
 * generating a proxy if needed.
 */
public static thesis.mobilis.api.control.IComponentManager asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
thesis.mobilis.api.control.IComponentManager in = (thesis.mobilis.api.control.IComponentManager)obj.queryLocalInterface(DESCRIPTOR);
if ((in!=null)) {
return in;
}
return new thesis.mobilis.api.control.IComponentManager.Stub.Proxy(obj);
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
thesis.mobilis.api.control.IComponentLoaderListener _arg1;
_arg1 = thesis.mobilis.api.control.IComponentLoaderListener.Stub.asInterface(data.readStrongBinder());
this.loadComponent(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_getLoadedComponents:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<java.lang.String> _arg0;
_arg0 = new java.util.ArrayList<java.lang.String>();
this.getLoadedComponents(_arg0);
reply.writeNoException();
reply.writeStringList(_arg0);
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
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements thesis.mobilis.api.control.IComponentManager
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
public void loadComponent(java.lang.String componentName, thesis.mobilis.api.control.IComponentLoaderListener listener) throws android.os.RemoteException
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
public void getLoadedComponents(java.util.List<java.lang.String> componentNames) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getLoadedComponents, _data, _reply, 0);
_reply.readException();
_reply.readStringList(componentNames);
}
finally {
_reply.recycle();
_data.recycle();
}
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
}
static final int TRANSACTION_loadComponent = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getLoadedComponents = (IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getComponent = (IBinder.FIRST_CALL_TRANSACTION + 2);
}
public void loadComponent(java.lang.String componentName, thesis.mobilis.api.control.IComponentLoaderListener listener) throws android.os.RemoteException;
public void getLoadedComponents(java.util.List<java.lang.String> componentNames) throws android.os.RemoteException;
public thesis.mobilis.api.IComponent getComponent(java.lang.String componentName) throws android.os.RemoteException;
}

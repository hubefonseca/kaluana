/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\java\\android\\workspace\\mobilis\\src\\mobilis\\api\\control\\IComponentManager.aidl
 */
package mobilis.api.control;
import mobilis.api.IComponent;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
import java.util.List;
public interface IComponentManager extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements mobilis.api.control.IComponentManager
{
private static final java.lang.String DESCRIPTOR = "mobilis.api.control.IComponentManager";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IComponentManager interface,
 * generating a proxy if needed.
 */
public static mobilis.api.control.IComponentManager asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
mobilis.api.control.IComponentManager in = (mobilis.api.control.IComponentManager)obj.queryLocalInterface(DESCRIPTOR);
if ((in!=null)) {
return in;
}
return new mobilis.api.control.IComponentManager.Stub.Proxy(obj);
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
case TRANSACTION_loadComponents:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<java.lang.String> _arg0;
_arg0 = data.createStringArrayList();
long _arg1;
_arg1 = data.readLong();
this.loadComponents(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_getComponent:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
mobilis.api.IComponent _result = this.getComponent(_arg0);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_init:
{
data.enforceInterface(DESCRIPTOR);
mobilis.api.control.IComponentManagerListener _arg0;
_arg0 = mobilis.api.control.IComponentManagerListener.Stub.asInterface(data.readStrongBinder());
this.init(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_loaded:
{
data.enforceInterface(DESCRIPTOR);
mobilis.api.IComponent _arg0;
_arg0 = mobilis.api.IComponent.Stub.asInterface(data.readStrongBinder());
this.loaded(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_unloaded:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.unloaded(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements mobilis.api.control.IComponentManager
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
public void loadComponents(java.util.List<java.lang.String> componentNames, long callId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStringList(componentNames);
_data.writeLong(callId);
mRemote.transact(Stub.TRANSACTION_loadComponents, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public mobilis.api.IComponent getComponent(java.lang.String componentName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
mobilis.api.IComponent _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(componentName);
mRemote.transact(Stub.TRANSACTION_getComponent, _data, _reply, 0);
_reply.readException();
_result = mobilis.api.IComponent.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public void init(mobilis.api.control.IComponentManagerListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_init, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * Callback interfaces
	 */
public void loaded(mobilis.api.IComponent component) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((component!=null))?(component.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_loaded, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void unloaded(java.lang.String componentName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(componentName);
mRemote.transact(Stub.TRANSACTION_unloaded, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_loadComponents = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getComponent = (IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_init = (IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_loaded = (IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_unloaded = (IBinder.FIRST_CALL_TRANSACTION + 4);
}
public void loadComponents(java.util.List<java.lang.String> componentNames, long callId) throws android.os.RemoteException;
public mobilis.api.IComponent getComponent(java.lang.String componentName) throws android.os.RemoteException;
public void init(mobilis.api.control.IComponentManagerListener listener) throws android.os.RemoteException;
/**
	 * Callback interfaces
	 */
public void loaded(mobilis.api.IComponent component) throws android.os.RemoteException;
public void unloaded(java.lang.String componentName) throws android.os.RemoteException;
}

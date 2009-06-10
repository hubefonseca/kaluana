/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/hubertfonseca/java/android/workspace/mobilis/src/mobilis/api/control/IComponentManager.aidl
 */
package mobilis.api.control;
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
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof mobilis.api.control.IComponentManager))) {
return ((mobilis.api.control.IComponentManager)iin);
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
case TRANSACTION_isLoaded:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.isLoaded(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
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
case TRANSACTION_unloadComponent:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.unloadComponent(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getComponent:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
mobilis.api.control.ILocalLoader _result = this.getComponent(_arg0);
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
case TRANSACTION_getAllNames:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<java.lang.String> _result = this.getAllNames();
reply.writeNoException();
reply.writeStringList(_result);
return true;
}
case TRANSACTION_getByName:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
mobilis.api.control.ILocalLoader _result = this.getByName(_arg0);
reply.writeNoException();
reply.writeStrongBinder((((_result!=null))?(_result.asBinder()):(null)));
return true;
}
case TRANSACTION_loaded:
{
data.enforceInterface(DESCRIPTOR);
mobilis.api.control.ILocalLoader _arg0;
_arg0 = mobilis.api.control.ILocalLoader.Stub.asInterface(data.readStrongBinder());
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
case TRANSACTION_addListener:
{
data.enforceInterface(DESCRIPTOR);
mobilis.api.control.IComponentManagerListener _arg0;
_arg0 = mobilis.api.control.IComponentManagerListener.Stub.asInterface(data.readStrongBinder());
this.addListener(_arg0);
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
public boolean isLoaded(java.lang.String componentName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(componentName);
mRemote.transact(Stub.TRANSACTION_isLoaded, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public void loadComponents(java.util.List<java.lang.String> categories, long callId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStringList(categories);
_data.writeLong(callId);
mRemote.transact(Stub.TRANSACTION_loadComponents, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void unloadComponent(java.lang.String componentName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(componentName);
mRemote.transact(Stub.TRANSACTION_unloadComponent, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public mobilis.api.control.ILocalLoader getComponent(java.lang.String category) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
mobilis.api.control.ILocalLoader _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(category);
mRemote.transact(Stub.TRANSACTION_getComponent, _data, _reply, 0);
_reply.readException();
_result = mobilis.api.control.ILocalLoader.Stub.asInterface(_reply.readStrongBinder());
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
public java.util.List<java.lang.String> getAllNames() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<java.lang.String> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAllNames, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArrayList();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public mobilis.api.control.ILocalLoader getByName(java.lang.String name) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
mobilis.api.control.ILocalLoader _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
mRemote.transact(Stub.TRANSACTION_getByName, _data, _reply, 0);
_reply.readException();
_result = mobilis.api.control.ILocalLoader.Stub.asInterface(_reply.readStrongBinder());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * Callback interfaces
	 */
public void loaded(mobilis.api.control.ILocalLoader component) throws android.os.RemoteException
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
public void addListener(mobilis.api.control.IComponentManagerListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_addListener, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_isLoaded = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_loadComponents = (IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_unloadComponent = (IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_getComponent = (IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_init = (IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getAllNames = (IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getByName = (IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_loaded = (IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_unloaded = (IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_addListener = (IBinder.FIRST_CALL_TRANSACTION + 9);
}
public boolean isLoaded(java.lang.String componentName) throws android.os.RemoteException;
public void loadComponents(java.util.List<java.lang.String> categories, long callId) throws android.os.RemoteException;
public void unloadComponent(java.lang.String componentName) throws android.os.RemoteException;
public mobilis.api.control.ILocalLoader getComponent(java.lang.String category) throws android.os.RemoteException;
public void init(mobilis.api.control.IComponentManagerListener listener) throws android.os.RemoteException;
public java.util.List<java.lang.String> getAllNames() throws android.os.RemoteException;
public mobilis.api.control.ILocalLoader getByName(java.lang.String name) throws android.os.RemoteException;
/**
	 * Callback interfaces
	 */
public void loaded(mobilis.api.control.ILocalLoader component) throws android.os.RemoteException;
public void unloaded(java.lang.String componentName) throws android.os.RemoteException;
public void addListener(mobilis.api.control.IComponentManagerListener listener) throws android.os.RemoteException;
}

/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/hubertfonseca/java/android/workspace/mobilis/src/mobilis/api/IComponent.aidl
 */
package mobilis.api;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
import java.util.List;
public interface IComponent extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements mobilis.api.IComponent
{
private static final java.lang.String DESCRIPTOR = "mobilis.api.IComponent";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an IComponent interface,
 * generating a proxy if needed.
 */
public static mobilis.api.IComponent asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof mobilis.api.IComponent))) {
return ((mobilis.api.IComponent)iin);
}
return new mobilis.api.IComponent.Stub.Proxy(obj);
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
case TRANSACTION_bindService:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
android.os.IBinder _arg1;
_arg1 = data.readStrongBinder();
this.bindService(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_getName:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getName();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_start:
{
data.enforceInterface(DESCRIPTOR);
this.start();
reply.writeNoException();
return true;
}
case TRANSACTION_stop:
{
data.enforceInterface(DESCRIPTOR);
this.stop();
reply.writeNoException();
return true;
}
case TRANSACTION_registerServices:
{
data.enforceInterface(DESCRIPTOR);
this.registerServices();
reply.writeNoException();
return true;
}
case TRANSACTION_registerReceptacles:
{
data.enforceInterface(DESCRIPTOR);
this.registerReceptacles();
reply.writeNoException();
return true;
}
case TRANSACTION_getCategory:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getCategory();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_registerService:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.registerService(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_registerReceptacle:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.registerReceptacle(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_registerDependency:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.registerDependency(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getServiceNames:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<java.lang.String> _arg0;
_arg0 = new java.util.ArrayList<java.lang.String>();
this.getServiceNames(_arg0);
reply.writeNoException();
reply.writeStringList(_arg0);
return true;
}
case TRANSACTION_getReceptacleNames:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<java.lang.String> _arg0;
_arg0 = new java.util.ArrayList<java.lang.String>();
this.getReceptacleNames(_arg0);
reply.writeNoException();
reply.writeStringList(_arg0);
return true;
}
case TRANSACTION_getService:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
android.os.IBinder _result = this.getService(_arg0);
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
case TRANSACTION_getServiceInfo:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
mobilis.api.ServiceInfo _result = this.getServiceInfo(_arg0);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_getReceptacleInfo:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
mobilis.api.ReceptacleInfo _result = this.getReceptacleInfo(_arg0);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_bindReceptacle:
{
data.enforceInterface(DESCRIPTOR);
mobilis.api.ReceptacleInfo _arg0;
if ((0!=data.readInt())) {
_arg0 = mobilis.api.ReceptacleInfo.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
android.os.IBinder _arg1;
_arg1 = data.readStrongBinder();
mobilis.api.ServiceInfo _arg2;
if ((0!=data.readInt())) {
_arg2 = mobilis.api.ServiceInfo.CREATOR.createFromParcel(data);
}
else {
_arg2 = null;
}
this.bindReceptacle(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_getBoundService:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
android.os.IBinder _result = this.getBoundService(_arg0);
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements mobilis.api.IComponent
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
/*
	* Internal
	*/
public void bindService(java.lang.String serviceName, android.os.IBinder service) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(serviceName);
_data.writeStrongBinder(service);
mRemote.transact(Stub.TRANSACTION_bindService, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public java.lang.String getName() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getName, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
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
public void stop() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stop, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/*
	 * Must be implemented by component developer
	 */
public void registerServices() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_registerServices, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void registerReceptacles() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_registerReceptacles, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public java.lang.String getCategory() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCategory, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/*
	 * May be called by component developer
	 */
public void registerService(java.lang.String serviceName, java.lang.String interfaceName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(serviceName);
_data.writeString(interfaceName);
mRemote.transact(Stub.TRANSACTION_registerService, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void registerReceptacle(java.lang.String receptacleName, java.lang.String interfaceName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(receptacleName);
_data.writeString(interfaceName);
mRemote.transact(Stub.TRANSACTION_registerReceptacle, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void registerDependency(java.lang.String componentName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(componentName);
mRemote.transact(Stub.TRANSACTION_registerDependency, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/*
	 * Should be called by component user
	 */
public void getServiceNames(java.util.List<java.lang.String> serviceNames) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getServiceNames, _data, _reply, 0);
_reply.readException();
_reply.readStringList(serviceNames);
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void getReceptacleNames(java.util.List<java.lang.String> receptacleNames) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getReceptacleNames, _data, _reply, 0);
_reply.readException();
_reply.readStringList(receptacleNames);
}
finally {
_reply.recycle();
_data.recycle();
}
}
public android.os.IBinder getService(java.lang.String serviceName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(serviceName);
mRemote.transact(Stub.TRANSACTION_getService, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public mobilis.api.ServiceInfo getServiceInfo(java.lang.String serviceName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
mobilis.api.ServiceInfo _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(serviceName);
mRemote.transact(Stub.TRANSACTION_getServiceInfo, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = mobilis.api.ServiceInfo.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public mobilis.api.ReceptacleInfo getReceptacleInfo(java.lang.String receptacleName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
mobilis.api.ReceptacleInfo _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(receptacleName);
mRemote.transact(Stub.TRANSACTION_getReceptacleInfo, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = mobilis.api.ReceptacleInfo.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public void bindReceptacle(mobilis.api.ReceptacleInfo receptacleInfo, android.os.IBinder service, mobilis.api.ServiceInfo serviceInfo) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((receptacleInfo!=null)) {
_data.writeInt(1);
receptacleInfo.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
_data.writeStrongBinder(service);
if ((serviceInfo!=null)) {
_data.writeInt(1);
serviceInfo.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_bindReceptacle, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public android.os.IBinder getBoundService(java.lang.String receptacleName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(receptacleName);
mRemote.transact(Stub.TRANSACTION_getBoundService, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_bindService = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getName = (IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_start = (IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_stop = (IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_registerServices = (IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_registerReceptacles = (IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getCategory = (IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_registerService = (IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_registerReceptacle = (IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_registerDependency = (IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_getServiceNames = (IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_getReceptacleNames = (IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_getService = (IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_getServiceInfo = (IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_getReceptacleInfo = (IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_bindReceptacle = (IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_getBoundService = (IBinder.FIRST_CALL_TRANSACTION + 16);
}
/*
	* Internal
	*/
public void bindService(java.lang.String serviceName, android.os.IBinder service) throws android.os.RemoteException;
public java.lang.String getName() throws android.os.RemoteException;
public void start() throws android.os.RemoteException;
public void stop() throws android.os.RemoteException;
/*
	 * Must be implemented by component developer
	 */
public void registerServices() throws android.os.RemoteException;
public void registerReceptacles() throws android.os.RemoteException;
public java.lang.String getCategory() throws android.os.RemoteException;
/*
	 * May be called by component developer
	 */
public void registerService(java.lang.String serviceName, java.lang.String interfaceName) throws android.os.RemoteException;
public void registerReceptacle(java.lang.String receptacleName, java.lang.String interfaceName) throws android.os.RemoteException;
public void registerDependency(java.lang.String componentName) throws android.os.RemoteException;
/*
	 * Should be called by component user
	 */
public void getServiceNames(java.util.List<java.lang.String> serviceNames) throws android.os.RemoteException;
public void getReceptacleNames(java.util.List<java.lang.String> receptacleNames) throws android.os.RemoteException;
public android.os.IBinder getService(java.lang.String serviceName) throws android.os.RemoteException;
public mobilis.api.ServiceInfo getServiceInfo(java.lang.String serviceName) throws android.os.RemoteException;
public mobilis.api.ReceptacleInfo getReceptacleInfo(java.lang.String receptacleName) throws android.os.RemoteException;
public void bindReceptacle(mobilis.api.ReceptacleInfo receptacleInfo, android.os.IBinder service, mobilis.api.ServiceInfo serviceInfo) throws android.os.RemoteException;
public android.os.IBinder getBoundService(java.lang.String receptacleName) throws android.os.RemoteException;
}

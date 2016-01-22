package com.mao.android.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.mao.revelator.FieldRevelator;

/**
 * ActivityManager代理
 * 
 * @author mao
 *
 */
public class ActivityManagerProxyer {
	
	/**
	 * 请求代理ActivityManager
	 * 
	 * @param proxy 代理对象
	 * @return 代理成功返回true，失败返回false
	 */
	public static boolean requestActivityManagerProxy(ActivityManagerInvocationHandler handler) {
		try {			
			final ActivityManagerInvocationHandler h = handler;
			Class<?> activityManagerNativeClazz = Class.forName("android.app.ActivityManagerNative");
			Object gDefault = FieldRevelator.getFieldValue(null, activityManagerNativeClazz, "gDefault");
			Class<?> clazz = Class.forName("android.app.IActivityManager");
			final Object preIActivityManager = FieldRevelator.getSuperFieldValue(gDefault, "mInstance");
			Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), 
				new Class<?>[]{clazz}, new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if(h != null) {
							return h.invoke(proxy, preIActivityManager, method, args);
						} else {
							return invokeDefaultProxy(proxy, preIActivityManager, method, args);
						}
					}
				});
			return FieldRevelator.setSuperFieldValue(gDefault, proxy, "mInstance");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//默认代理行为
	private static Object invokeDefaultProxy(Object proxy, Object preIActivityManager, Method method, Object[] args) {
		try {
			return method.invoke(preIActivityManager, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 方法调用拦截监听接口
	 * 
	 * @author mao
	 *
	 */
	public interface ActivityManagerInvocationHandler {
		
		/**
		 * 当特定的方法被调用时会回调该方法
		 * 
		 * @param proxy
		 * @param method
		 * @param args
		 * @return
		 * @throws Throwable
		 */
		Object invoke(Object proxy, Object iActivityManager, Method method, Object[] args) throws Throwable; 
	}
	
}

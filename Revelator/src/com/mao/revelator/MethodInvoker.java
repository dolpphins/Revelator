package com.mao.revelator;

import java.lang.reflect.Method;

public class MethodInvoker {

	public static Object invoke(Method method, Object receiver, Object...args) {
		if(method == null || receiver == null) {
			return null;
		}
		try {
			method.setAccessible(true);
			return method.invoke(receiver, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

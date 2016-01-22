package com.mao.revelator;

public class ClassFinder {

	public static Class<?> getClass(Object object, Class<?> clazz) {
		if(object == null || clazz == null) {
			return null; 
		}
		Class<?> c = object.getClass();
		while(c != null && c != clazz) {
			c = c.getSuperclass();
		}
		return c;
	}
}

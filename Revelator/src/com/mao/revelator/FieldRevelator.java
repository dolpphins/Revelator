package com.mao.revelator;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import android.text.TextUtils;

public class FieldRevelator {

	public static Field getField(Class<?> clazz, String fieldName) {
		if(clazz == null || TextUtils.isEmpty(fieldName)) {
			return null;
		}
		try {
			Field field = null;
			Class<?> temp = clazz;
			while(clazz != null) {
				try {
					field = clazz.getDeclaredField(fieldName);
				} catch (Exception e) {}
				
				if(field != null) {
					if(clazz == temp) {
						break;
					}
					int mod = field.getModifiers();
					if(Modifier.isPublic(mod) || Modifier.isProtected(mod)) {
						break;
					}
				}
				clazz = clazz.getSuperclass();
				field = null;
			}
			if(field == null) {
				return null;
			}
			field.setAccessible(true);
			return field;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object getSuperFieldValue(Object object, String fieldName) {
		if(object == null || TextUtils.isEmpty(fieldName)) {
			return null;
		}
		Class<?> clazz = object.getClass().getSuperclass();
		if(clazz != null) {
			return getFieldValue(object, clazz, fieldName);
		} else {
			return null;
		}
	}
	
	public static Object getFieldValue(Object object, String fieldName) {
		return getFieldValue(object, null, fieldName);
	}
	
	/**
	 * 
	 * 
	 * @param object 为null表示获取静态变量的值
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	public static Object getFieldValue(Object object, Class<?> clazz, String fieldName) {
		if(TextUtils.isEmpty(fieldName)) {
			return null;
		}
		if(clazz == null) {
			clazz = object.getClass();
		}
		Field field = getField(clazz, fieldName);
		if(field == null) {
			return null;
		}
		try {
			return field.get(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 设置父类成员域的值
	 * 
	 * @param object 要进行操作对象，如果为null表示设置静态域
	 * @param value 要设置的值
	 * @param fieldName 要设置成员域的名字
	 * @return
	 */
	public static boolean setSuperFieldValue(Object object, Object value, String fieldName) {
		if(TextUtils.isEmpty(fieldName)) {
			return false;
		}
		Class<?> clazz = object.getClass().getSuperclass();
		if(clazz != null) {
			return setFieldValue(object, value, clazz, fieldName);
		} else {
			return false;
		}
	}
	
	public static boolean setFieldValue(Object object, Object value, String fieldName) {
		return setFieldValue(object, value, null, fieldName);
	}
	
	public static boolean setFieldValue(Object object, Object value, Class<?> clazz, String fieldName) {
		if(TextUtils.isEmpty(fieldName)) {
			return false;
		}
		if(clazz == null && object != null) {
			clazz = object.getClass();
		}
		Field field = getField(clazz, fieldName);
		if(field == null) {
			return false;
		}
		try {
			field.set(object, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static Map<Object, Object> getAllSuperFieldValues(Object obj) {
		if(obj == null) {
			return null;
		}
		Class<?> clazz = obj.getClass().getSuperclass();
		if(clazz != null) {
			return getAllFieldValues(obj, clazz);
		} else {
			return null;
		}
	}
	
	public static Map<Object, Object> getAllFieldValues(Object obj) {
		return getAllFieldValues(obj, null);
	}
	
	public static Map<Object, Object> getAllFieldValues(Object obj, Class<?> clazz) {
		if(obj == null) {
			return null;
		}
		if(clazz == null) {
			clazz = obj.getClass();
		}
		Field[] fields = clazz.getDeclaredFields();
		if(fields == null) {
			return null;
		}
		try {
			Map<Object, Object> fieldMap = new HashMap<Object, Object>();
			for(Field field : fields) {
				if(field != null) {
					field.setAccessible(true);
					fieldMap.put(field.getName(), field.get(obj));
				}
			}
			return fieldMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void printAllFieldValues(Object obj) {
		printAllFieldValues(obj, null);
	}
	
	public static void printAllFieldValues(Object obj, Class<?> clazz) {
		Map<Object, Object> fieldMap = getAllFieldValues(obj, clazz);
		if(fieldMap == null) {
			return;
		}
		Set<Map.Entry<Object, Object>> entrys = fieldMap.entrySet();
		if(entrys == null) {
			return;
		}
		for(Map.Entry<Object, Object> entry : entrys) {
			if(entry != null) {
				System.out.println(entry.getKey() + " " + entry.getValue());
			}
		}
	}
}

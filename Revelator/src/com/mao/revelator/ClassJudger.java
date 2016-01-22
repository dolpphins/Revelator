package com.mao.revelator;

import android.text.TextUtils;

public class ClassJudger {
	
	public static boolean isLoadedApk(Object object) {
		return judge(object, "android.app.LoadedApk");
	}
	
	public static boolean isActivityThread(Object object) {
		return judge(object, "android.app.ActivityThread");
	}
	
	public static boolean isActivityClientRecord(Object object) {
		return judge(object, "android.app.ActivityThread$ActivityClientRecord");
	}
	
	public static boolean isActivity(Object object) {
		return judge(object, "android.app.Activity");
	}
	
	private static boolean judge(Object object, String className) {
		if(object == null || TextUtils.isEmpty(className)) {
			return false;
		}
		if(className.equals(object.getClass().getName())) {
			if(object.getClass().getClassLoader() == ClassLoader.getSystemClassLoader().getParent()) {
				return true;
			}
		}
		return false;
	}
}

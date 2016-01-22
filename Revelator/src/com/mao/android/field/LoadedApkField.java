package com.mao.android.field;

import com.mao.revelator.ClassJudger;
import com.mao.revelator.FieldRevelator;

import android.app.Application;
import android.content.pm.ApplicationInfo;

public class LoadedApkField {

	public static Object getLoadedApkField(Object object, String fieldName) {
		if(ClassJudger.isLoadedApk(object)) {
			return FieldRevelator.getFieldValue(object, fieldName);
		} 
		return null;
	}
	
	public static Object getActivityThread(Object object) {
		if(ClassJudger.isLoadedApk(object)) {
			return FieldRevelator.getFieldValue(object, "mActivityThread");
		}
		return null;
	}
	
	public static ApplicationInfo getApplicationInfo(Object object) {
		if(ClassJudger.isLoadedApk(object)) {
			return (ApplicationInfo) FieldRevelator.getFieldValue(object, "mApplicationInfo");
		}
		return null;
	}
	
	public static Application getApplication(Object object) {
		if(ClassJudger.isLoadedApk(object)) {
			return (Application) FieldRevelator.getFieldValue(object, "mApplication");
		}
		return null;
	}
}

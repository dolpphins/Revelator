package com.mao.android.field;

import com.mao.revelator.ClassFinder;
import com.mao.revelator.FieldRevelator;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.pm.ActivityInfo;
import android.os.IBinder;

public class ActivityField {

	public static Object getActivityField(Activity activity, String fieldName) {
		if(activity == null) {
			return null;
		}
		return FieldRevelator.getFieldValue(activity, ClassFinder.getClass(activity, Activity.class), fieldName);
	}
	
	/**
	 * @param activity
	 * @return 实际返回类型：ActivityThread
	 */
	public static Object getActivityThread(Activity activity) {
		if(activity == null) {
			return null;
		}
		return FieldRevelator.getFieldValue(activity, ClassFinder.getClass(activity, Activity.class), "mMainThread");
	}
	
	public static Instrumentation getActivityInstrumentation(Activity activity) {
		if(activity == null) {
			return null;
		}
		return (Instrumentation) FieldRevelator.getFieldValue(activity, ClassFinder.getClass(activity, Activity.class), "mInstrumentation");
	}
	
	public static IBinder getActivityToken(Activity activity) {
		if(activity == null) {
			return null;
		}
		return (IBinder) FieldRevelator.getFieldValue(activity, ClassFinder.getClass(activity, Activity.class), "mToken");
	}
	
	public static ActivityInfo getActivityInfo(Activity activity) {
		if(activity == null) {
			return null;
		}
		return (ActivityInfo) FieldRevelator.getFieldValue(activity, ClassFinder.getClass(activity, Activity.class), "mActivityInfo");
	}

}

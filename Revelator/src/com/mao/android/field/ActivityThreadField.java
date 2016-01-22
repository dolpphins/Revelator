package com.mao.android.field;

import com.mao.revelator.ClassJudger;
import com.mao.revelator.FieldRevelator;

public class ActivityThreadField {

	public static Object getActivityThreadField(Object object, String fieldName) {
		if(ClassJudger.isActivityThread(object)) {
			return FieldRevelator.getFieldValue(object, fieldName);
		} 
		return null;
	}
	
	/**
	 * @param object 必须是ActivityThread对象
	 * @return 实际返回类型：ArrayMap<IBinder, ActivityClientRecord>
	 */
	public static Object getActivities(Object object) {
		if(ClassJudger.isActivityThread(object)) {
			return FieldRevelator.getFieldValue(object, "mActivities");
		} 
		return null;
	}
	
	/**
	 * @param object 必须是ActivityThread对象
	 * @return 实际返回类型：ArrayMap<IBinder, Service>
	 */
	public static Object getServices(Object object) {
		if(ClassJudger.isActivityThread(object)) {
			return FieldRevelator.getFieldValue(object, "mServices");
		}
		return null;
	}
	
	/**
	 * @param object 必须是ActivityThread对象
	 * @return 实际返回类型：ActivityThread$H
	 */
	public static Object getH(Object object) {
		if(ClassJudger.isActivityThread(object)) {
			return FieldRevelator.getFieldValue(object, "mH");
		}
		return null;
	}
}

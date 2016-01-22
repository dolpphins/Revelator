package com.mao.android.field;

import com.mao.revelator.ClassJudger;
import com.mao.revelator.FieldRevelator;

import android.app.Activity;
import android.text.TextUtils;

public class ActivityClientRecordField {

	/**
	 * getter
	 */

	public static Activity getActivity(Object obj) {
		if(ClassJudger.isActivityClientRecord(obj)) {
			return (Activity) FieldRevelator.getFieldValue(obj, "activity");
		}
		return null;
	}
	
	
	
	
	
	/**
	 * setter 
	 */
	
	public static boolean set(Object obj, Object value, String fieldName) {
		if(ClassJudger.isActivityClientRecord(obj) && !TextUtils.isEmpty(fieldName)) {
			return FieldRevelator.setFieldValue(obj, value, fieldName);
		}
		return false;
	}
	
}

/**
 * Utils.java.java        May 21, 2012
 *
 */
package com.fimtrus.dday.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 
 * 
 * @author jong-hyun.jeong
 */
public class Util {


	/**
	 * Assets에 저장되어있는 데이터를 불러온다.
	 * 
	 * @param resourceName
	 * @return
	 */
	public static String getResourceBundle(String resourceName) {
		ResourceBundle resource = ResourceBundle.getBundle("assets.auth-config");
		String bundle = resource.getString(resourceName);
		return bundle;
	}

	/**
	 * Preference에 저장된 내용들을 모두 지운다.
	 */
	public static void clearPreference(Context context) {
		SharedPreferences pref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.clear();
		editor.commit();
	}

	/**
	 * Preference에 저장된 내용들 얻어온다. Key가 필요하다.
	 * 
	 * @param name
	 *            : Key
	 * @return
	 */
	public static Object getPreference(Context context, String name) {

		SharedPreferences pref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
		Map<String, ?> map = pref.getAll();
		Object obj = map.get(name);

		return obj;
	}
	

	/**
	 * Preference에 데이터를 저장한다.
	 */
	public static void setPreference(Context context, String name, Object value) {

		SharedPreferences pref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();

		if (value instanceof Boolean) {
			editor.putBoolean(name, (Boolean) value);
		} else if (value instanceof Float) {
			editor.putFloat(name, (Float) value);
		} else if (value instanceof Integer) {
			editor.putInt(name, (Integer) value);
		} else if (value instanceof String) {
			editor.putString(name, (String) value);
		} else if (value instanceof Long) {
			editor.putLong(name, (Long) value);
		}
		editor.commit();
	}
	
	public static String getDate(int year, int month, int day) {
		Date date = new Date(year - 1900, month, day);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MMM d일", Locale.KOREAN);
			dateFormat.setTimeZone(TimeZone.getDefault());
			return dateFormat.format(date);
	}
	public static String getTime(int hour, int minute) {
		Date date = new Date(0, 0, 0, hour, minute);
		SimpleDateFormat dateFormat = new SimpleDateFormat("a hh:mm", Locale.KOREAN);
		dateFormat.setTimeZone(TimeZone.getDefault());
		return dateFormat.format(date);
	}
}

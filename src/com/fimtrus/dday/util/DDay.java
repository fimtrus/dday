package com.fimtrus.dday.util;

import java.util.Calendar;

public class DDay {

	public static String getDDay(int year, int month, int date, int hour, int minute) {

		try {
			Calendar today = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			cal.set(year, month, date, hour, minute);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);

			// 2012년 5월 31일 목요일까지 2311일(6년 119일) 남았습니다.
			// System.out.print(sdf.format(cal.getTime()) + " 까지  ");

			// 오늘과 입력된 날짜간의 시간차이를 계산한다.
			long delta = cal.getTimeInMillis() - today.getTimeInMillis();
			
			if (delta < 0) {
				delta = today.getTimeInMillis() - cal.getTimeInMillis();
			}
			
//TODO : reverse 구현.
			cal.setTimeInMillis(delta);
			// Calendar는 1970년 1월 1일 0초를 기준점으로 삼는다.
			
			long mod = (delta % 86400000);

			StringBuilder builder = new StringBuilder();
			builder.append((delta / 86400000) + "일 ");
			builder.append((mod / 3600000) + "시간 ");
			mod = (mod % 3600000);
			builder.append((mod / 60000) + "분 ");
			// mod = (mod % 60000);
			builder.append(cal.get(Calendar.SECOND) + ".");
			// mod = (mod % 1000);
			builder.append((cal.get(Calendar.MILLISECOND) / 100) + "초");
			
			if (delta < 0) {
				return "";
			} else {
				return builder.toString();	
			}
		} catch (NumberFormatException e) {
			System.out.println("java DDay 년 월 일 ");
			return "";
		}
	}
}

package common.util;

import java.util.Calendar;

import common.constant.DateConstant;
import common.constant.DateFormatCD;
import common.util.impl.DateUtilImpl;


/**
 * @author 박윤기
 * @version 1.0 <br/> 
 * <br/> 
 * 날짜 유틸.
 * 대부분 DateFormatCD enum클래스를 파라미터로 받는다.
 *
 */
public class DateUtil {
	
	/**
	 * @param cd 날짜포맷
	 * @return String DateFormatCD에 해당하는 오늘날짜
	 */
	public static String now(DateFormatCD cd) {
		return now(cd, "", "", false);
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param dateDelimeter 날짜 구분문자열
	 * @return String DateFormatCD에 해당하는 오늘날짜(날짜 구분문자열 포함)
	 */
	public static String now(DateFormatCD cd, String dateDelimeter) {
		return now(cd, dateDelimeter, "", false);
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param dateDelimeter 날짜 구분문자열
	 * @param timeDelimeter 시간 구분문자열
	 * @param isBlankDateBetweenTime 날짜와 시간 간격여부
	 * @return String DateFormatCD에 해당하는 오늘날짜(날짜, 시간 구분문자열 포함)
	 */
	public static String now(DateFormatCD cd, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.nowDateStr(cd, dateDelimeter, timeDelimeter, isBlankDateBetweenTime);
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param amount 날짜에 연산되는 수
	 * @return String 현재날짜로부터 amount일만큼 이동된 날짜문자열 (DateFormatCD 포멧에 따른다)
	 */
	public static String addDay(DateFormatCD cd, int amount) {
		return addDay(cd, "NOW", amount, "", "", false);
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param date 날짜문자열
	 * @param amount 날짜에 연산되는 수
	 * @return String 날짜문자열로부터 amount일만큼 이동된 날짜문자열 (DateFormatCD 포멧에 따른다)
	 */
	public static String addDay(DateFormatCD cd, String date, int amount) {
		return addDay(cd, date, amount, "", "", false);
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param date 날짜문자열
	 * @param amount 날짜에 연산되는 수
	 * @param dateDelimeter 날짜 구분문자열
	 * @param timeDelimeter 시간 구분문자열
	 * @param isBlankDateBetweenTime 날짜와 시간 간격여부
	 * @return String 날짜문자열로부터 amount일만큼 이동된 날짜문자열 (날짜, 시간 구분문자열 포함, DateFormatCD 포멧에 따른다)
	 */
	private static String addDay(DateFormatCD cd, String date, int amount, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		String now = date;
		if (now.equals("NOW")) {
			now = dateUtil.nowDateStr(cd);
		}
		return dateUtil.calculateDateStr(cd, now, amount, Calendar.DAY_OF_MONTH, dateDelimeter, timeDelimeter, isBlankDateBetweenTime);
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param amount 날짜에 연산되는 수
	 * @return String 현재날짜로부터 amount월만큼 이동된 날짜문자열 (DateFormatCD 포멧에 따른다)
	 */
	public static String addMonth(DateFormatCD cd, int amount) {
		return addMonth(cd, "NOW", amount, "", "", false);
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param date 날짜문자열
	 * @param amount 날짜에 연산되는 수
	 * @return String 날짜문자열로부터 amount월만큼 이동된 날짜문자열 (DateFormatCD 포멧에 따른다)
	 */
	public static String addMonth(DateFormatCD cd, String date, int amount) {
		return addMonth(cd, date, amount, "", "", false);
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param date 날짜문자열
	 * @param amount 날짜에 연산되는 수
	 * @param dateDelimeter 날짜 구분문자열
	 * @param timeDelimeter 시간 구분문자열
	 * @param isBlankDateBetweenTime 날짜와 시간 간격여부
	 * @return String 날짜문자열로부터 amount월만큼 이동된 날짜문자열 (날짜, 시간 구분문자열 포함, DateFormatCD 포멧에 따른다)
	 */
	public static String addMonth(DateFormatCD cd, String date, int amount, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		String now = date;
		if (now.equals("NOW")) {
			now = dateUtil.nowDateStr(cd);
		}
		return dateUtil.calculateDateStr(cd, now, amount, Calendar.MONTH, dateDelimeter, timeDelimeter, isBlankDateBetweenTime);
	}
	
	
	
	/**
	 * @param cd 날짜포맷
	 * @param amount 날짜에 연산되는 수
	 * @return String 현재날짜로부터 amount년만큼 이동된 날짜문자열 (DateFormatCD 포멧에 따른다)
	 */
	public static String addYear(DateFormatCD cd, int amount) {
		return addYear(cd, "NOW", amount, "", "", false);
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param date 날짜문자열
	 * @param amount 날짜에 연산되는 수
	 * @return String 날짜문자열로부터 amount년만큼 이동된 날짜문자열 (DateFormatCD 포멧에 따른다)
	 */
	public static String addYear(DateFormatCD cd, String date, int amount) {
		return addYear(cd, date, amount, "", "", false);
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param date 날짜문자열
	 * @param amount 날짜에 연산되는 수
	 * @param dateDelimeter 날짜 구분문자열
	 * @param timeDelimeter 시간 구분문자열
	 * @param isBlankDateBetweenTime 날짜와 시간 간격여부
	 * @return String 날짜문자열로부터 amount년만큼 이동된 날짜문자열 (날짜, 시간 구분문자열 포함, DateFormatCD 포멧에 따른다)
	 */
	public static String addYear(DateFormatCD cd, String date, int amount, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		String now = date;
		if (now.equals("NOW")) {
			now = dateUtil.nowDateStr(cd);
		}
		return dateUtil.calculateDateStr(cd, now, amount, Calendar.YEAR, dateDelimeter, timeDelimeter, isBlankDateBetweenTime);
	}
	
	
	/**
	 * @param cd 날짜포맷
	 * @param date1 날짜문자열1
	 * @param date2 날짜문자열2
	 * @return long 두 날짜 간 시간 차이(밀리세컨드)이며 무조건 양수값이 나온다. 
	 * @throws Exception
	 */
	public static long interval(DateFormatCD cd, String date1, String date2) throws Exception{
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.interval(cd, date1, date2);
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param date1 날짜문자열1
	 * @param date2 날짜문자열2
	 * @return int 두 날짜 간 시간 차이(일자)이며 무조건 양수값이 나온다. 
	 * @throws Exception
	 */
	public static int intervalDay(DateFormatCD cd, String date1, String date2) throws Exception{
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.interval(cd, date1, date2, Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param date1 날짜문자열1
	 * @param date2 날짜문자열2
	 * @return int 두 날짜 간 시간 차이(월간)이며 무조건 양수값이 나온다. 
	 * @throws Exception
	 */
	public static int intervalMonth(DateFormatCD cd, String date1, String date2) throws Exception{
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.interval(cd, date1, date2, Calendar.MONTH);
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param date1 날짜문자열1
	 * @param date2 날짜문자열2
	 * @return int 두 날짜 간 시간 차이(년간)이며 무조건 양수값이 나온다.
	 * @throws Exception
	 */
	public static int intervalYear(DateFormatCD cd, String date1, String date2) throws Exception{
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.interval(cd, date1, date2, Calendar.YEAR);
	}
	
	/**
	 * @return int 현재 요일에 대한 숫자
	 * 일요일 1~ 토요일 7
	 */
	public static int getDateName(){
		return getDateName(DateFormatCD.YYYYMMDD, "NOW");
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param date 날짜문자열
	 * @return int 날짜문자열에 대한 요일
	 * 일요일 1~ 토요일 7
	 */
	public static int getDateName(DateFormatCD cd, String date){
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		String now = date;
		if (now.equals("NOW")) {
			now = dateUtil.nowDateStr(cd);
		}
		return dateUtil.getDayName(cd, now);
	}
	
	/**
	 * @return int 현재날짜가 월의 몇번째 주인지에 대한 리턴값
	 */
	public static int getWeekIdx(){
		return getWeekIdx(DateFormatCD.YYYYMMDD, "NOW");
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param date 날짜문자열
	 * @return int 날짜문자열이 월의 몇번째 주인지에 대한 리턴값
	 */
	public static int getWeekIdx(DateFormatCD cd, String date){
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		String now = date;
		if (now.equals("NOW")) {
			now = dateUtil.nowDateStr(cd);
		}
		return dateUtil.getWeekIdx(cd, now);
	}
	
	/**
	 * @return int 현재날짜가 포함된 월의 마지막 주 index를 리턴
	 */
	public static int getLastWeekIdxOfMonth(){
		return getLastWeekIdxOfMonth(DateFormatCD.YYYYMMDD, "NOW");
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param date 날짜문자열
	 * @return int 날짜문자열이 포함된 월의 마지막 주 index를 리턴
	 */
	public static int getLastWeekIdxOfMonth(DateFormatCD cd, String date){
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		String now = date;
		if (now.equals("NOW")) {
			now = dateUtil.nowDateStr(cd);
		}
		return dateUtil.getLastWeekIdxOfMonth(cd, now);
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param weekIdx 주 index
	 * @param dateIdx 요일 index
	 * @return String 현재월의 몇번째 주의 무슨요일을 지정하면 해당하는 날짜값을 리턴
	 */
	public static String getDateFromDateIdx(DateFormatCD cd, int weekIdx, int dateIdx) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.getThisDateFromDateIdx(cd, weekIdx, dateIdx);
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param date1 날짜문자열1
	 * @param date2 날짜문자열2
	 * @return String 두 날짜간 차이를 한글값으로 리턴
	 * @throws Exception
	 */
	public static String intervalKorStr(DateFormatCD cd, String date1, String date2) throws Exception{
		long milsec = interval(cd, date1, date2);
		long sec = (long)((float) milsec / (float) 1000 + 0.5);
		long min = (long)((float) sec / (float) 60 + 0.5);
		long hour =(long)((float) min / (float) 60 + 0.5);
		int day = (int) (hour/24);
		int mon = day/31;
		int year = mon/12;
		
		
		String rslt = "";
		int num = 0;
		if (year > 0) {
			num = intervalYear(cd, date1, date2);
			if (num == 2) {
				rslt = DateConstant.TWO_YEARS_AGO.getDescription();
			} else if (num == 1) {
				rslt = DateConstant.YESTERYEAR.getDescription();
			} else if (num > 5) {
				rslt = DateConstant.A_LONG_TIME_AGO.getDescription();
			} else {
				rslt = DateConstant.YEARS_AGO.getDescription();
			}
			
		} else if (mon > 0) {
			num = mon;
			if (num > 6 && intervalYear(cd, date1, date2) > 0) {
				num = 1;
				rslt = DateConstant.YESTERYEAR.getDescription();
			} else {
				rslt = DateConstant.MONTHS_AGO.getDescription();
			}
			
		} else if (day > 0) {
			num = day;
			if (num > 14 && intervalMonth(cd, date1, date2) > 0) {
				num = 1;
				rslt = DateConstant.ONE_MONTH_AGO.getDescription();
			} else if (num > 14) {
				rslt = DateConstant.WEEKS_AGO.getDescription();
				num = num / 7;
			} else if (num > 7) {
				rslt = DateConstant.ONE_WEEK_AGO.getDescription();
				num = num / 7;
			} else {
				num = intervalDay(cd, date1, date2);
				if (num > 2) {
					rslt = DateConstant.DAYS_AGO.getDescription();
				} else if (num == 2) {
					rslt = DateConstant.DAY_BEFORE_YESTERDAY.getDescription();
				} else {
					rslt = DateConstant.YESTERDAY.getDescription();
				}
			}
				
		} else if (hour > 0) {
			num = (int) hour;
			int intervalDay = intervalDay(cd, date1, date2);
			if (num > 12 && intervalDay > 0) {
				num = day;
				rslt = DateConstant.YESTERDAY.getDescription();
			} else {
				
				rslt = DateConstant.HOURS_AGO.getDescription();
			}
		} else if (min > 0) {
			num = (int) min;
			rslt = DateConstant.MINUTES_AGO.getDescription();
		} else if (sec > 1) {
			num = (int) sec;
			rslt = DateConstant.SECONDS_AGO.getDescription();
		} else {
			num = 0;
			rslt = DateConstant.JUST.getDescription();
		}
		if ( rslt.charAt(0) == '?') {
			rslt = rslt.substring(1);
			rslt = num +" " +rslt;
		}
		return rslt;
	}
	
	/**
	 * @param cd 날짜포맷
	 * @param date 날짜문자열
	 * @return String 날짜문자열을 파싱하여 날짜, 시간 구분문자열("/", ":") 포함하여 리턴
	 */
	public static String parseDate(DateFormatCD cd, String date) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.getDateFormStr(cd, date);
	}
	
	
	/*
	public static String[] addDay(DateFormatCD cd, int amount) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.calculateDate(cd, amount, Calendar.DAY_OF_MONTH);
	}
	
	public static String[] addDay(DateFormatCD cd, String date, int amount) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.calculateDate(cd, date, amount, Calendar.DAY_OF_MONTH);
	}
	
	
	
	public static String[] addMonth(DateFormatCD cd, int amount) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.calculateDate(cd, amount, Calendar.MONTH);
	}
	
	public static String[] addMonth(DateFormatCD cd, String date, int amount) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.calculateDate(cd, date, amount, Calendar.MONTH);
	}
	
	
	
	public static String[] addYear(DateFormatCD cd, int amount) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.calculateDate(cd, amount, Calendar.YEAR);
	}
	
	public static String[] addYear(DateFormatCD cd, String date, int amount) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.calculateDate(cd, date, amount, Calendar.YEAR);
	}
	
	
	
	public static String addDayStr(DateFormatCD cd, int amount) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.calculateDateStr(cd, amount, Calendar.DAY_OF_MONTH, "/", ":", true);
	}
	
	public static String addDayStr(DateFormatCD cd, String date, int amount) {
		return addDayStr(cd, date, amount, "/", ":", true);
	}
	
	public static String addDayStr(DateFormatCD cd, int amount, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.calculateDateStr(cd, amount, Calendar.DAY_OF_MONTH, dateDelimeter, timeDelimeter, isBlankDateBetweenTime);
	}
	
	public static String addDayStr(DateFormatCD cd, String date, int amount, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.calculateDateStr(cd, date, amount, Calendar.DAY_OF_MONTH, dateDelimeter, timeDelimeter, isBlankDateBetweenTime);
	}
	
	
	
	public static String addMonthStr(DateFormatCD cd, int amount) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.calculateDateStr(cd, amount, Calendar.MONTH, "/", ":", true);
	}
	
	public static String addMonthStr(DateFormatCD cd, String date, int amount) {
		return addMonthStr(cd, date, amount, "/", ":", true);
	}
	
	public static String addMonthStr(DateFormatCD cd, int amount, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.calculateDateStr(cd, amount, Calendar.MONTH, dateDelimeter, timeDelimeter, isBlankDateBetweenTime);
	}
	
	public static String addMonthStr(DateFormatCD cd, String date, int amount, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.calculateDateStr(cd, date, amount, Calendar.MONTH, dateDelimeter, timeDelimeter, isBlankDateBetweenTime);
	}
	
	
	
	public static String addYearStr(DateFormatCD cd, int amount) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.calculateDateStr(cd, amount, Calendar.YEAR, "/", ":", true);
	}
	
	public static String addYearStr(DateFormatCD cd, String date, int amount) {
		return addYearStr(cd, date, amount, "/", ":", true);
	}
	
	public static String addYearStr(DateFormatCD cd, int amount, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.calculateDateStr(cd, amount, Calendar.YEAR, dateDelimeter, timeDelimeter, isBlankDateBetweenTime);
	}
	
	public static String addYearStr(DateFormatCD cd, String date, int amount, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.calculateDateStr(cd, date, amount, Calendar.YEAR, dateDelimeter, timeDelimeter, isBlankDateBetweenTime);
	}
	
	
	
	public static String nowDate(DateFormatCD cd) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.nowDateStr(cd);
	}

	public static String nowDate(DateFormatCD cd, String dateDelimeter) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.nowDateStr(cd, dateDelimeter, "", true);
	}
	
	public static String nowDate(DateFormatCD cd, String dateDelimeter, String timeDelimeter) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.nowDateStr(cd, dateDelimeter, timeDelimeter, true);
	}
	
	public static String[] nowDateArr(DateFormatCD cd) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.nowDateArr(cd);
	}
	
	
	
	public static String parseDateStr(DateFormatCD cd, String dateStr) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.getDateFormStr(cd, dateStr);
	}
	
	public static String parseDateStr(DateFormatCD cd, String[] dateStr) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.getDateFormStr(cd, dateStr);
	}
	
	
	
	public static String getDateStr(DateFormatCD cd, int year, int mon, int day, int hour, int min, int sec) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.getDateFormStr(cd, year, mon, day, hour, min, sec);
	}

	
	
	public static int getMonthLastWeek() {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.getThisLastWeekIdxOfMonth();
	}
	
	public static int getMonthLastWeek(DateFormatCD cd, String dateStr) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.getLastWeekIdxOfMonth(cd, dateStr);
	}
	
	
	public static long interval(DateFormatCD cd, String date1, String date2) throws Exception{
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.interval(cd, date1, date2);
	}
	
	public static int intervalDay(DateFormatCD cd, String date1, String date2) throws Exception{
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.intervalDate(cd, date1, date2, Calendar.DAY_OF_MONTH);
	}
	
	public static int intervalMonth(DateFormatCD cd, String date1, String date2) throws Exception{
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.intervalDate(cd, date1, date2, Calendar.MONTH);
	}
	
	public static int intervalYear(DateFormatCD cd, String date1, String date2) throws Exception{
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.intervalDate(cd, date1, date2, Calendar.YEAR);
	}
	
	public static String intervalStr(DateFormatCD cd, String date1, String date2) throws Exception{
		return intervalKorStr(cd, date1, date2);
	}
	
	private static String intervalKorStr(DateFormatCD cd, String date1, String date2) throws Exception{
		long milsec = interval(cd, date1, date2);
		long sec = (long)((float) milsec / (float) 1000 + 0.5);
		long min = (long)((float) sec / (float) 60 + 0.5);
		long hour =(long)((float) min / (float) 60 + 0.5);
		int day = (int) (hour/24);
		int mon = day/31;
		int year = mon/12;
		
		
		String rslt = "";
		int num = 0;
		if (year > 0) {
			num = intervalYear(cd, date1, date2);
			if (num == 2) {
				rslt = DateConstant.TWO_YEARS_AGO.getDescription();
			} else if (num == 1) {
				rslt = DateConstant.YESTERYEAR.getDescription();
			} else if (num > 5) {
				rslt = DateConstant.A_LONG_TIME_AGO.getDescription();
			} else {
				rslt = DateConstant.YEARS_AGO.getDescription();
			}
			
		} else if (mon > 0) {
			num = mon;
			if (num > 6 && intervalYear(cd, date1, date2) > 0) {
				num = 1;
				rslt = DateConstant.YESTERYEAR.getDescription();
			} else {
				rslt = DateConstant.MONTHS_AGO.getDescription();
			}
			
		} else if (day > 0) {
			num = day;
			if (num > 14 && intervalMonth(cd, date1, date2) > 0) {
				num = 1;
				rslt = DateConstant.ONE_MONTH_AGO.getDescription();
			} else if (num > 14) {
				rslt = DateConstant.WEEKS_AGO.getDescription();
				num = num / 7;
			} else if (num > 7) {
				rslt = DateConstant.ONE_WEEK_AGO.getDescription();
				num = num / 7;
			} else {
				num = intervalDay(cd, date1, date2);
				if (num > 2) {
					rslt = DateConstant.DAYS_AGO.getDescription();
				} else if (num == 2) {
					rslt = DateConstant.DAY_BEFORE_YESTERDAY.getDescription();
				} else {
					rslt = DateConstant.YESTERDAY.getDescription();
				}
			}
				
		} else if (hour > 0) {
			num = (int) hour;
			int intervalDay = intervalDay(cd, date1, date2);
			if (num > 12 && intervalDay > 0) {
				num = day;
				rslt = DateConstant.YESTERDAY.getDescription();
			} else {
				
				rslt = DateConstant.HOURS_AGO.getDescription();
			}
		} else if (min > 0) {
			num = (int) min;
			rslt = DateConstant.MINUTES_AGO.getDescription();
		} else if (sec > 1) {
			num = (int) sec;
			rslt = DateConstant.SECONDS_AGO.getDescription();
		} else {
			num = 0;
			rslt = DateConstant.JUST.getDescription();
		}
		if ( rslt.charAt(0) == '?') {
			rslt = rslt.substring(1);
			rslt = num +" " +rslt;
		}
		return rslt;
	}
	
	public static int getDateName(){
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.getThisDayName();
	}
	
	public static int getDateName(DateFormatCD cd, String date){
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.getDayName(cd, date);
	}
	
	public static int getWeekIdx(){
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.getThisWeekIdx();
	}
	public static int getWeekIdx(DateFormatCD cd, String date){
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.getWeekIdx(cd, date);
	}
	
	public static int getLastWeekIdxOfMonth(){
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.getThisLastWeekIdxOfMonth();
	}
	
	public static int getLastWeekIdxOfMonth(DateFormatCD cd, String date){
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.getLastWeekIdxOfMonth(cd, date);
	}
	
	public static String getDateFromDateIdx(DateFormatCD cd, int weekIdx, int dateIdx) {
		DateUtilImpl dateUtil = DateUtilImpl.getInstance();
		return dateUtil.getThisDateFromDateIdx(cd, weekIdx, dateIdx);
	}
	*/
}

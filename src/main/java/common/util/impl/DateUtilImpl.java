package common.util.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import common.constant.DateFormatCD;
import common.util.HNTTrans;


/**
 * @author 박윤기
 * @version 1.0 <br/> 
 * <br/> 
 * 날짜 유틸 구현클래스<br/> 
 * 직접호출하지않고 DateUtil을 통해서 사용된다.<br/> 
 *
 */
public class DateUtilImpl {

	private static final String[] DATE_FORMAT = {
		"yyMM"
		,"yyyyMM"
		,"yyMMdd"
		,"yyyyMMdd"
		,"yyMMddhh"
		,"yyyyMMddhh"
		,"yyMMddHH"
		,"yyyyMMddHH"
		,"yyMMddhhmm"
		,"yyyyMMddhhmm"
		,"yyMMddHHmm"
		,"yyyyMMddHHmm"
		,"yyMMddhhmmss"
		,"yyyyMMddhhmmss"
		,"yyMMddHHmmss"
		,"yyyyMMddHHmmss"
	};
	
	private static Calendar calendar = Calendar.getInstance();
	private static DateUtilImpl singleton = new DateUtilImpl();
	private Date today;
	
	public static DateUtilImpl getInstance() {
		return singleton;
	}
	
	private void initCalendar() {
		today = new Date();
		calendar.setTime(today);
	}

	public String nowDateStr(DateFormatCD cd) {
		initCalendar();
		return makeDateStr(cd, "", "", false);
	}
	
	public String nowDateStr(DateFormatCD cd, String dateDel, String timeDel, boolean isBlankDateBetweenTime) {
		initCalendar();
		return makeDateStr(cd, dateDel, timeDel, isBlankDateBetweenTime);
	}
	
	
	public String calculateDateStr(DateFormatCD cd, String date, int amount, int calenderType, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		initCalendar();
		setCalendar(cd, date);
		if (calendar == null) return null;
		calendar.add(calenderType, amount);
		return makeDateStr(cd, dateDelimeter, timeDelimeter, isBlankDateBetweenTime);
	}
	
	public long interval(DateFormatCD cd, String date1, String date2) throws Exception{
		if (date1.length() != date2.length()) throw new Exception("date type is different");
		
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(new SimpleDateFormat(DATE_FORMAT[cd.idx]).parse(date1));
		calendar2.setTime(new SimpleDateFormat(DATE_FORMAT[cd.idx]).parse(date2));
		return interval(calendar1, calendar2);
	}
	
	public int interval(DateFormatCD cd, String date1, String date2, int dateType) throws Exception{
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(new SimpleDateFormat(DATE_FORMAT[cd.idx]).parse(date1));
		calendar2.setTime(new SimpleDateFormat(DATE_FORMAT[cd.idx]).parse(date2));
		return calculateIntervalCnt(calendar1, calendar2, dateType);
	}
	
	private int calculateIntervalCnt(Calendar calendar1, Calendar calendar2, int dateType) {
		boolean isNegative = false;
		if (calendar2.getTimeInMillis() > calendar1.getTimeInMillis()) {
			isNegative = true;
		}
		switch (dateType) {
		case Calendar.DAY_OF_MONTH :
			calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			break;
		case Calendar.MONTH :
			calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), 1, 0, 0, 0);
			calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), 1, 0, 0, 0);
			break;
		case Calendar.YEAR :
			calendar1.set(calendar1.get(Calendar.YEAR), 0, 1, 0, 0, 0);
			calendar2.set(calendar2.get(Calendar.YEAR), 0, 1, 0, 0, 0);
			break;
		default :
			break;
		}
		int intervalDayCnt = -1;
		if (isNegative) {
			while (calendar2.getTimeInMillis() >= calendar1.getTimeInMillis()) {
				intervalDayCnt++;
				calendar1.add(dateType, 1);
				
			}
		} else {
			while (calendar2.getTimeInMillis() <= calendar1.getTimeInMillis()) {
				intervalDayCnt++;
				calendar2.add(dateType, 1);
			}
		}
		return isNegative ? -1 * intervalDayCnt : intervalDayCnt;
	}
	
	private long interval(Calendar calendar1, Calendar calendar2) {
		if (calendar2.getTimeInMillis() > calendar1.getTimeInMillis()) return -1 * (calendar1.getTimeInMillis() - calendar2.getTimeInMillis());
		return calendar1.getTimeInMillis() - calendar2.getTimeInMillis(); 	
	}
	
	private void setCalendar(DateFormatCD cd, String date) {
		try {
			calendar.setTime(new SimpleDateFormat(DATE_FORMAT[cd.idx]).parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			calendar = null;
		}
	}
	
	private String makeDateStr(DateFormatCD cd, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		String[] dateArr = getDateArrCurrentCalendar(cd);
		return makeDateStrFromArr(cd,dateArr, dateDelimeter,timeDelimeter, isBlankDateBetweenTime);
	}
	
	
	private String makeDateStrFromArr(DateFormatCD cd, String[] dateArr, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		String blankStr = isBlankDateBetweenTime ? " " : "";

		if (dateArr == null) return "";

		String format = cd.getValue();
		String rslt = dateArr[0]+dateDelimeter+dateArr[1];
		
		
		if (format.charAt(3) != '0') {
			rslt += dateDelimeter+dateArr[2];
		}
		
		if (format.charAt(4) != '0') {
			rslt += blankStr+dateArr[3];
		}
		
		if (format.charAt(5) != '0') {
			rslt += timeDelimeter+dateArr[4];
		}
		
		if (format.charAt(6) != '0') {
			rslt += timeDelimeter+dateArr[5];
		}
		
		return rslt;
	}
	
	private String[] getDateArrCurrentCalendar(DateFormatCD cd) {
		String[] dateDataArr = null;
		String format = cd.getValue();
		
		dateDataArr = new String[(int)format.charAt(0)];
		
		switch (format.charAt(1)) {
		case '1':
			dateDataArr[0] = getThisYear(2);
			break;
		case '2':
			dateDataArr[0] = getThisYear(4);
			break;
		default:
			break;
		}
		
		dateDataArr[1] = getThisMonth(2);
		
		if (format.charAt(3) != '0') {
			dateDataArr[2] = getThisDay(2);
		}
		
		if (format.charAt(4) != '0') {
			dateDataArr[3] = getThisHour(2);
		}
		
		if (format.charAt(5) != '0') {
			dateDataArr[4] = getThisMinute(2);
		}
		
		if (format.charAt(6) != '0') {
			dateDataArr[5] = getThisSecond(2);
		}
		
		return dateDataArr;
	}
	
	private String[] makeDateDataCore(DateFormatCD cd, String dateStr) {
		String rslt = dateStr;
		try {
			calendar.setTime(new SimpleDateFormat(DATE_FORMAT[cd.idx]).parse(rslt));
			
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
		return getDateArrCurrentCalendar(cd);
	}
	

	
	private String getThisYear(int minlen) {
		int thisYear = getThisYear();
		if (minlen <3) return HNTTrans.trim(thisYear).substring(2);
		return HNTTrans.trim(thisYear);
	}
	
	private String getThisMonth(int minlen) {
		int thisMonth = getThisMonth();
		if (minlen < 2) return HNTTrans.trim(thisMonth);
		return HNTTrans.padLeftStr(minlen, HNTTrans.trim(thisMonth), '0');
	}
	
	private String getThisDay(int minlen) {
		int thisDay = getThisDay();
		if (minlen < 2) return HNTTrans.trim(thisDay);
		return HNTTrans.padLeftStr(minlen, HNTTrans.trim(thisDay), '0');
	}
	
	private String getThisHour(int minlen) {
		int thisHour = getThisHour();
		if (minlen < 2) return HNTTrans.trim(thisHour);
		return HNTTrans.padLeftStr(minlen, HNTTrans.trim(thisHour), '0');
	}
	
	private String getThisMinute(int minlen) {
		int thisMinute = getThisMinute();
		if (minlen < 2) return HNTTrans.trim(thisMinute);
		return HNTTrans.padLeftStr(minlen, HNTTrans.trim(thisMinute), '0');
	}
	
	private String getThisSecond(int minlen) {
		int thisSecond = getThisSecond();
		if (minlen < 2) return HNTTrans.trim(thisSecond);
		return HNTTrans.padLeftStr(minlen, HNTTrans.trim(thisSecond), '0');
	}
	
	private int getThisYear() {
		return calendar.get(Calendar.YEAR);
	}
	
	private int getThisMonth() {
		return calendar.get(Calendar.MONTH)+1;
	}
	
	private int getThisDay() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	private int getThisHour() {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	private int getThisMinute() {
		return calendar.get(Calendar.MINUTE);
	}
	
	private int getThisSecond() {
		return calendar.get(Calendar.SECOND);
	}
	
	private int getThisDayName() {
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	private int getThisWeekIdx() {
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}
	
	public int getDayName(DateFormatCD cd, String date) {
		setCalendar(cd, date);
		if (calendar == null) return -99;
		return getThisDayName();
	}

	public int getWeekIdx(DateFormatCD cd, String date) {
		setCalendar(cd, date);
		if (calendar == null) return -99;
		return getThisWeekIdx();
	}
	
	public int getThisLastWeekIdxOfMonth() {
		calendar.set(getThisYear(), getThisMonth()-1, calendar.getActualMaximum(Calendar.DATE));
		return getThisWeekIdx();
	}
	
	public int getLastWeekIdxOfMonth(DateFormatCD cd, String date) {
		setCalendar(cd, date);
		if (calendar == null) return -99;
		calendar.set(getThisYear(), getThisMonth()-1, calendar.getActualMaximum(Calendar.DATE));
		return getThisWeekIdx();
	}
	
	public String getThisDateFromDateIdx(DateFormatCD cd, int weekIdx, int dateIdx) {
		initCalendar();
		calendar.set(getThisYear(), getThisMonth()-1, calendar.getActualMinimum(Calendar.DATE));
		int intervalCnt = 0;
		int curDay = getThisDayName();
		
		if ( curDay != dateIdx ) {
			if (curDay>dateIdx) {
				intervalCnt = dateIdx + 7 - curDay;
			} else {
				intervalCnt = dateIdx-curDay;
			}
		}
		if (weekIdx > 1) {
			intervalCnt += 7*(weekIdx-1);
		}

		calendar.add(Calendar.DAY_OF_MONTH, intervalCnt);
		return makeDateStr(cd, "", "", false);
	}
	
	public String getDateFormStr(DateFormatCD cd, String dateTime) {
		return getDateFormStr(cd, dateTime, "/", ":", true);
	}
	
	
	public String getDateFormStr(DateFormatCD cd, String dateTime, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		String [] arr = makeDateDataCore(cd, dateTime);
		return makeDateStrFromArr(cd, arr, dateDelimeter, timeDelimeter, isBlankDateBetweenTime);
	}
	
	/*

	
	public String nowDateStr(DateFormatCD cd, boolean isBlankDateBetweenTime) {
		initCalendar();
		return makeDateStr(cd, "", "", isBlankDateBetweenTime);
	}
	
	public String nowDateStr(DateFormatCD cd, String dateDel, String timeDel, boolean isBlankDateBetweenTime) {
		initCalendar();
		String dateDelimeter = "/";
		String timeDelimeter = ":";
		if (!HNTTrans.trim(dateDel).equalsIgnoreCase("")) {
			dateDelimeter = dateDel; 
		}
		if (!HNTTrans.trim(timeDel).equalsIgnoreCase("")) {
			timeDelimeter = timeDel; 
		}
		return makeDateStr(cd, dateDelimeter, timeDelimeter, isBlankDateBetweenTime);
	}
	
	public String[] nowDateArr(DateFormatCD cd) {
		initCalendar();
		return getDateArrCurrentCalendar(cd);
	}
	
	public String[] calculateDate(DateFormatCD cd, int amount, int calenderType) {
		initCalendar();
		calendar.add(calenderType, amount);
		return getDateArrCurrentCalendar(cd);
	}
	
	public String[] calculateDate(DateFormatCD cd, String date, int amount, int calenderType) {
		initCalendar();
		setCalendar(cd, date);
		if (calendar == null) return null;
		calendar.add(calenderType, amount);
		return getDateArrCurrentCalendar(cd);
	}
	
	public String calculateDateStr(DateFormatCD cd, int amount, int calenderType, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		initCalendar();
		calendar.add(calenderType, amount);
		return makeDateStr(cd, dateDelimeter, timeDelimeter, isBlankDateBetweenTime);
	}
	

	
	public String getDateFormStr(DateFormatCD cd, String dateTime) {
		return getDateFormStr(cd, dateTime, "/", ":", true);
	}
	
	public String getDateFormStr(DateFormatCD cd, String[] dateTime) {
		return getDateFormStr(cd, dateTime, "/", ":", true);
	}
	
	public String getDateFormStr(DateFormatCD cd, int year, int mon, int day, int hour, int min, int sec) {
		return getDateFormStr(cd, year, mon, day, hour, min, sec, "/", ":", true);
	}
	
	public String getDateFormStr(DateFormatCD cd, String dateTime, String dateDelimeter, String timeDelimeter) {
		return getDateFormStr(cd, dateTime, dateDelimeter, timeDelimeter, true);
	}
	
	public String getDateFormStr(DateFormatCD cd, String dateTime, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		String [] arr = makeDateDataCore(cd, dateTime);
		return makeDateStrFromArr(cd, arr, dateDelimeter, timeDelimeter, isBlankDateBetweenTime);
	}
	
	public String getDateFormStr(DateFormatCD cd, String[] dateTime, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		String [] arr = makeDateDataCore(cd, dateTime);
		return makeDateStrFromArr(cd, arr, dateDelimeter, timeDelimeter, isBlankDateBetweenTime);
	}
	
	public String getDateFormStr(DateFormatCD cd, int year, int mon, int day, int hour, int min, int sec, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		String date = year+""+mon+""+day+""+hour+""+min+""+sec;
		try {
			calendar.setTime(new SimpleDateFormat("yyyyMdHms").parse(date));
		} catch (ParseException e) {
			return "";
		}
		
		return makeDateStr(cd, dateDelimeter, timeDelimeter, isBlankDateBetweenTime);
	}
	

	
	private String makeDateStrFromArr(DateFormatCD cd, String[] dateArr, String dateDelimeter, String timeDelimeter, boolean isBlankDateBetweenTime) {
		String blankStr = isBlankDateBetweenTime ? " " : "";

		if (dateArr == null) return "";
		switch(cd) {
		case YYMM:
			return dateArr[0]+dateDelimeter+dateArr[1];
		case YYYYMM:
			return dateArr[0]+dateDelimeter+dateArr[1];
		case YYMMDD:
			return dateArr[0]+dateDelimeter+dateArr[1]+dateDelimeter+dateArr[2];
		case YYYYMMDD:
			return dateArr[0]+dateDelimeter+dateArr[1]+dateDelimeter+dateArr[2];
		case YYMMDDHH24:
			return dateArr[0]+dateDelimeter+dateArr[1]+dateDelimeter+dateArr[2]+blankStr+dateArr[3];
		case YYYYMMDDHH24:
			return dateArr[0]+dateDelimeter+dateArr[1]+dateDelimeter+dateArr[2]+blankStr+dateArr[3];
		case YYMMDDHH24MI:
			return dateArr[0]+dateDelimeter+dateArr[1]+dateDelimeter+dateArr[2]+blankStr+dateArr[3]+timeDelimeter+dateArr[4];
		case YYYYMMDDHH24MI:
			return dateArr[0]+dateDelimeter+dateArr[1]+dateDelimeter+dateArr[2]+blankStr+dateArr[3]+timeDelimeter+dateArr[4];
		case YYMMDDHH24MISS:
			return dateArr[0]+dateDelimeter+dateArr[1]+dateDelimeter+dateArr[2]+blankStr+dateArr[3]+timeDelimeter+dateArr[4]+timeDelimeter+dateArr[5];
		case YYYYMMDDHH24MISS:
			return dateArr[0]+dateDelimeter+dateArr[1]+dateDelimeter+dateArr[2]+blankStr+dateArr[3]+timeDelimeter+dateArr[4]+timeDelimeter+dateArr[5];
		default :
			return "";
		}
	}
	

	
	private String[] makeDateDataCore(DateFormatCD cd, String dateStr) {
		String rslt = "";
		try {
			switch (cd) {
			case YYMM: 
				if (dateStr.length() < 4) return null;
				rslt = dateStr.substring(0,4);
				if (!Pattern.matches("^[0-9]{2}"+MONTH_REG+"$", rslt)) return null;
				break;
			case YYYYMM: 
				if (dateStr.length() < 6) return null;
				rslt = dateStr.substring(0,6);
				if (!Pattern.matches("^(20|19)[0-9]{2}"+MONTH_REG+"$", rslt)) return null;
				break;
			case YYMMDD: 
				if (dateStr.length() < 6) return null;
				rslt = dateStr.substring(0,6);
				if (!Pattern.matches("^[0-9]{2}"+MONTH_REG+DAY_REG+"$", rslt)) return null;
				break;
			case YYYYMMDD: 
				if (dateStr.length() < 8) return null;
				rslt = dateStr.substring(0,8);
				if (!Pattern.matches("^(20|19)[0-9]{2}"+MONTH_REG+DAY_REG+"$", rslt)) return null;
				break;
			case YYMMDDHH24: 
				if (dateStr.length() < 8) return null;
				rslt = dateStr.substring(0,8);
				if (!Pattern.matches("^(20|19)[0-9]{2}"+MONTH_REG+DAY_REG+HOUR_REG+"$", rslt)) return null;
				break;
			case YYMMDDHH24MI: 
				if (dateStr.length() < 10) return null;
				rslt = dateStr.substring(0,10);
				if (!Pattern.matches("^(20|19)[0-9]{2}"+MONTH_REG+DAY_REG+HOUR_REG+MIN_SEC_REG+"$", rslt)) return null;
				break;
			case YYYYMMDDHH24: 
				if (dateStr.length() < 10) return null;
				rslt = dateStr.substring(0,10);
				if (!Pattern.matches("^(20|19)[0-9]{2}"+MONTH_REG+DAY_REG+HOUR_REG+"$", rslt)) return null;
				break;
			case YYYYMMDDHH24MI: 
				if (dateStr.length() < 12) return null;
				rslt = dateStr.substring(0,12);
				if (!Pattern.matches("^(20|19)[0-9]{2}"+MONTH_REG+DAY_REG+HOUR_REG+MIN_SEC_REG+"$", rslt)) return null;
				break;
			case YYMMDDHH24MISS: 
				if (dateStr.length() < 12) return null;
				rslt = dateStr.substring(0,12);
				if (!Pattern.matches("^(20|19)[0-9]{2}"+MONTH_REG+DAY_REG+HOUR_REG+MIN_SEC_REG+MIN_SEC_REG+"$", rslt)) return null;
				break;
			case YYYYMMDDHH24MISS: 
				if (dateStr.length() < 14) return null;
				rslt = dateStr.substring(0,14);
				if (!Pattern.matches("^(20|19)[0-9]{2}"+MONTH_REG+DAY_REG+HOUR_REG+MIN_SEC_REG+MIN_SEC_REG+"$", rslt)) return null;
				break;
			default:
				break;
			}
		
			calendar.setTime(new SimpleDateFormat(DATE_FORMAT[cd.idx]).parse(rslt));
			
		} catch (ParseException e) {
			return null;
		}
		
		return getDateArrCurrentCalendar(cd);
	}
	
	private String[] makeDateDataCore(DateFormatCD cd, String[] dateArr) {
		String rslt = "";
		StringBuilder sb = new StringBuilder();
		try {
			switch (cd) {
			case YYMM: 
				if (dateArr.length < 2) return null;
				if (dateArr[0].length() > 2 ) dateArr[0] = dateArr[0].substring(2);
				sb.append(dateArr[0]).append(dateArr[1]);
				rslt = sb.toString();
				if (!Pattern.matches("^[0-9]{2}"+MONTH_REG+"$", rslt)) return null;
				break;
			case YYYYMM: 
				if (dateArr.length < 2) return null;
				sb.append(dateArr[0]).append(dateArr[1]);
				rslt = sb.toString();
				if (!Pattern.matches("^(20|19)[0-9]{2}"+MONTH_REG+"$", rslt)) return null;
				break;
			case YYMMDD: 
				if (dateArr.length < 3) return null;
				if (dateArr[0].length() > 2 ) dateArr[0] = dateArr[0].substring(2);
				sb.append(dateArr[0]).append(dateArr[1]).append(dateArr[2]);
				rslt = sb.toString();
				if (!Pattern.matches("^[0-9]{2}"+MONTH_REG+DAY_REG+"$", rslt)) return null;
				break;
			case YYYYMMDD: 
				if (dateArr.length < 3) return null;
				sb.append(dateArr[0]).append(dateArr[1]).append(dateArr[2]);
				rslt = sb.toString();
				if (!Pattern.matches("^(20|19)[0-9]{2}"+MONTH_REG+DAY_REG+"$", rslt)) return null;
				break;
			case YYMMDDHH24: 
				if (dateArr.length < 4) return null;
				if (dateArr[0].length() > 2 ) dateArr[0] = dateArr[0].substring(2);
				sb.append(dateArr[0]).append(dateArr[1]).append(dateArr[2]).append(dateArr[3]);
				rslt = sb.toString();
				if (!Pattern.matches("^(20|19)[0-9]{2}"+MONTH_REG+DAY_REG+HOUR_REG+"$", rslt)) return null;
				break;
			case YYYYMMDDHH24: 
				if (dateArr.length < 4) return null;
				sb.append(dateArr[0]).append(dateArr[1]).append(dateArr[2]).append(dateArr[3]);
				rslt = sb.toString();
				if (!Pattern.matches("^(20|19)[0-9]{2}"+MONTH_REG+DAY_REG+HOUR_REG+"$", rslt)) return null;
				break;
			case YYMMDDHH24MI: 
				if (dateArr.length < 5) return null;
				if (dateArr[0].length() > 2 ) dateArr[0] = dateArr[0].substring(2);
				sb.append(dateArr[0]).append(dateArr[1]).append(dateArr[2]).append(dateArr[3]).append(dateArr[4]);
				rslt = sb.toString();
				if (!Pattern.matches("^(20|19)[0-9]{2}"+MONTH_REG+DAY_REG+HOUR_REG+MIN_SEC_REG+"$", rslt)) return null;
				break;
			case YYYYMMDDHH24MI: 
				if (dateArr.length < 5) return null;
				sb.append(dateArr[0]).append(dateArr[1]).append(dateArr[2]).append(dateArr[3]).append(dateArr[4]);
				rslt = sb.toString();
				if (!Pattern.matches("^(20|19)[0-9]{2}"+MONTH_REG+DAY_REG+HOUR_REG+MIN_SEC_REG+"$", rslt)) return null;
				break;
			case YYMMDDHH24MISS: 
				if (dateArr.length < 6) return null;
				if (dateArr[0].length() > 2 ) dateArr[0] = dateArr[0].substring(2);
				sb.append(dateArr[0]).append(dateArr[1]).append(dateArr[2]).append(dateArr[3]).append(dateArr[4]).append(dateArr[5]);
				rslt = sb.toString();
				if (!Pattern.matches("^(20|19)[0-9]{2}"+MONTH_REG+DAY_REG+HOUR_REG+MIN_SEC_REG+MIN_SEC_REG+"$", rslt)) return null;
				break;
			case YYYYMMDDHH24MISS: 
				if (dateArr.length < 6) return null;
				sb.append(dateArr[0]).append(dateArr[1]).append(dateArr[2]).append(dateArr[3]).append(dateArr[4]).append(dateArr[5]);
				rslt = sb.toString();
				if (!Pattern.matches("^(20|19)[0-9]{2}"+MONTH_REG+DAY_REG+HOUR_REG+MIN_SEC_REG+MIN_SEC_REG+"$", rslt)) return null;
				break;
			default:
				break;
			}
		
			calendar.setTime(new SimpleDateFormat(DATE_FORMAT[cd.idx]).parse(rslt));
			
		} catch (ParseException e) {
			return null;
		}
		return getDateArrCurrentCalendar(cd);
	}

	private long interval(Calendar calendar1, Calendar calendar2) {
		if (calendar2.getTimeInMillis() > calendar1.getTimeInMillis()) return -1 * (calendar1.getTimeInMillis() - calendar2.getTimeInMillis());
		return calendar1.getTimeInMillis() - calendar2.getTimeInMillis(); 	
	}
	
	public long interval(DateFormatCD cd, String date) throws Exception{
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(new Date());
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(new SimpleDateFormat(DATE_FORMAT[cd.idx]).parse(date));
		return interval(calendar1, calendar2);
	}
	
	public long interval(DateFormatCD cd, String date1, String date2) throws Exception{
		if (date1.length() != date2.length()) throw new Exception("date type is different");
		
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(new SimpleDateFormat(DATE_FORMAT[cd.idx]).parse(date1));
		calendar2.setTime(new SimpleDateFormat(DATE_FORMAT[cd.idx]).parse(date2));
		return interval(calendar1, calendar2);
	}
	
	public int intervalDate(DateFormatCD cd, String date1, String date2, int dateType) throws Exception{
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(new SimpleDateFormat(DATE_FORMAT[cd.idx]).parse(date1));
		calendar2.setTime(new SimpleDateFormat(DATE_FORMAT[cd.idx]).parse(date2));
		return calculateIntervalCnt(calendar1, calendar2, dateType);
	}
	
	private int calculateIntervalCnt(Calendar calendar1, Calendar calendar2, int dateType) {
		boolean isNegative = false;
		if (calendar2.getTimeInMillis() > calendar1.getTimeInMillis()) {
			isNegative = true;
		}
		switch (dateType) {
		case Calendar.DAY_OF_MONTH :
			calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			break;
		case Calendar.MONTH :
			calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), 1, 0, 0, 0);
			calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), 1, 0, 0, 0);
			break;
		case Calendar.YEAR :
			calendar1.set(calendar1.get(Calendar.YEAR), 0, 1, 0, 0, 0);
			calendar2.set(calendar2.get(Calendar.YEAR), 0, 1, 0, 0, 0);
			break;
		default :
			break;
		}
		int intervalDayCnt = -1;
		if (isNegative) {
			while (calendar2.getTimeInMillis() >= calendar1.getTimeInMillis()) {
				intervalDayCnt++;
				calendar1.add(dateType, 1);
				
			}
		} else {
			while (calendar2.getTimeInMillis() <= calendar1.getTimeInMillis()) {
				intervalDayCnt++;
				calendar2.add(dateType, 1);
			}
		}
		return isNegative ? -1 * intervalDayCnt : intervalDayCnt;
	}
	

	
	private void setCalendar(DateFormatCD cd, String date) {
		try {
			calendar.setTime(new SimpleDateFormat(DATE_FORMAT[cd.idx]).parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			calendar = null;
		}
	}
	

	
	public int getDayName(DateFormatCD cd, String date) {
		setCalendar(cd, date);
		if (calendar == null) return -99;
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	public int getThisWeekIdx() {
		initCalendar();
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}
	
	public int getWeekIdx(DateFormatCD cd, String date) {
		setCalendar(cd, date);
		if (calendar == null) return -99;
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}
	
	public int getThisLastWeekIdxOfMonth() {
		initCalendar();
		calendar.set(getThisYear(), getThisMonth()-1, calendar.getActualMaximum(Calendar.DATE));
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}
	
	public int getLastWeekIdxOfMonth(DateFormatCD cd, String date) {
		setCalendar(cd, date);
		if (calendar == null) return -99;
		calendar.set(getThisYear(), getThisMonth()-1, calendar.getActualMaximum(Calendar.DATE));
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}

	
	public String getThisDateFromDateIdx(DateFormatCD cd, int weekIdx, int dateIdx) {
		initCalendar();
		calendar.set(getThisYear(), getThisMonth()-1, calendar.getActualMinimum(Calendar.DATE));
		int intervalCnt = 0;
		int curDay = calendar.get(Calendar.DAY_OF_WEEK);
		
		if ( curDay != dateIdx ) {
			if (curDay>dateIdx) {
				intervalCnt = dateIdx + 7 - curDay;
			} else {
				intervalCnt = dateIdx-curDay;
			}
		}
		if (weekIdx > 1) {
			intervalCnt += 7*(weekIdx-1);
		}

		calendar.add(Calendar.DAY_OF_MONTH, intervalCnt);
		return makeDateStr(cd, "", "", false);
	}
	

	*/
	/*
	public DayNameCD getFirstDayName() {
		calendar.
	}
	*/
}

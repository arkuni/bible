package common.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * @author 박윤기
 * @version 1.0 <br/> 
 * <br/> 
 * 문자열처리 및 변환 유틸<br>
 */
public class HNTTrans {
	public static String trim(String txt) {
		if (txt == null) return "";
		return txt.trim();
	}
	/**
	 * 널처리 + 좌우공백제거<br>
	 * @param txt
	 * @param alter
	 * @return trim처리된 문자열
	 */
	public static String trim(String txt, String alter) {
		String rst = "";
		if (txt == null) return alter;
		rst = txt.trim();
		if (rst.equals("")) return alter;
		return rst;
	}
	/**
	 * int->String 형변환<br>
	 * @param no
	 * @return 형변환된 문자열
	 */
	public static String trim(int no) {
		return Integer.toString(no);
	}
	/**
	 * char->String 형변환<br>
	 * @param ch
	 * @return 형변환된 문자열
	 */
	public static String trim(char ch) {
		return String.valueOf(ch);
	}
	/**
	 * long->String 형변환<br>
	 * @param no
	 * @return 형변환된 문자열
	 */
	public static String trim(long no) {
		return Long.toString(no);
	}
	/**
	 * float->String 형변환<br>
	 * @param no
	 * @return 형변환된 문자열
	 */
	public static String trim(float no) {
		return Float.toString(no);
	}
	/**
	 * double->String 형변환<br>
	 * @param no
	 * @return 형변환된 문자열
	 */
	public static String trim(double no) {
		return Double.toString(no);
	}
	/**
	 * object->String 형변환<br>
	 * @param obj
	 * @return 형변환된 문자열
	 */
	public static String trim(Object obj) {
		if (obj == null) return "";
		return trim(obj.toString());
	}
	/**
	 * String->int 형변환<br>
	 * @param txt
	 * @return 형변환된 정수
	 */
	public static int toI(String txt) {
		if (trim(txt).equals("")) return 0;
		return toICore(txt, 0);
	}
	/**
	 * String->int 형변환<br>
	 * @param txt
	 * @param num
	 * @return 형변환된 정수
	 */
	public static int toI(String txt, int num) {
		if (trim(txt).equals("")) return num;
		return toICore(txt, num);
	}
	private static int toICore(String txt, int num) {
		int rst = 0;
		try {
			rst = Integer.parseInt(txt.replace(",", ""));
		} catch (Exception e) {
			return num;
		}
		return rst;
	}
	/**
	 * float->int 형변환<br>
	 * @param noF
	 * @return 형변환된 정수
	 */
	public static int toI(float noF) {
		return (int)noF;
	}
	/**
	 * double->int 형변환<br>
	 * @param noD
	 * @return 형변환된 정수
	 */
	public static int toI(double noD) {
		return (int)noD;
	}
	/**
	 * long->int 형변환<br>
	 * @param noL
	 * @return 형변환된 정수
	 */
	public static int toI(long noL) {
		return (int)noL;
	}
	/**
	 * Object->int 형변환<br>
	 * @param obj
	 * @return 형변환된 정수
	 */
	public static int toI(Object obj) {
		return toI(obj, 0);
	}
	
	/**
	 * String->int 형변환<br>
	 * @param obj
	 * @param num
	 * @return 형변환된 정수
	 */
	public static int toI(Object obj, int num) {
		if (obj == null) return num;
		if (obj instanceof Integer) return ((Integer) obj).intValue();
		if (obj instanceof Long) return ((Long) obj).intValue();
		if (obj instanceof Float) return ((Float) obj).intValue();
		if (obj instanceof Double) return ((Double) obj).intValue();
		if (obj instanceof String) toICore(trim(obj), num);
		if (obj instanceof BigDecimal) return ((BigDecimal)obj).intValue();
		return num;
	}
	/**
	 * String->float 형변환<br>
	 * @param txt
	 * @return 형변환된 float
	 */
	public static float toF(String txt) {
		if (trim(txt).equals("")) return 0f;
		return toFCore(txt, 0f);
	}
	/**
	 * String->float 형변환<br>
	 * @param txt
	 * @param num
	 * @return 형변환된 float
	 */
	public static float toF(String txt, float num) {
		if (trim(txt).equals("")) return num;
		return toFCore(txt, num);
	}
	private static float toFCore(String txt, float num) {
		float rst = 0f;
		try {
			rst = Float.parseFloat(txt.replace(",", ""));
		} catch (Exception e) {
			return num;
		}
		return rst;
	}
	/**
	 * int->float 형변환<br>
	 * @param noI
	 * @return 형변환된 float
	 */
	public static float toF(int noI) {
		return (float)noI;
	}
	/**
	 * double->float 형변환<br>
	 * @param noD
	 * @return 형변환된 float
	 */
	public static float toF(double noD) {
		return (float)noD;
	}
	/**
	 * long->float 형변환<br>
	 * @param noL
	 * @return 형변환된 float
	 */
	public static float toF(long noL) {
		return (float)noL;
	}
	/**
	 * Object->float 형변환<br>
	 * @param obj
	 * @return 형변환된 float
	 */
	public static float toF(Object obj) {
		float rst = toF(trim(obj));
		return rst;
	}
	
	/**
	 * String->double 형변환<br>
	 * @param txt
	 * @return 형변환된 double
	 */
	public static double toD(String txt) {
		if (trim(txt).equals("")) return 0d;
		return toDCore(txt, 0d);
	}
	/**
	 * String->double 형변환<br>
	 * @param txt
	 * @param num
	 * @return 형변환된 double
	 */
	public static double toD(String txt, double num) {
		if (trim(txt).equals("")) return num;
		return toDCore(txt, num);
	}
	private static double toDCore(String txt, double num) {
		double rst = 0d;
		try {
			rst = Double.parseDouble(txt.replace(",", ""));
		} catch (Exception e) {
			return num;
		}
		return rst;
	}
	/**
	 * int->double 형변환<br>
	 * @param noI
	 * @return 형변환된 double
	 */
	public static double toD(int noI) {
		return (double)noI;
	}
	/**
	 * float->double 형변환<br>
	 * @param noD
	 * @return 형변환된 double
	 */
	public static double toD(float noD) {
		return (double)noD;
	}
	/**
	 * long->double 형변환<br>
	 * @param noL
	 * @return 형변환된 double
	 */
	public static double toD(long noL) {
		double rst = (double)noL;
		return rst;
	}
	/**
	 * Object->double 형변환<br>
	 * @param obj
	 * @return 형변환된 double
	 */
	public static double toD(Object obj) {
		double rst = toD(trim(obj));
		return rst;
	}
	
	/**
	 * String->long 형변환<br>
	 * @param txt
	 * @return 형변환된 long
	 */
	public static long toL(String txt) {
		if (trim(txt).equals("")) return 0l;
		return toLCore(txt, 0l);
	}
	/**
	 * String->long 형변환<br>
	 * @param txt
	 * @param num
	 * @return 형변환된 long
	 */
	public static long toL(String txt, long num) {
		if (trim(txt).equals("")) return num;
		return toLCore(txt, num);
	}
	private static long toLCore(String txt, long num) {
		long rst = 0l;
		try {
			rst = Long.parseLong(txt.replace(",", ""));
		} catch (Exception e) {
			return num;
		}
		return rst;
	}
	/**
	 * int->long 형변환<br>
	 * @param noI
	 * @return 형변환된 long
	 */
	public static long toL(int noI) {
		return (long)noI;
	}
	/**
	 * float->long 형변환<br>
	 * @param noF
	 * @return 형변환된 long
	 */
	public static long toL(float noF) {
		return (long)noF;
	}
	/**
	 * double->long 형변환<br>
	 * @param noD
	 * @return 형변환된 long
	 */
	public static long toL(double noD) {
		long rst = (long)noD;
		return rst;
	}
	/**
	 * Object->long 형변환<br>
	 * @param obj
	 * @return 형변환된 long
	 */
	public static long toL(Object obj) {
		long rst = Long.parseLong(trim(obj));
		return rst;
	}
	
	
	
	private static boolean toBCore(String txt, boolean bool) {
		boolean rst = false;
		try {
			if ( trim(txt).equals("1") ||  trim(txt).toLowerCase().equals("true") ||  trim(txt).toLowerCase().equals("y") ) {
				rst = true;
			} else if ( trim(txt).equals("0") ||  trim(txt).toLowerCase().equals("false") ||  trim(txt).toLowerCase().equals("n") ){
				rst = false;
			} else {
				return bool;
			}
		} catch (Exception e) {
			return bool;
		}
		return rst;
	}
	/**
	 * String->boolean 형변환<br>
	 * "1", "true", "y" : true<br/>
	 * 그 외 false<br/>
	 * @param txt
	 * @return T/F
	 */
	public static boolean toB(String txt) {
		if (trim(txt).equals("")) return false;
		return toBCore(txt, false);
	}
	/**
	 * String->boolean 형변환<br>
	 * @param txt
	 * @param bool
	 * @return T/F
	 */
	public static boolean toB(String txt, boolean bool) {
		if (trim(txt).equals("")) return bool;
		return toBCore(txt, bool);
	}
	/**
	 * int->boolean 형변환<br>
	 * 0이상이면 true<br/>
	 * @param noI
	 * @return T/F
	 */
	public static boolean toB(int noI) {
		return noI > 0 ? true : false;
	}
	/**
	 * float->boolean 형변환<br>
	 * 0이상이면 true<br/>
	 * @param noD
	 * @return T/F
	 */
	public static boolean toB(float noD) {
		return noD > 0 ? true : false;
	}
	/**
	 * long->boolean 형변환<br>
	 * 0이상이면 true<br/>
	 * @param noL
	 * @return T/F
	 */
	public static boolean toB(long noL) {
		return noL > 0 ? true : false;
	}
	/**
	 * Object->boolean 형변환<br>
	 * "1", "true", "y" : true<br/>
	 * 그 외 false<br/>
	 * @param obj
	 * @return T/F
	 */
	public static boolean toB(Object obj) {
		return toBCore(trim(obj),false);
	}
	
	/**
	 * 왼쪽패딩<br/>
	 * @param fixedLength
	 * @param curStr
	 * @param padStr
	 * @return 패딩처리된 문자열
	 */
	public static String padLeftStr(int fixedLength, String curStr, char padStr) {
		String rslt = "";
		if (trim(curStr).equals("")) return curStr;
		if (fixedLength < 1) return curStr;
		if (fixedLength <= curStr.length()) return curStr;
		if (fixedLength-curStr.length() < 2 ) return padStr+curStr;
		
		StringBuilder stringbuilder = new StringBuilder();
		int j = 0;
		int i = fixedLength-curStr.length()-1;
		do {
			stringbuilder.append(padStr);
			if (j == i) break;
			j++;
		} while (true);
		
		stringbuilder.append(curStr);
		
		rslt = stringbuilder.toString();
		
		return rslt;
	}
	
	/**
	 * 왼쪽패딩<br/>
	 * @param fixedLength
	 * @param num
	 * @param padStr
	 * @return 패딩처리된 문자열
	 */
	public static String padLeftStr(int fixedLength, int num, char padStr) {
		String rslt = "";
		String curStr = trim(num);
		rslt = padLeftStr(fixedLength, curStr, padStr);
		return rslt;
	}
	
	/**
	 * 왼쪽패딩<br/>
	 * @param fixedLength
	 * @param num
	 * @param padStr
	 * @return 패딩처리된 문자열
	 */
	public static String padLeftStr(int fixedLength, long num, char padStr) {
		String rslt = "";
		String curStr = trim(num);
		rslt = padLeftStr(fixedLength, curStr, padStr);
		return rslt;
	}
	
	/**
	 * 왼쪽패딩<br/>
	 * @param fixedLength
	 * @param num
	 * @param padStr
	 * @return 패딩처리된 문자열
	 */
	public static String padLeftStr(int fixedLength, double num, char padStr) {
		String rslt = "";
		String curStr = trim(num);
		rslt = padLeftStr(fixedLength, curStr, padStr);
		return rslt;
	}
	
	/**
	 * 왼쪽패딩<br/>
	 * @param fixedLength
	 * @param num
	 * @param padStr
	 * @return 패딩처리된 문자열
	 */
	public static String padLeftStr(int fixedLength, float num, char padStr) {
		String rslt = "";
		String curStr = trim(num);
		rslt = padLeftStr(fixedLength, curStr, padStr);
		return rslt;
	}
	
	/**
	 * 오른쪽패딩<br/>
	 * @param fixedLength
	 * @param curStr
	 * @param padStr
	 * @return 패딩처리된 문자열
	 */
	public static String padRightStr(int fixedLength, String curStr, char padStr) {
		String rslt = "";
		if (trim(curStr).equals("")) return curStr;
		if (fixedLength < 1) return curStr;
		if (fixedLength <= curStr.length()) return curStr;
		if (fixedLength-curStr.length() < 2 ) return curStr+padStr;
		
		StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append(curStr);
		int j = 0;
		int i = fixedLength-curStr.length()-1;
		do {
			stringbuilder.append(padStr);
			if (j == i) break;
			j++;
		} while (true);
		rslt = stringbuilder.toString();
		return rslt;
	}
	
	private static String comma(String str, boolean isChecked) {
		String sign = "";
		String point = "";
		String decimal = "";
		
		if (!isChecked) comma(str);
		if (str.charAt(0) == '-' || str.charAt(0) == '+') {
			sign = trim(str.charAt(0));
			str = str.substring(1);
		}
		
		if (str.indexOf(".") > -1) {
			decimal = str.substring(0, str.indexOf("."));
			point = str.substring(str.indexOf(".")+1);
		} else {
			decimal = str;
		}
		
		char[] tmpArr = decimal.toCharArray();
		StringBuilder sb = new StringBuilder();
		int remain = tmpArr.length % 3;
		int quota = (int) (tmpArr.length / 3);
		if (remain > 0) {
			if (remain < 2) sb.append(tmpArr[0]).append(",");
			else if (remain < 3) sb.append(tmpArr[0]).append(tmpArr[1]).append(",");
		}
		
		for ( int i=0; i<quota; i++) {
			if ( i>0 ) sb.append(",");
			sb.append(tmpArr[(i*3)+remain]).append(tmpArr[(i*3)+remain+1]).append(tmpArr[(i*3)+remain+2]);
		}
		
		if (str.indexOf(".") > -1) {
			sb.append(".").append(point);
		}
		
		sb.insert(0,sign);
		return sb.toString();
	}
	
	/**
	 * 금액표시 관련 콤마<br/>
	 * @param str
	 * @return 콤마처리된 문자열
	 */
	public static String comma(String str) {
		String rslt = "";
		str = trim(str);
		double val = 0d;
		if (!Pattern.matches("^(\\-|\\+)?[0-9]+(\\.[0-9]+)?$", str)) return str;
		if ( str.length() < 4) return str;
		try {
			val = Double.parseDouble(str);
		}catch(NumberFormatException e){
			return str;
		}
		
		if (val < 1000 && val > 0) return str;
		if (val > -1000 && val < 0) return str;
		
		rslt = comma(str, true);
		return rslt;
	}
	
	/**
	 * 금액표시 관련 콤마<br/>
	 * @param no
	 * @return 콤마처리된 문자열
	 */
	public static String comma(int no) {
		String rslt = "";
		String str = trim(no);
		if (no < 1000) return str;
		rslt= comma(str, true);
		return rslt;
	}
	
	/**
	 * 금액표시 관련 콤마<br/>
	 * @param no
	 * @return 콤마처리된 문자열
	 */
	public static String comma(double no) {
		String rslt = "";
		String str = trim(no);
		if (no < 1000) return str;
		rslt= comma(str, true);
		return rslt;
	}
	
	/**
	 * 금액표시 관련 콤마<br/>
	 * @param no
	 * @return 콤마처리된 문자열
	 */
	public static String comma(float no) {
		String rslt = "";
		String str = trim(no);
		if (no < 1000) return str;
		rslt= comma(str, true);
		return rslt;
	}
	
	/**
	 * 금액표시 관련 콤마<br/>
	 * @param no
	 * @return 콤마처리된 문자열
	 */
	public static String comma(long no) {
		String rslt = "";
		String str = trim(no);
		if (no < 1000) return str;
		rslt= comma(str, true);
		return rslt;
	}
	
	/**
	 * 오라클 decode함수 구현<br/>
	 * @param base
	 * @param compare
	 * @param val1
	 * @param val2
	 * @return 문자열
	 */
	public static String decode(String base, String compare, String val1, String val2) {
		if (base.equals(compare)) return val1;
		return val2;
		
	}

	/**
	 * 오라클 decode함수 구현(파라미터 무한)<br/>
	 * @param base
	 * @param compare
	 * @param val1
	 * @param compares
	 * @return 문자열
	 */
	public static String decode(String base, String compare, String val1, String...compares) {
		if(compares.length < 1 || compares.length%2 != 1) return "";
		if (base.equals(compare)) return val1;
		int i=0;
		while(i<compares.length) {
			if(base.equals(compares[i++])) return compares[i++];
		}
		return compares[compares.length-1];
	}
}

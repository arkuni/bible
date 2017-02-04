package common.util;


/**
 * @author 박윤기
 * @version 1.0 <br/>
 * <br/>
 * 태그에 관련된 웹유틸<br>
 * selected : 해당하는 값을 select하는 메서드<br>
 * checked : 해당하는 값을 checked하는 메서드<br>
 * readOnly<br>
 * disabled<br>
 * centerMasking : 가운데 마스킹<br>
 * firstMasking : 앞부분 마스킹<br>
 * endMasking : 뒷부분 마스킹<br>
 * textToHTML : 문자열을 태그로 변환<br>
 * htmlToText : 태그를 문자열로 변환<br>
 * displayTag : 태그실행 방지(태그가 그대로 보이도록)<br>
 *
 */
public class WebUtil {
	public static String selected(String org, String dest) {
		return org.equals(dest) ? "selected" :"";
	}

	public static String checked(String org, String dest) {
		return org.equals(dest) ? "checked" :"";
	}

	public static String checked(String[] org, String dest) {
		if (org == null || org.length < 1) return "";
		for (String val : org) {
			if (val.equals(dest)) {
				return "checked";
			}
		}
		return "";
	}

	public static String readOnly(boolean readonly) {
		return readonly ? "readonly" : "";
	}

	public static String disabled(boolean disabled) {
		return disabled ? "disabled" : "";
	}

	public static String readOnly(String org, String dest) {
		return org.equals(dest) ? "readonly" : "";
	}

	public static String disabled(String org, String dest) {
		return org.equals(dest) ? "disabled" : "";
	}

	public static String readOnly(WebProperties obj) {
		if(obj.isReadOnly()) return "readonly";
		return "";
	}

	public static String disabled(WebProperties obj) {
		if(obj.isDisabled()) return "disabled";
		return "";
	}

	public static String reduceStr(String org, int size, int oneCharWeight) {
		final String end = "...";
		final int thinCharWeightVal = 1;
		final int engCharWeightVal = 2;
		final int korCharWeightVal = 4;

		int totalSum = 0;
		String rtn = "";
		if (org == null || org.trim().equals("") || org.length() < 1) return rtn;

		char [] tmpArr = org.toCharArray();
		for (int i=0; i<tmpArr.length; i++) {

			switch (Character.getType(tmpArr[i])) {
			case Character.OTHER_LETTER :
				totalSum += korCharWeightVal * oneCharWeight;
				break;
			case Character.LETTER_NUMBER :

				break;
			case Character.LOWERCASE_LETTER :
				if (tmpArr[i] == 'i' || tmpArr[i] == 'l') {
					totalSum += thinCharWeightVal * oneCharWeight;
				} else {
					totalSum += engCharWeightVal * oneCharWeight;
				}
				break;
			case Character.UPPERCASE_LETTER :
				if (tmpArr[i] == 'I') {
					totalSum += thinCharWeightVal * oneCharWeight;
				} else {
					totalSum += engCharWeightVal * oneCharWeight;
				}
				break;
			case Character.OTHER_SYMBOL :
				totalSum += korCharWeightVal * oneCharWeight;
				break;
			default :
				if (tmpArr[i] == '|' || tmpArr[i] == '!' || tmpArr[i] == ';' || tmpArr[i] == ':' || tmpArr[i] == '\'' ||
					tmpArr[i] == ',' || tmpArr[i] == '.' || tmpArr[i] == '`' || tmpArr[i] == '.' || tmpArr[i] == '/' ) {
					totalSum += thinCharWeightVal * oneCharWeight;
				} else {
					totalSum += engCharWeightVal * oneCharWeight;
				}
			}
			rtn += String.valueOf(tmpArr[i]);
			if (totalSum >= size && i < tmpArr.length-1) {
				rtn += end;
				break;
			}
		}
		return rtn;
	}

	public static String centerMasking(String org, int cnt) {
		if (org == null || org.trim().equals("")) return org;
		if (org.length() > 30000) return org;
		if (cnt < 1) return org;
		StringBuilder sb = new StringBuilder();
		if (org.length() <= cnt) {
			for(int i=0;i<cnt;i++) {
				sb.append('*');
			}
			return sb.toString();
		}
		if (org.length() < 3) return org.charAt(0)+"*";

		short len = (short) org.length();
		short firstEnd = 0;
		boolean isOdd = false;
		if ((len-cnt)%2 !=0) isOdd = true;
		firstEnd = (short)((len-cnt)/2);
		if (isOdd) firstEnd++;
		String firstStr = org.substring(0, firstEnd);
		sb.append(firstStr);
		for(int i=0;i<cnt;i++) {
			sb.append('*');
		}
		sb.append(org.substring(firstEnd+cnt));
		return sb.toString();
	}

	public static String firstMasking(String org, int cnt) {
		if (org == null || org.trim().equals("")) return org;
		if (org.length() > 30000) return org;
		if (cnt < 1) return org;
		StringBuilder sb = new StringBuilder();
		if (org.length() <= cnt) {
			for(int i=0;i<cnt;i++) {
				sb.append('*');
			}
			return sb.toString();
		}
		if (org.length() < 3) return "*"+org.charAt(0);

		for(int i=0;i<cnt;i++) {
			sb.append('*');
		}
		sb.append(org.substring(cnt));
		return sb.toString();
	}

	public static String endMasking(String org, int cnt) {
		if (org == null || org.trim().equals("")) return org;
		if (org.length() > 30000) return org;
		if (cnt < 1) return org;
		StringBuilder sb = new StringBuilder();
		if (org.length() <= cnt) {
			for(int i=0;i<cnt;i++) {
				sb.append('*');
			}
			return sb.toString();
		}
		if (org.length() < 3) return org.charAt(0)+"*";
		sb.append(org.substring(0, org.length()-cnt));
		for(int i=0;i<cnt;i++) {
			sb.append('*');
		}
		return sb.toString();
	}

	public static String textToHTML(String data) {
		String val = data;
		val = val.replace("\r\n", "<br/>");
		val = val.replace("\r", "<br/>");
		val = val.replace("\n", "<br/>");
		val = val.replace("\t", "	");
		val = val.replace(" ", "&nbsp;");
		return val;
	}

	public static String htmlToText(String data) {
		String val = data;
		val = val.replace("<br/>", "\n");
		val = val.replace("&nbsp;", " ");
		return val;
	}

	public static String displayTag(String data) {
		String val = data;
		val = val.replace("&", "&amp;");
		val = val.replace("<", "&lt;");
		val = val.replace(">", "&gt;");
		val = val.replace("\"", "&quot;");
		return val;
	}
}

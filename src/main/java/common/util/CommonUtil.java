package common.util;


/**
 * @author 박윤기
 * @version 1.0 <br/> 
 * <br/> 
 * 기타유틸을 정리한 클래스.
 *
 */
public class CommonUtil {
	/**
	 * @param e 예외
	 * @param level
	 * @param userClass
	 * @return
	 * 
	 * 화면에 예외로그를 출력하는 유틸이다.<br/>
	 * printStackTrace에서 몇줄을 찍을 것인지, 사용자클래스만 찍을것인지 결정한다.<br/>
	 * 
	 */
	public static String exceptionLog(Exception e, int level, boolean userClass) {
		String msg = e.getMessage();
		StackTraceElement[] st = e.getStackTrace();
		if(userClass) {
			for (int i = 0; i<st.length && i<level; i++) {
				StackTraceElement el = st[i];
				if (el.getClassName().indexOf("com.hnt.") == 0 || el.getClassName().indexOf("common.") == 0) {
					msg += "\n\t - " + (i+1) + ") " + el.getClassName() + "(" + el.getFileName() + ":" + el.getLineNumber() + ")";
				} else {
					continue;
				}
			}
		} else {
			for (int i = 0; i<st.length && i<level; i++) {
				StackTraceElement el = st[i];
				msg += "\n\t - " + (i+1) + ") " + el.getClassName() + "(" + el.getFileName() + ":" + el.getLineNumber() + ")";
			}
		}
		return msg;
	}
}

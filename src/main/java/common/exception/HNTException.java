package common.exception;

/**
 * @author 박윤기
 * @version 1.0 <br/> 
 * <br/> 
 * 하나투어에서 정의한 예외처리이다.<br>
 * 예외사항을 지정해주면 콘솔에서 에러내용을 프린트한다.<br>
 * 예외사항에 대한 내용은 ErrorCode에 정의되어있다.<br>
 * printStackTrace부분을 재정의하여 개발내용에 관련된 부분만 출력이 된다. <br>
 *
 */
public class HNTException extends Exception {

	private static final long serialVersionUID = 1L;
	private int errorCode;

	public HNTException(String userMsg) {
		super(userMsg);
	}
	
	public HNTException(int cd, String userMsg) {
		super(MessagePrint.getErrorMsg(cd) + userMsg);
		this.errorCode = cd;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public HNTException setErrorCode(int cd) {
		this.errorCode = cd;
		return this;
	}
	
	private static class MessagePrint{
		private static String getErrorMsg(int errorCode) {
			String errorMsg = "";
			switch (errorCode) {
			case ErrorCode.OK :  
				errorMsg = "OK ";
				break;
			case ErrorCode.ABNORMAL_APPLICATION :  
				errorMsg = "ABNORMAL_APPLICATION ";
				break;
			case ErrorCode.ABNORMAL_DB :  
				errorMsg = "ABNORMAL_DB ";
				break;
			case ErrorCode.ABNORMAL_SERVER :  
				errorMsg = "ABNORMAL_SERVER ";
				break;
			case ErrorCode.DUPLICATE_REQUEST :  
				errorMsg = "DUPLICATE_REQUEST ";
				break;
			case ErrorCode.ETC_ERROR :  
				errorMsg = "ETC_ERROR ";
				break;
			case ErrorCode.FAIL_DECRYPTION :  
				errorMsg = "FAIL_DECRYPTION ";
				break;
			case ErrorCode.FAIL_ENCRYPTION :  
				errorMsg = "FAIL_ENCRYPTION ";
				break;
			case ErrorCode.INVALID_AUTH :  
				errorMsg = "INVALID_AUTH ";
				break;
			case ErrorCode.INVALID_REQ_FORM :  
				errorMsg = "INVALID_REQ_FORM ";
				break;
			case ErrorCode.INVALID_REQ_LEN :  
				errorMsg = "INVALID_REQ_LEN ";
				break;
			case ErrorCode.INVALID_REQUEST :  
				errorMsg = "INVALID_REQUEST ";
				break;
			case ErrorCode.NULL_REQUEST :  
				errorMsg = "NULL_REQUEST ";
				break;
			case ErrorCode.NULL_RESPONSE :  
				errorMsg = "NULL_RESPONSE ";
				break;

			default:
				break;
			}
			return errorMsg;
		}
	}

	public String printStackTrace(int level) {
		return printStackTrace(level, true);
	}
	
	public String printStackTrace(int level, boolean userClass) {
		String msg = this.getMessage();
		StackTraceElement[] st = this.getStackTrace();
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

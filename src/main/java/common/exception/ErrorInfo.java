package common.exception;

public class ErrorInfo {
	private int resultCode = ErrorCode.OK;
	private String resultMsg = "";
	public int getResultCode() {
		return resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultInfo(Exception e) {
		if (e instanceof HNTException ) {
			resultCode = ((HNTException) e).getErrorCode();
			if (resultCode > 0) resultMsg = e.getMessage().substring(e.getMessage().indexOf(" ")+1);
			else resultMsg = e.getMessage();
		} else if (e instanceof HNTException) {
			resultCode = ((HNTException) e).getErrorCode();
			if (resultCode > 0) resultMsg = e.getMessage().substring(e.getMessage().indexOf(" ")+1);
			else resultMsg = e.getMessage();
		} else {
			resultCode = ErrorCode.FAIL_PROCESSING;
			resultMsg = e.getMessage();
		}
	}
}

package common.exception;

/**
 * @author 박윤기
 * @version 1.0 <br/>
 * <br/>
 * 에러사항에 대한 구분을 정의함.
 *
 */
public interface ErrorCode {
	public static final int OK = 0;
	public static final int INVALID_REQUEST = 10;
	public static final int INVALID_REQ_LEN = 11;
	public static final int INVALID_REQ_FORM = 12;
	public static final int DUPLICATE_REQUEST = 13;
	public static final int NULL_REQUEST = 14;
	public static final int NULL_RESPONSE = 15;

	public static final int INVALID_AUTH = 20;

	public static final int FAIL_PROCESSING = 30;
	public static final int FAIL_DECRYPTION= 31;
	public static final int FAIL_ENCRYPTION= 32;

	public static final int ABNORMAL_SERVER = 50;
	public static final int ABNORMAL_DB = 60;
	public static final int ABNORMAL_APPLICATION = 70;

	public static final int INVALID_FILE = 70;
	public static final int EMPTY_FILE = 71;
	public static final int FILE_PROCESS_ERROR = 72;

	public static final int ETC_ERROR = 90;
}

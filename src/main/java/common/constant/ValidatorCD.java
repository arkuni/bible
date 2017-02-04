package common.constant;

/**
 * @author 박윤기
 * @version 1.0 <br/>
 * <br/>
 * 유효성 검사를 위해 정규식을 담은 Enum 객체.
 *
 */
public enum ValidatorCD {
	ONLY_NUM(0, "^[\\-]?[0-9]+[\\.]?[0-9]*$"),
	ONLY_CHAR(1, "^[a-zA-Z]+$"),
	NOT_BLANK(2, "^[\\S]+$"),
	DATESTR_8(3, "^(20|19)[0-9]{2}((0[1-9])|10|11|12)((0[1-9])|([1-2][0-9])|30|31)$"),
	RESERVATION_CD(4, "^(LG|[R][A-Z]|[H][0-9])[0-9A-Z]{10}$"),
	EN_AND_NUM(5, "^[a-zA-Z0-9]+$"),
	NOT_SPECIAL_CHAR(6, "^[^\\`\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\+\\=\\{\\}\\[\\]\\:\\;\"'\\<\\>\\?\\/]+$"),
	BASIC_STR(7, "^[a-zA-Z0-9_]+$"),
	GOODS_CD(8, "^([A-Z]{2})([A-Z])([0-9A-Z])([0-9A-Z]{2})([0-9]{2})(0[0-9]|10|11|12)([0-2][0-9]|30|31)[A-Z0-9]*$"),
	HP_TEL_NO(9, "^0(1[01256789]|2|[3456][12345]|70|80)[\\-]?[0-9]{3,4}[\\-]?[0-9]{4}$"),
	EMP_CODE(10, "^H[A-Z0-9]{4}$"),
	Y_OR_N(11, "^[ynYN]$"),
	NOT_FILE_NAME(12, "^[^\\`\\~\\!\\@\\#\\$\\%\\^\\&\\*\\+\\=\\{\\}\\[\\]\\:\\;\"'\\<\\>\\?\\/]+$"),
	XSS_CHECK(13, "(?i)[^\\w](on[a-zA-Z]+|" +
				"allowscriptaccess|"+
				"javascript|"+
				"htc|"+
				"meta http\\-equiv\\=|"+
				"background|"+
				"#exec cmd|"+
				"vbscript|"+
				"xss)[^\\w]|"+
				"[^\\w](\\\\00[\\w]{2}|"+
				"&#[\\n]+|"+
				"&#x[\\w]+)|"+
				"(<script|"+
				"<\\!\\[cdata|"+
				"<object|"+
				"<style|"+
				"<frameset|"+
				"<iframe|"+
				"<link|"+
				"<base)[^\\w]");
	private final int idx;
	private final String regExStr;
	private ValidatorCD(int idx, String regExStr) {
		this.idx = idx;
		this.regExStr = regExStr;
	}
	public int getValue() {
		return idx;
	}
	public String getRegExStr() {
		return regExStr;
	}
}

package common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.constant.ValidatorCD;
import common.exception.ErrorCode;
import common.exception.HNTException;

/**
 * @author 박윤기
 * @version 1.0 <br/>
 * <br/>
 * 파라미터 유효성 검사 유틸<br>
 * ValidatorCD에 해당하는 정규식으로 겁사하며, 정규식을 직접입력할수도 있다.<br>
 * 유효성검사에 실패하면 HNTException을 발생한다.<br>
 */
public class ParameterValidator {

	public static void check(ValidatorCD cd, int len, String txt, String adminTxt, boolean isOptional) throws HNTException{
		if (isOptional && (txt == null || txt.equals(""))) return;
		if (txt == null || txt.equals("")) throw new HNTException(ErrorCode.NULL_REQUEST, adminTxt);
		if (!lengthCheck(txt, len)) throw new HNTException(ErrorCode.INVALID_REQ_LEN, adminTxt);
		if (!Pattern.matches(cd.getRegExStr(), txt)) throw new HNTException(ErrorCode.INVALID_REQ_FORM, adminTxt);
		return;
	}

	public static void check(ValidatorCD cd, String txt, String adminTxt, boolean isOptional) throws HNTException{
		if (isOptional && (txt == null || txt.equals(""))) return;
		if (txt == null || txt.equals("")) throw new HNTException(ErrorCode.NULL_REQUEST, adminTxt);
		if (!Pattern.matches(cd.getRegExStr(), txt)) throw new HNTException(ErrorCode.INVALID_REQ_FORM, adminTxt);
		return;
	}

	public static void check(String reg, int len, String txt, String adminTxt, boolean isOptional) throws HNTException{
		if (isOptional && (txt == null || txt.equals(""))) return;
		if (txt == null || txt.equals("")) throw new HNTException(ErrorCode.NULL_REQUEST, adminTxt);
		if (!lengthCheck(txt, len)) throw new HNTException(ErrorCode.INVALID_REQ_LEN, adminTxt);
		if (!Pattern.matches(reg, txt)) throw new HNTException(ErrorCode.INVALID_REQ_FORM, adminTxt);
		return;
	}

	public static void checkScript(int len, String txt, String adminTxt, boolean isOptional) throws HNTException{
		if (isOptional && (txt == null || txt.equals(""))) return;
		if (txt == null || txt.equals("")) throw new HNTException(ErrorCode.NULL_REQUEST, adminTxt);
		//if (!lengthCheck(txt, len)) throw new InterfaceException(ErrorCode.INVALID_REQ_LEN, adminTxt);
		txt = txt.replaceAll("[\r\n\0]", "");
		txt = txt.toLowerCase();
		String [] tagNames =
			{
				"a", "abbr", "acronym", "address", "applet", "area", "article", "aside", "audio"
				,"b", "base", "basefont", "bdi", "bdo", "big", "blockquote", "body", "br", "button"
				,"canvas", "caption", "center", "cite", "code", "col", "colgroup", "command"
				,"datalist", "dd", "del", "details", "dfn", "dialog", "dir", "div", "dl", "dt"
				,"em", "embed"
				,"fieldset", "figcaption", "figure", "font", "footer", "form", "frame", "frameset"
				,"h1", "h2", "h3", "h4", "h5", "h6", "head", "header", "hr", "html", "hgroup"
				,"i", "iframe", "img", "input", "ins"
				,"kbd", "keygen"
				,"label", "legend", "li", "link"
				,"main", "math", "map", "mark", "menu", "menuitem", "meta", "meter"
				,"nav", "noframes", "noscript"
				,"object", "ol", "optgroup", "option", "output"
				,"p", "param", "pre", "progress"
				,"q"
				,"rp", "rt", "ruby"
				,"s", "samp", "script", "section", "select", "small", "source", "span", "strike", "strong", "style", "sub", "summary", "sup", "svg"
				,"table", "tbody", "td", "textarea", "tfoot", "th", "thead", "time", "title", "tr", "track", "tt"
				,"u", "ul"
				,"var", "video", "wbr", "xmp", "vbscript"
			};

		boolean isTagDocument = false;
		for(String tag : tagNames) {
			if (txt.indexOf("<"+tag+" ") > -1 || txt.indexOf("<"+tag+">") > -1) {
				isTagDocument = true;
				break;
			}
		}

		if (!isTagDocument) return;

		Pattern p = Pattern.compile(ValidatorCD.XSS_CHECK.getRegExStr());
		Matcher m = p.matcher(txt);
		if (m.find()) {
			throw new HNTException(ErrorCode.INVALID_REQ_FORM, adminTxt);
		}
		return;
	}

	public static boolean lengthCheck(String txt, int len) {
		return (txt.length() <= len);
	}
}

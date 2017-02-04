
package common.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import common.constant.ValidatorCD;
import common.exception.ErrorCode;
import common.exception.HNTException;
import common.upload.bean.FileDictionary;
import common.upload.bean.FileHeaderInfo;
import common.upload.bean.FileTypes;
import common.util.ParameterValidator;


/**
 * @author 박윤기
 * @version 1.0 <br/>
 * <br/>
 * 파일분석 클래스.
 *
 */
public class FileForensicFactory {

	private String extend;
	private String filename;
	private File file;
	private FileHeaderInfo fileHeader;

	/**
	 * @param file 업로드할 파일
	 * @throws HNTException 파일에 확장자가 없으면 예외처리.
	 */
	public FileForensicFactory(File file) throws HNTException {
		if (file == null) throw new HNTException(ErrorCode.INVALID_REQUEST, "file is null");

		this.filename = file.getName();
		if (filename.indexOf('.') < 0) throw new HNTException(
				ErrorCode.INVALID_REQUEST,
				"ext is null");
		this.file = file;
		this.extend = filename.substring(filename.lastIndexOf('.')+1).toLowerCase();
		FileDictionary fileDictionary = new FileDictionary();
		this.fileHeader = fileDictionary.getFileByExt(extend);
	}

	/**
	 * blacklist기법<br/>
	 * 웹쉘, 소스파일, 실행파일인지 아닌지 확장자로 체크<br/>
	 * @return T/F
	 */
	public boolean isDangerousExt() {
		String [] danger = {"jsp", "asp", "aspx", "c", "dll", "java", "php", "class", "obj", "js", "exe"};
		for(String ext : danger) {
			if (ext.equals(extend)) return true;
		}
		return false;
	}

	/**
	 * whitelist기법<br/>
	 * 미리 정의된 확장자인지 체크<br/>
	 * 미리 정의된 확장자는 common.upload.bean.FileTypes enum클래스에 있음.<br/>
	 * @return T/F
	 */

	public boolean isSafetyExt() {
		for(FileTypes ext : FileTypes.values()) {
			if (ext.getValue().equals(extend)) return true;
			if (this.fileHeader == null) continue;
			if (this.fileHeader.getFileType().equals(FileTypes.SYM)) return true;
		}
		return false;
	}

	/**
	 * 파일명에 특수문자가 포함되었는지 체크<br/>
	 * @return T/F
	 *
	 */
	public boolean isSpecialCharFileName() {
		try {
			ParameterValidator.check(ValidatorCD.NOT_FILE_NAME, filename, "filename", false);
		} catch (HNTException e) {
			return false;
		}
		return true;
	}

	/**
	 * 확장자와 그에 해당하는 헤더값이 일치하는지 체크함<br/>
	 * common.upload.bean.FileTypes enum클래스에 있는 파일명만 체크가 가능함.<br/>
	 * enum클래스에 없는 파일은 false처리<br/>
	 * 헤더값 정보는 common.upload.bean.FileDictionary에 있음.<br/>
	 * @return T/F
	 */
	public boolean isMatchExtendWithHeader() {
		try {
			if (this.fileHeader == null) throw new HNTException(ErrorCode.INVALID_REQUEST, "cannot upload this file. ("+extend+")");
			List<byte[]> info = this.fileHeader.getFileHeaders();

			InputStream is = new FileInputStream(file);
			long length = file.length();
			byte[] data = new byte[this.fileHeader.getMaxHeaderLength()];
			int offset = 0;
			int numRead = 0;
			while (offset < length
					&& (numRead = is.read(data, offset, data.length - offset)) >= 0
					&& offset < data.length) {
				offset += numRead;
			}
			is.close();
			for (int i = 0; i < info.size(); i++) {
				//System.out.println("info no : " + i);
				byte[] tmp = info.get(i);
				boolean isMatch = true;
				for (int j = 0; j < tmp.length; j++) {
					if (tmp[j] == 0) continue;
					if (data[j] != tmp[j]) {
						//System.out.println("   index : " + j +" is not matched.. ("+ Integer.toHexString(data[j])+ ", " + Integer.toHexString(tmp[j]) +")");
						isMatch = false;
						break;
					}
					//System.out.println("   index : " + j +" is matched! ");
					continue;
				}

				if (isMatch) return true;
			}
			return false;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

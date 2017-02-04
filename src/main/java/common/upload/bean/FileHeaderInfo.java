package common.upload.bean;

import java.util.ArrayList;
import java.util.List;


public class FileHeaderInfo {
	private FileTypes fileType;
	private int maxHeaderLength;
	private List<byte[]> fileHeaders;
	public FileHeaderInfo(FileTypes fileType, byte[] fileHeader) {
		this.fileType = fileType;
		this.fileHeaders = new ArrayList<byte[]>();
		this.maxHeaderLength = fileHeader.length;
		this.fileHeaders.add(fileHeader);
	}
	public FileHeaderInfo addFileHeader(byte[] fileHeader) {
		this.fileHeaders.add(fileHeader);
		this.maxHeaderLength = this.maxHeaderLength < fileHeader.length ? fileHeader.length : this.maxHeaderLength;
		return this;
	}
	
	public FileTypes getFileType() {
		return fileType;
	}
	
	public List<byte[]> getFileHeaders() {
		return fileHeaders;
	}
	
	public int getMaxHeaderLength() {
		return maxHeaderLength;
	}
}

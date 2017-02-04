package common.upload.bean;


public enum FileTypes {
	JPG		("jpg", 0),
	BMP		("bmp", 1),
	GIF		("gif", 2),
	PNG		("png", 3),
	XLS		("xls", 4),
	XLSX	("xlsx", 5),
	PPT		("ppt", 6),
	PPTX	("pptx", 7),
	DOC		("doc", 8),
	DOCX	("docx", 9),
	ZIP		("zip", 10),
	RAR		("rar", 11),
	SEVENZ	("7z", 12),
	PDF		("pdf", 13),
	HWP		("hwp", 14),
	TIF		("tif", 15),
	JPEG	("jpeg", 16),
	SYM		("sym", 17);

	public final String ext;
	public final int idx;
	/**
	 * @param idx
	 */
	private FileTypes(String ext, int idx) {
		this.ext = ext;
		this.idx = idx;
	}
	/**
	 * @return String
	 */
	public String getValue() {
		return ext;
	}

	public int getIdx() {
		return idx;
	}
}

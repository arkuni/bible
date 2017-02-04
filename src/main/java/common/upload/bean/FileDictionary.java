package common.upload.bean;

public class FileDictionary {
	// images
	private byte[] jpg_0 = {(byte)0xFF, (byte)0xD8, (byte)0xFF, (byte)0xE0};
	private byte[] jpg_1 = {(byte)0xFF, (byte)0xD8, (byte)0xFF, (byte)0xE1};
	private byte[] jpg_2 = {(byte)0xFF, (byte)0xD8, (byte)0xFF, (byte)0xE2};
	private byte[] jpg_3 = {(byte)0xFF, (byte)0xD8, (byte)0xFF, (byte)0xE3};
	private byte[] jpg_4 = {(byte)0xFF, (byte)0xD8, (byte)0xFF, (byte)0xE8};
	private byte[] bmp = {(byte)0x42, (byte)0x4D};
	private byte[] png = {(byte)0x89, (byte)0x50, (byte)0x4E, (byte)0x47, (byte)0x0D, (byte)0x0A, (byte)0x1A, (byte)0x0A};
	//private byte[] tif_0 = {(byte)0x49, (byte)0x49, (byte)0x2A};
	//private byte[] tif_1 = {(byte)0x4D, (byte)0x4D, (byte)0x2A};
	//private byte[] pic = {(byte)0x34, (byte)0x12};
	private byte[] gif_0 = {(byte)0x47, (byte)0x49, (byte)0x46, (byte)0x38, (byte)0x37, (byte)0x61};
	private byte[] gif_1 = {(byte)0x47, (byte)0x49, (byte)0x46, (byte)0x38, (byte)0x39, (byte)0x61};

	private byte[] tif_0 = {(byte)0x49, (byte)0x49, (byte)0x2A};
	private byte[] tif_1 = {(byte)0x4D, (byte)0x4D, (byte)0x2A};
	private byte[] tif_2 = {(byte)0x4D, (byte)0x4D, (byte)0x00, (byte)0x2A};
	private byte[] tif_3 = {(byte)0x4D, (byte)0x4D, (byte)0x00, (byte)0x2B};


	// document
	//private byte[] hwp = {};
	private byte[] xls = {(byte)0xD0, (byte)0xCF, (byte)0x11, (byte)0xE0, (byte)0xA1, (byte)0xB1, (byte)0x1A, (byte)0xE1};
	private byte[] xlsx = {(byte)0x50, (byte)0x4B, (byte)0x03, (byte)0x04};
	private byte[] ppt = {(byte)0xD0, (byte)0xCF, (byte)0x11, (byte)0xE0, (byte)0xA1, (byte)0xB1, (byte)0x1A, (byte)0xE1};
	private byte[] pptx = {(byte)0x50, (byte)0x4B, (byte)0x03, (byte)0x04};
	private byte[] doc = {(byte)0xD0, (byte)0xCF, (byte)0x11, (byte)0xE0, (byte)0xA1, (byte)0xB1, (byte)0x1A, (byte)0xE1};
	private byte[] docx = {(byte)0x50, (byte)0x4B, (byte)0x03, (byte)0x04};
	private byte[] pdf = {(byte)0x25, (byte)0x50, (byte)0x44, (byte)0x46, (byte)0x2d, (byte)0x31, (byte)0x2e};
	private byte[] hwp_0 = {(byte)0xD0, (byte)0xCF, (byte)0x11, (byte)0xE0, (byte)0xA1, (byte)0xB1, (byte)0x1A, (byte)0xE1};
	private byte[] hwp_1 = {(byte)0x48, (byte)0x57, (byte)0x50, (byte)0x20, (byte)0x44, (byte)0x6f, (byte)0x63,
							(byte)0x75, (byte)0x6D, (byte)0x65, (byte)0x6E, (byte)0x74, (byte)0x20, (byte)0x46,
							(byte)0x69, (byte)0x6C, (byte)0x65};

	// archive
	private byte[] zip_0 = {(byte)0x50, (byte)0x4B, (byte)0x03, (byte)0x04};
	private byte[] zip_1 = {(byte)0x50, (byte)0x4B, (byte)0x05, (byte)0x06};
	private byte[] zip_2 = {(byte)0x50, (byte)0x4B, (byte)0x07, (byte)0x08};
	private byte[] zip_3 = {(byte)0x57, (byte)0x69, (byte)0x6E, (byte)0x5A, (byte)0x69, (byte)0x70};
	private byte[] zip_4 = {(byte)0x50, (byte)0x4B, (byte)0x4C, (byte)0x49, (byte)0x54, (byte)0x45};
	private byte[] zip_5 = {(byte)0x50, (byte)0x4B, (byte)0x53, (byte)0x70, (byte)0x58};
	private byte[] sevenz = {(byte)0x37, (byte)0x7A, (byte)0xBC, (byte)0xAF, (byte)0x27, (byte)0x1C};
	//private byte[] lzh = {};
	private byte[] rar = {(byte)0x52, (byte)0x61, (byte)0x72, (byte)0x21, (byte)0x1A, (byte)0x07};
	//private byte[] tar = {};
	//private byte[] gz = {};

	private byte[] sym ={(byte)0xFA, 0, (byte)0x2C, 0, 0, (byte)0xF0, 0, (byte)0x13, 0, 0, (byte)0xC6, 0, 0, (byte)0xE1, 0, (byte)0xF2};


	/*
	 *
	// multimedia
	private byte[] mp3 = {};
	private byte[] mp4 = {};
	private byte[] avi = {};
	private byte[] wma = {};
	private byte[] wmv = {};
	private byte[] asf = {};
	private byte[] mpg = {};
	*/

	private byte[][] jpg = {jpg_0, jpg_1, jpg_2, jpg_3, jpg_4};
	private byte[][] gif = {gif_0, gif_1};
	private byte[][] hwp = {hwp_0, hwp_1};
	private byte[][] zip = {zip_0, zip_1, zip_2, zip_3, zip_4, zip_5};
	private byte[][] tif = {tif_0, tif_1, tif_2, tif_3};



	private FileHeaderInfo getBmp() {
		FileHeaderInfo info = new FileHeaderInfo(FileTypes.BMP, bmp);
		return info;
	}

	private FileHeaderInfo getPng() {
		FileHeaderInfo info = new FileHeaderInfo(FileTypes.PNG, png);
		return info;
	}

	private FileHeaderInfo getXls() {
		FileHeaderInfo info = new FileHeaderInfo(FileTypes.XLS, xls);
		return info;
	}

	private FileHeaderInfo getXlsx() {
		FileHeaderInfo info = new FileHeaderInfo(FileTypes.XLSX, xlsx);
		return info;
	}

	private FileHeaderInfo getPpt() {
		FileHeaderInfo info = new FileHeaderInfo(FileTypes.PPT, ppt);
		return info;
	}

	private FileHeaderInfo getPptx() {
		FileHeaderInfo info = new FileHeaderInfo(FileTypes.PPTX, pptx);
		return info;
	}

	private FileHeaderInfo getPdf() {
		FileHeaderInfo info = new FileHeaderInfo(FileTypes.PDF, pdf);
		return info;
	}

	private FileHeaderInfo getHwp() {
		FileHeaderInfo info = new FileHeaderInfo(FileTypes.HWP, hwp[0])
								.addFileHeader(hwp[1]);
		return info;
	}

	private FileHeaderInfo getDoc() {
		FileHeaderInfo info = new FileHeaderInfo(FileTypes.DOC, doc);
		return info;
	}

	private FileHeaderInfo getDocx() {
		FileHeaderInfo info = new FileHeaderInfo(FileTypes.DOCX, docx);
		return info;
	}

	private FileHeaderInfo getSevenz() {
		FileHeaderInfo info = new FileHeaderInfo(FileTypes.SEVENZ, sevenz);
		return info;
	}

	private FileHeaderInfo getRar() {
		FileHeaderInfo info = new FileHeaderInfo(FileTypes.RAR, rar);
		return info;
	}

	private FileHeaderInfo getSym() {
		FileHeaderInfo info = new FileHeaderInfo(FileTypes.SYM, sym);
		return info;
	}

	private FileHeaderInfo getJpg() {
		FileHeaderInfo info = new FileHeaderInfo(FileTypes.JPG, jpg[0])
								.addFileHeader(jpg[1])
								.addFileHeader(jpg[2])
								.addFileHeader(jpg[3])
								.addFileHeader(jpg[4]);
		return info;
	}

	private FileHeaderInfo getTif() {
		FileHeaderInfo info = new FileHeaderInfo(FileTypes.TIF, tif[0])
								.addFileHeader(tif[1])
								.addFileHeader(tif[2])
								.addFileHeader(tif[3]);
		return info;
	}

	private FileHeaderInfo getGif() {
		FileHeaderInfo info = new FileHeaderInfo(FileTypes.GIF, gif[0])
								.addFileHeader(gif[1]);
		return info;
	}

	private FileHeaderInfo getZip() {
		FileHeaderInfo info = new FileHeaderInfo(FileTypes.ZIP, zip[0])
								.addFileHeader(zip[1])
								.addFileHeader(zip[2])
								.addFileHeader(zip[3])
								.addFileHeader(zip[4])
								.addFileHeader(zip[5]);
		return info;
	}

	public FileHeaderInfo getFileByExt(String ext) {
		FileHeaderInfo headerInfo = null;
		FileTypes type = null;
		for(FileTypes tmp : FileTypes.values()) {
			if(ext.equals(tmp.getValue())) {
				type = tmp;
				break;
			}
		}
		if(ext.indexOf("sym") == 0 || ext.equals("scare")) type = FileTypes.SYM;
		if(ext.equals("jpeg")) type = FileTypes.JPG;
		if (type == null) return null;

		switch(type) {
		case JPG :
			headerInfo = this.getJpg();
			break;
		case BMP :
			headerInfo = this.getBmp();
			break;
		case PNG :
			headerInfo = this.getPng();
			break;
		case GIF :
			headerInfo = this.getGif();
			break;
		case TIF :
			headerInfo = this.getTif();
			break;
		case XLS :
			headerInfo = this.getXls();
			break;
		case XLSX :
			headerInfo = this.getXlsx();
			break;
		case PPT :
			headerInfo = this.getPpt();
			break;
		case PPTX :
			headerInfo = this.getPptx();
			break;
		case PDF :
			headerInfo = this.getPdf();
			break;
		case HWP :
			headerInfo = this.getHwp();
			break;
		case DOC :
			headerInfo = this.getDoc();
			break;
		case DOCX :
			headerInfo = this.getDocx();
			break;
		case ZIP :
			headerInfo = this.getZip();
			break;
		case RAR :
			headerInfo = this.getRar();
			break;
		case SEVENZ :
			headerInfo = this.getSevenz();
			break;
		case SYM :
			headerInfo = this.getSym();
			break;
		}

		return headerInfo;
	}
}


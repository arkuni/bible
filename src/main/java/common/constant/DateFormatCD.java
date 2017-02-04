package common.constant;

/**
 * @author 박윤기
 * @version 1.0 <br/> 
 * <br/> 
 * DateUtil에서 사용하는 날짜패턴. <br/> 
 * Oracle날짜 형식을 가져왔다.<br/>
 *
 */
public enum DateFormatCD {
	YYMM			("2110000", 0),
	YYYYMM			("2210000", 1),
	YYMMDD			("3111000", 2),
	YYYYMMDD		("3211000", 3),
	YYMMDDHH		("4111100", 4),
	YYYYMMDDHH		("4211100", 5),
	YYMMDDHH24		("4111200", 6),
	YYYYMMDDHH24	("4211200", 7),
	YYMMDDHHMI		("5111110", 8),
	YYYYMMDDHHMI	("5211110", 9),
	YYMMDDHH24MI	("5111210", 10),
	YYYYMMDDHH24MI	("5211210", 11),
	YYMMDDHHMISS	("6111111", 12),
	YYYYMMDDHHMISS	("6211111", 13),
	YYMMDDHH24MISS	("6111211", 14),
	YYYYMMDDHH24MISS("6211211", 15);
	public final String seq;
	public final int idx;
	/**
	 * @param idx
	 */
	private DateFormatCD(String seq, int idx) {
		this.seq = seq;
		this.idx = idx;
	}
	/**
	 * @return String
	 */
	public String getValue() {
		return seq;
	}
	
	public int getIdx() {
		return idx;
	}
}
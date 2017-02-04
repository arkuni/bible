package common.code;



/**
 * @author 박윤기
 * @version 1.0 <br/> 
 * <br/> 
 * CodeAbstract 클래스를 상속받은 Code 클래스를 인자로 받아 코드 이름과 코드배열을 리턴하는 유틸.<br/>
 * 스트링 배열로 관리하는 임시코드들을 관리할수 있도록 만든 유틸이다.<br/>
 * CodeAbstract를 상속받은 Code클래스는 2차원 스트링배열을 요소로 가진다.<br/>
 * 아래 코드를 참조한다.
 * 
 * <pre>
public class CancelStatusCD extends CodeAbstract{
	public static CancelStatusCD singleton = new CancelStatusCD();
	private String UNDEFINED = "확인안됨";
	private String[][] codeInfos = 
		{
			{"0", "전체"}
			,{"A", "승인됨"}
			,{"R", "접수됨"}
			,{"X", "취소불가"}
			,{"C", "확인중"}
			,{"E", "취소완료"}
		};
	public CancelStatusCD() {}
	
	public CancelStatusCD getInstance() {
		return singleton;
	}
	
	public String getCodeNm(String code) {
		for (String[] tmp : codeInfos) {
			if (tmp[0].equals(code)) return tmp[1];
		}
		return UNDEFINED;
	}

	public String[][] getCodeInfo() {
		return codeInfos;
	}

}</pre>
 * 
 * 
 */
public class StaticCodeUtil {
	/**
	 * @param class1 CodeAbstract를 상속받은 코드 클래스
	 * @param code 코드 이름을 받고자 하는 코드
	 * @return String 코드명
	 * 
	 */
	public static String getCodeNm(AbstractCode class1, String code) {
		return class1.getCodeNm(code);
	}
	
	/**
	 * @param class1 CodeAbstract를 상속받은 코드 클래스
	 * @return String[][] 코드 2차원배열
	 * 
	 * 코드 전체 정보.
	 */
	public static String[][] getCodeInfo(AbstractCode class1) {
		return class1.getCodeInfo();
	}
}

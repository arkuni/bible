/*
 * 작성된 날짜: 2013. 9. 6.
 *
 */
package common.util;

/**
 * @author 박윤기
 * @version 1.0 <br/> 
 * <br/> 
 * 페이징 정보 객체
 */
public class PageUtil {
	private int LIST_PER_PAGE = 20;		//한페이지당리스트수
	private int GROUP_CNT = 10;			//페이징에 보여지는 그룹 개수
	private int totalPage = -1;
	private int beforeGroupPage = 0;	// 이전단위 페이지  그룹 마지막번호
	private int nextGroupPage = 0;		// 다음단위 페이지 그룹 시작번호
	private int pageGroupStart = 0;		// 페이지 리스트 그룹시작번호
	private int pageGroupEnd = 0;		// 페이지 리스트 그룹 끝번호
	private int beforePageNo = 0;		// 이전페이지 번호
	private int nextPageNo = 0;			// 다음페이지 번호
	private int currentPage = 0;		// 현재 유효한 페이지번호
	
	
	/**
	 * @param totalListCnt
	 */
	public PageUtil(int totalListCnt) {
		this(totalListCnt, 1);
	}
	
	/**
	 * @param totalListCnt
	 * @param currentPage
	 */
	public PageUtil(int totalListCnt, int currentPage) {
		this(totalListCnt, currentPage, 10);
	}
	
	/**
	 * @param totalListCnt
	 * @param currentPage
	 * @param listPerPage
	 */
	public PageUtil(int totalListCnt, int currentPage, int listPerPage) {
		this(totalListCnt, currentPage, listPerPage, 10);
	}
	
	/**
	 * @param totalListCnt
	 * @param currentPage
	 * @param listPerPage
	 * @param groupCnt
	 */
	public PageUtil(int totalListCnt, int currentPage, int listPerPage, int groupCnt) {
		this.currentPage = currentPage;
		this.GROUP_CNT = groupCnt;
		this.LIST_PER_PAGE = listPerPage;
		
		pageCalculate(totalListCnt);
	}

	
	public int currentGroupStartNo() {
		return pageGroupStart;
	}
	
	public int currentGroupEndNo() {
		return pageGroupEnd;
	}

	public int beforeGroupPage() {
		return beforeGroupPage;
	}

	
	public int nextGroupPage() {
		return nextGroupPage;
	}
	
	public int nextPageNo() {
		return nextPageNo;
	}
	
	public int beforePageNo() {
		return beforePageNo;
	}
	
	private void pageCalculate(int totalListCnt) {
		
		if (totalListCnt%LIST_PER_PAGE < 1) totalPage = (totalListCnt/LIST_PER_PAGE);
		else totalPage = (totalListCnt/LIST_PER_PAGE)+1;
		
		if (currentPage < 1) currentPage = 1;
		else if (currentPage > totalPage) currentPage = totalPage+1;
		pageGroupStart = (currentPage/GROUP_CNT)*GROUP_CNT+1;
		pageGroupEnd = ((currentPage/GROUP_CNT)+1)*GROUP_CNT;
		
		// 총 페이지 수가 단위 페이징수 보다 작으면
		if (totalPage < GROUP_CNT) {
			pageGroupStart = 1;
			pageGroupEnd = totalPage;
		}
		// 현재 페이지번호가 단위 페이징수 보다 작으면 
		else if (currentPage < GROUP_CNT) {
			pageGroupStart = 1;
		}
		// 현재 페이지번호가 전체 페이지 번호보다 크면 
		else if (currentPage > totalPage) {
			pageGroupStart = getMaxStartGroupNo();
			pageGroupEnd = getMaxEndGroupNo();
		}
		// 현재 페이지가 단위페이징수로 나눴을 때 나머지가 0인 경우
		else if (currentPage % GROUP_CNT < 1) {
			pageGroupStart = ((currentPage/GROUP_CNT)-1)*GROUP_CNT+1;
			pageGroupEnd = (currentPage/GROUP_CNT)*GROUP_CNT;
		} 
		// 총 페이지 수가 페이지 그룹 마지막번호 계산로직의 결과 보다 작은 경우 또는 단위페이징보다 작은 경우
		else if (totalPage < (currentPage/GROUP_CNT)*GROUP_CNT || totalPage < pageGroupEnd) {
			pageGroupEnd = totalPage;
		}
		
		beforeGroupPage = pageGroupStart-1;
		nextGroupPage = pageGroupEnd+1;
		beforePageNo = currentPage -1;
		nextPageNo = currentPage +1;
		
		if (beforeGroupPage < 1 ) beforeGroupPage = 1;
		if (nextGroupPage < GROUP_CNT) nextGroupPage = 1;
		if (nextGroupPage > totalPage) nextGroupPage = totalPage;
		
		if (beforePageNo < 1) beforePageNo = 1;
		else if (beforePageNo > totalPage) beforePageNo = totalPage -1;
		
		if (nextPageNo > totalPage) nextPageNo = totalPage;
	}

	public void setListPerPage(int listPerPage) {
		this.LIST_PER_PAGE = listPerPage;
	}
	
	public void setGroupCnt(int groupCnt) {
		this.GROUP_CNT = groupCnt;
	}
	
	private int getMaxStartGroupNo() {
		int basic = totalPage/GROUP_CNT*GROUP_CNT;
		if (totalPage%GROUP_CNT < 1) return basic-GROUP_CNT+1;
		return basic+1;
	}
	
	private int getMaxEndGroupNo() {
		int basic = totalPage/GROUP_CNT*GROUP_CNT;
		if (totalPage < basic+GROUP_CNT) return totalPage;
		if (totalPage%GROUP_CNT < 1)  return basic+GROUP_CNT;
		return basic;
	}
	
	public int getTotalPageNo() {
		return totalPage;
	}

}

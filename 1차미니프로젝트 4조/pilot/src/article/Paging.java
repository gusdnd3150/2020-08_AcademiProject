package article;

public class Paging {
	private int page=1; // 현재 페이지(get)
	private int totalCount; // row 전체의 수(get)
	private int beginPage;  // 페이지 처음 번호
	private int endPage;	// 페이지 끝 번호
	private int displayRow = 10;  // 한 페이지에 몇 개의 로우 (선택 set)
	private int displayPage = 10; // 한 페이지에 몇 개의 페이지
	private int startNum;// 해당 페이지에서 시작하는 게시글의 번호
	private int endNum;// 해당 페이지에서 끝나는 게시글의 번호
	boolean prev;	// prev 버튼이 보일지 안보일지
	boolean next;	// next 버튼이 보일지 안보일지
	
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page; // 선택한 페이지 번호를 설정
		startNum = (page-1)*displayRow+1; // page가 1일때 (0*10+1) 2일때 (1*10+1) ...
		endNum = page*displayRow;		  // page가 1일때 (1*10) 2일때 (2*10) ...
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount; // 총 게시글 수 설정
		paging(); // 총 게시글을 설정한 후에 몇 개의 페이지를 보여줄지, prev, next 버튼이 보일지 설정하는 메소드  호출
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getDisplayRow() {
		return displayRow;
	}
	public void setDisplayRow(int displayRow) {
		this.displayRow = displayRow;
	}
	public int getDisplayPage() {
		return displayPage;
	}
	public void setDisplayPage(int displayPage) {
		this.displayPage = displayPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	private void paging() {
		// prev, next, beginPage, endPage를 계산해서 만든다.
		
		// page = 현재페이지
		// page = 1 => 1 / 10 => 0.1 (올림) => 1 * 10 => 10
		// page = 2 => 2 / 10 => 0.2 (올림) => 2 * 10 => 20
		// 한 화면에 보여줄 페이지 수의 끝 (첫 번째 섹션은 ~10 두번째는 ~20)
		endPage = ((int)Math.ceil(page/(double)displayPage)) * displayPage;
		System.out.println("endPage:" + endPage);
		
		// page = 1 => 10 - (10-1) = 1
		// page = 2 => 20 - (10-1) = 11
		// 한 화면에 보여줄 페이지 수의 시작 (첫번째 섹션은 1~ 두번째는 11~) 
		beginPage = endPage - (displayPage - 1);
		System.out.println("beginPage:" + beginPage);
		
		// 글 32개
		// 32/10 = 3.2(올림) 4페이지
		// 총 페이지 수 계산  = 총 게시글 수 / 한 섹션 페이지 수
		int totalPage = (int)Math.ceil(totalCount/(double)displayRow);
		
		// 4 < 10
		// 총 페이지 수가 끝 페이지보다 작으면 바꿔줌
		if(totalPage < endPage) {
			endPage = totalPage;
			next = false; // 10페이지보다 작기때문에 next는 안보여줌
		} else {
			next = true; // 총 페이지 수가 10보다 크면 next 보여줌
		}
		// 시작페이지가 1이 아니면 prev 버튼을 보여줌
		prev = (beginPage==1) ? false : true;
		System.out.println("endPage :" + endPage);
		System.out.println("totalPage :" + totalPage);
	}
	
}

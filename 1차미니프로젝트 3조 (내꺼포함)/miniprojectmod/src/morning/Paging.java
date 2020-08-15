package morning;

public class Paging {
	
	private int pageCount, maxPage, nowPage, maxBound, minBound, maxNum;
	
	public Paging() {
		super();
	}
	public Paging(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}	
	public int getMaxBound() {
		return maxBound;
	}
	public void setMaxBound(int maxBound) {
		this.maxBound = maxBound;
	}
	public int getMinBound() {
		return minBound;
	}
	public void setMinBound(int minBound) {
		this.minBound = minBound;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	public void setBound() {
		this.maxBound = maxPage-(nowPage-1)*pageCount-1;
		if(maxPage<10) {
			maxBound = maxPage;
		}
		this.minBound = maxPage-nowPage*pageCount;
		if(minBound<0) {
			minBound = 0;
		}
		this.maxNum = (maxPage-1)/pageCount+1;
	}

}

package Common;

public class PagingVO {

    private int totalCount;
    private int currentPage;
    private int totalPage;

    private int displayPage = 10;
    private int displayRow = 10;
    private int beginPage;
    private int endPage;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getDisplayPage() {
        return displayPage;
    }

    public void setDisplayPage(int displayPage) {
        this.displayPage = displayPage;
    }

    public int getDisplayRow() {
        return displayRow;
    }

    public void setDisplayRow(int displayRow) {
        this.displayRow = displayRow;
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

    @Override
    public String toString() {
        return "PagingVO{" +
                "totalCount=" + totalCount +
                ", currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", displayPage=" + displayPage +
                ", displayRow=" + displayRow +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                '}';
    }
}

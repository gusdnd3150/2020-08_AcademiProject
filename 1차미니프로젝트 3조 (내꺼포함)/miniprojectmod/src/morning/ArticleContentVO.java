package morning;

public class ArticleContentVO {
	
	private int ArticleNo;
	private String Content;
	
	public ArticleContentVO() {
		super();
	}
	public ArticleContentVO(int articleNo, String content) {
		super();
		ArticleNo = articleNo;
		Content = content;
	}
	public int getArticleNo() {
		return ArticleNo;
	}
	public void setArticleNo(int articleNo) {
		ArticleNo = articleNo;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	
}

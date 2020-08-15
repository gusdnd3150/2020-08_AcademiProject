package article;

public class ArticleVO {
	private String article_no;
	private String writer_id;
	private String writer_name;
	private String title;
	private String password;
	private String regdate;
	private String moddate;
	private String read_cnt;
	
	public ArticleVO() {
		
	}
	public ArticleVO(String article_no, String title, String writer_name, String read_cnt) {
		this.article_no = article_no;
		this.writer_name = writer_name;
		this.title = title;
		this.read_cnt = read_cnt;
	}
	public String getArticle_no() {
		return article_no;
	}
	public void setArticle_no(String article_no) {
		this.article_no = article_no;
	}
	public String getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	public String getWriter_name() {
		return writer_name;
	}
	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getModdate() {
		return moddate;
	}
	public void setModdate(String moddate) {
		this.moddate = moddate;
	}
	public String getRead_cnt() {
		return read_cnt;
	}
	public void setRead_cnt(String read_cnt) {
		this.read_cnt = read_cnt;
	}
}

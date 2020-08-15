package article;

public class ContentVO {
	private String article_no;
	private String writer_name;
	private String title;
	private String content;
	private String file_name;
	
	public ContentVO(String article_no, String writer_name, String title, String content, String fileName) {
		this.article_no = article_no;
		this.writer_name = writer_name;
		this.title = title;
		this.content = content;
		this.file_name = fileName;
	}
	public ContentVO(String article_no, String title, String content) {
		this.article_no = article_no;
		this.title = title;
		this.content = content;
	}
	public String getArticle_no() {
		return article_no;
	}
	public void setArticle_no(String article_no) {
		this.article_no = article_no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
}

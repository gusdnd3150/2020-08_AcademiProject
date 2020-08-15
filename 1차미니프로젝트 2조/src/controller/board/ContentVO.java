package controller.board;



public class ContentVO {
	private int article_no;
	private String content;
	private String password;
	private String title;
	private String writer_name;
	private String file_name;

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public ContentVO() {

	}
	
	ContentVO(String title, String content){
		this.title=title;
		this.content=content;
	}
	public ContentVO(int article_no, String content, String password, String title, String writer_name, String file_name) {
		this.article_no = article_no;
		this.content = content;
		this.password = password;
		this.title = title;
		this.writer_name = writer_name;
		this.file_name= file_name;
	}
	public String getWriter_name() {
		return writer_name;
	}
	public void setWriter_id(String writer_name) {
		this.writer_name = writer_name;
	}
	ContentVO(int article_no, String content,String password,String title,String writer_name){
		this.article_no=article_no;
		this.content=content;
		this.password=password;
		this.title = title;
		this.writer_name=writer_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getArticle_no() {
		return article_no;
	}
	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}

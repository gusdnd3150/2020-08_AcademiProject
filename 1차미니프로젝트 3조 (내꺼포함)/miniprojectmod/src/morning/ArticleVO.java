package morning;

import java.util.ArrayList;
import java.util.Date;

public class ArticleVO {

	private int articleNo, readCnt;
	private String writerId, writerName, title, password, fileName, regdate, moddate;

	public ArticleVO() {
		super();
	}

	public ArticleVO(int articleNo, String writerId, String writerName, String title, String password, String regdate,
			String moddate, int readCnt, String fileName) {
		super();
		this.articleNo = articleNo;
		this.readCnt = readCnt;
		this.writerId = writerId;
		this.writerName = writerName;
		this.title = title;
		this.password = password;
		this.fileName = fileName;
		this.regdate = regdate;
		this.moddate = moddate;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}

package morning;

import java.util.Date;

public class postCommentVO {
	private int postCommentNo, articleNo;
	private String writerId,postComment;
	private Date regdate;
	
	public postCommentVO() {
	}
	public postCommentVO(int postCommentNo, String writerId, String postComment, Date regdate, int articleNo) {
		super();
		this.postCommentNo = postCommentNo;
		this.writerId = writerId;
		this.postComment = postComment;
		this.regdate = regdate;
		this.articleNo = articleNo;
	}
	public int getPostCommentNo() {
		return postCommentNo;
	}

	public void setPostCommentNo(int postCommentNo) {
		this.postCommentNo = postCommentNo;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getPostComment() {
		return postComment;
	}

	public void setPostComment(String postComment) {
		this.postComment = postComment;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
}

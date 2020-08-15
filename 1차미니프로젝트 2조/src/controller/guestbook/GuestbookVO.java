package controller.guestbook;

public class GuestbookVO {
	private int message_id;
	private String guest_name, password, message;
	private String fileName;	// DB에 없지만 파일 저장하기위해
	
	public GuestbookVO() {
	}
	
	public GuestbookVO(int message_id, String guest_name, String password, String message) {
		super();
		this.message_id = message_id;
		this.guest_name = guest_name;
		this.password = password;
		this.message = message;
	}
	
	public GuestbookVO(int message_id, String guest_name, String password, String message, String fileName) {
		super();
		this.message_id = message_id;
		this.guest_name = guest_name;
		this.password = password;
		this.message = message;
		this.fileName = fileName;
	}

	public GuestbookVO(String guest_name, String password, String message) {
		this.guest_name = guest_name;
		this.password = password;
		this.message = message;
	}
	public int getMessage_id() {
		return message_id;
	}
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}
	public String getGuest_name() {
		return guest_name;
	}
	public void setGuest_name(String guest_name) {
		this.guest_name = guest_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}

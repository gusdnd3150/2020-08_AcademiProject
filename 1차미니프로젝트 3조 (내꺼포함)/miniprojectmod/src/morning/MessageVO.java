package morning;

public class MessageVO {
	private int messageId;
	private String guestName, password, message, fileName;

	public MessageVO() {

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public MessageVO(int messageId, String guestName, String password, String message) {
		this.messageId = messageId;
		this.guestName = guestName;
		this.password = password;
		this.message = message;
	}

	public MessageVO(int messageId, String guestName, String password, String message, String fileName) {
		this.messageId = messageId;
		this.guestName = guestName;
		this.password = password;
		this.message = message;
		this.fileName = fileName;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
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

}

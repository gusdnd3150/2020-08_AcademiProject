package GuestBook;

public class GuestbookVO {

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String message_id;
    private String guest_name;
    private String password;
    private String message;
    private String fileName;
    private String orgfileName;

    public GuestbookVO() {
    }

    public GuestbookVO (String message_id, String guest_name, String password, String message, String fileName,
                        String orgfileName) {
        this.message_id = message_id;
        this.guest_name = guest_name;
        this.password = password;
        this.message = message;
        this.orgfileName = orgfileName;
        this.fileName = fileName;

    }

    public GuestbookVO (String message_id, String guest_name, String password, String message) {
        this.message_id = message_id;
        this.guest_name = guest_name;
        this.password = password;
        this.message = message;
    }
    public GuestbookVO (String message_id, String guest_name, String password, String message, String fileName) {
        this.message_id = message_id;
        this.guest_name = guest_name;
        this.password = password;
        this.message = message;
        this.fileName = fileName;

    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
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

    public String getOrgfileName() {
        return orgfileName;
    }

    public void setOrgfileName(String orgfileName) {
        this.orgfileName = orgfileName;
    }

    @Override
    public String toString() {
        return "GuestBookVO{" + "message_id='" + message_id + '\'' + ", guest_name='" + guest_name + '\''
                + ", password='" + password + '\'' + ", message='" + message + '\'' + ", fileName='" + fileName + '\''
                + '}';
    }
}
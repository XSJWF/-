package background.CommentBean;

public class CommentBean {
    private int comment_id;
    private int comment_userid;
    private int comment_spid;
    private String comment_title;
    private String comment_content;
    private String comment_time;
    private String comment_username;

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getComment_userid() {
        return comment_userid;
    }

    public void setComment_userid(int comment_userid) {
        this.comment_userid = comment_userid;
    }

    public int getComment_spid() {
        return comment_spid;
    }

    public void setComment_spid(int comment_spid) {
        this.comment_spid = comment_spid;
    }

    public String getComment_title() {
        return comment_title;
    }

    public void setComment_title(String comment_title) {
        this.comment_title = comment_title;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public String getComment_username() {
        return comment_username;
    }

    public void setComment_username(String comment_username) {
        this.comment_username = comment_username;
    }
}

package modal;

import java.util.Date;

public class Share {
    private int id;
    private int userid;
    private int videoid;
    private String emails;
    private Date sharedate;
    private int number;

    public Share() {}

    public Share(int id, int userid, int videoid,
                 String emails, Date sharedate, int number) {
        this.id = id;
        this.userid = userid;
        this.videoid = videoid;
        this.emails = emails;
        this.sharedate = sharedate;
        this.number = number;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserid() { return userid; }
    public void setUserid(int userid) { this.userid = userid; }

    public int getVideoid() { return videoid; }
    public void setVideoid(int videoid) { this.videoid = videoid; }

    public String getEmails() { return emails; }
    public void setEmails(String emails) { this.emails = emails; }

    public Date getSharedate() { return sharedate; }
    public void setSharedate(Date sharedate) { this.sharedate = sharedate; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }
}

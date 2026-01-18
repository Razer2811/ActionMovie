package modal;

import java.util.Date;

public class Favorite {
    private int id;
    private int userid;
    private int videoid;
    private Date likedate;
    private boolean active;

    public Favorite() {}

    public Favorite(int id, int userid, int videoid, Date likedate, boolean active) {
        this.id = id;
        this.userid = userid;
        this.videoid = videoid;
        this.likedate = likedate;
        this.active = active;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserid() { return userid; }
    public void setUserid(int userid) { this.userid = userid; }

    public int getVideoid() { return videoid; }
    public void setVideoid(int videoid) { this.videoid = videoid; }

    public Date getLikedate() { return likedate; }
    public void setLikedate(Date likedate) { this.likedate = likedate; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}

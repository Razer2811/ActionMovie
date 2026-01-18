package modal;

import java.util.Date;

public class Users {
    private int id;
    private String username;
    private String pass;
    private String email;
    private String fullname;
    private Date birth;
    private String avatar;
    private Date startday;
    private boolean vip;
    private boolean admin;
    private boolean active;

    public Users() {}

    public Users(int id, String username, String pass, String email,
                String fullname, Date birth, String avatar,
                Date startday, boolean vip, boolean admin, boolean active) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.fullname = fullname;
        this.birth = birth;
        this.avatar = avatar;
        this.startday = startday;
        this.vip = vip;
        this.admin = admin;
        this.active = active;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public Date getBirth() { return birth; }
    public void setBirth(Date birth) { this.birth = birth; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public Date getStartday() { return startday; }
    public void setStartday(Date startday) { this.startday = startday; }

    public boolean isVip() { return vip; }
    public void setVip(boolean vip) { this.vip = vip; }

    public boolean isAdmin() { return admin; }
    public void setAdmin(boolean admin) { this.admin = admin; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}

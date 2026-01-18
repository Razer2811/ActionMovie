package bo;

import java.sql.Date;
import java.util.ArrayList;

import dao.UserDao;
import modal.Users;

public class UserBo {

    UserDao udao = new UserDao();

    public ArrayList<Users> getUser() throws Exception {
        return udao.getUser();
    }

    public Users ktdangnhap(String ten, String mk) throws Exception {
        return udao.ktdangnhap(ten, mk);
    }

    public Boolean checkTaiKhoanTonTai(String tendn) throws Exception {
        return udao.checkTaiKhoanTonTai(tendn);
    }


	public void themUser(String username, String pass, String email,
	                     String fullname, Date birth, String avatar,
	                     Date starday) throws Exception {
		udao.themUser(username, pass, email, fullname, birth, avatar, starday);
	}

    public void capNhatMatKhau(int id, String mkmoi) throws Exception {
        udao.capNhatMatKhau(id, mkmoi);
    }

    public Users timKhachHangBangEmailVaTenDN(String mail, String tendn) throws Exception {
        return udao.timKhachHangBangEmailVaTenDN(mail, tendn);
    }
}

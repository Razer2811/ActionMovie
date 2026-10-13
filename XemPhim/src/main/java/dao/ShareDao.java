package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modal.KetNoi;
import modal.Share;

public class ShareDao {

	// 1. Lấy danh sách chia sẻ
	public ArrayList<Share> getShare() throws Exception {
		ArrayList<Share> ds = new ArrayList<>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "SELECT * FROM SHARE";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();

		while(rs.next()) {
			ds.add(new Share(rs.getInt("id"),
					rs.getInt("userid"),
					rs.getInt("videoid"),
					rs.getString("emails"),
					rs.getDate("sharedate"), 
					rs.getInt("number")));
		}

		rs.close();
		kn.cn.close();
		return ds;
	}

	// 2. Thêm lượt chia sẻ (Share)
	public void insertShare(int userId, int videoId, String emails) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "INSERT INTO SHARE (userid, videoid, emails, sharedate, number) VALUES (?, ?, ?, GETDATE(), 1)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, userId);
		cmd.setInt(2, videoId);
		cmd.setString(3, emails);  // Ghi chú khi share
		cmd.executeUpdate();
		cmd.close();
		kn.cn.close();
	}

	// 3. Đếm tổng số Share của một video
	public int countShare(int videoId) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "SELECT COUNT(*) as total FROM SHARE WHERE videoid = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, videoId);
		ResultSet rs = cmd.executeQuery();

		int count = 0;
		if (rs.next()) {
			count = rs.getInt("total");
		}
		rs.close();
		kn.cn.close();
		return count;
	}

}
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modal.Favorite;
import modal.KetNoi;

public class FavoriteDao {

	// 1. Lấy danh sách yêu thích (Legacy)
	public ArrayList<Favorite> getFavorite() throws Exception {
		ArrayList<Favorite> ds = new ArrayList<>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "SELECT * FROM FAVORITE WHERE active = 1";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();

		while (rs.next()) {
			ds.add(new Favorite(rs.getInt("id"),
					rs.getInt("userid"),
					rs.getInt("videoid"),
					rs.getDate("likedate"),
					rs.getBoolean("active")
					));
		}
		rs.close();
		kn.cn.close();
		return ds;
	}

	// 2. Kiểm tra User đã like video chưa
	public boolean checkLiked(int userId, int videoId) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "SELECT * FROM FAVORITE WHERE userid = ? AND videoid = ? AND active = 1";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, userId);
		cmd.setInt(2, videoId);
		ResultSet rs = cmd.executeQuery();

		boolean result = rs.next();
		rs.close();
		kn.cn.close();
		return result;
	}

	// 3. Thêm lượt thích (Like)
	public void insertLike(int userId, int videoId) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "INSERT INTO FAVORITE (userid, videoid, likedate, active) VALUES (?, ?, GETDATE(), 1)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, userId);
		cmd.setInt(2, videoId);
		cmd.executeUpdate();
		cmd.close();
		kn.cn.close();
	}

	// 4. Xóa lượt thích (Unlike)
	public void deleteLike(int userId, int videoId) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "DELETE FROM FAVORITE WHERE userid = ? AND videoid = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, userId);
		cmd.setInt(2, videoId);
		cmd.executeUpdate();
		cmd.close();
		kn.cn.close();
	}


}
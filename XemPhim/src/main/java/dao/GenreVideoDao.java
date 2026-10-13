package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modal.GenreVideo;
import modal.KetNoi;

public class GenreVideoDao {
	public ArrayList<GenreVideo> getGenreVideo() throws Exception {
	      ArrayList<GenreVideo> ds = new ArrayList<>();
	      KetNoi kn = new KetNoi();
	      kn.ketnoi();
	      String sql = "SELECT * FROM GENREVIDEO";
	      PreparedStatement cmd = kn.cn.prepareStatement(sql);
	      ResultSet rs = cmd.executeQuery();

	      while(rs.next()) {
	         ds.add(new GenreVideo(rs.getInt("id"), rs.getString("title")));
	      }

	      rs.close();
	      kn.cn.close();
	      return ds;
	   }

	   public GenreVideo timtheoID(Integer id) throws Exception {
	      GenreVideo g = null;
	      KetNoi kn = new KetNoi();
	      kn.ketnoi();
	      String sql = "SELECT * FROM GENREVIDEO WHERE id = ?";
	      PreparedStatement cmd = kn.cn.prepareStatement(sql);
	      cmd.setInt(1, id);
	      ResultSet rs = cmd.executeQuery();
	      if (rs.next()) {
	         g = new GenreVideo(rs.getInt("id"), rs.getString("title"));
	      }

	      rs.close();
	      kn.cn.close();
	      return g;
	   }
	   public int themLoai(String maLoai, String tenLoai) throws Exception {
			KetNoi kn = new KetNoi();
			kn.ketnoi();
			String sql = "INSERT INTO GENREVIDEO(id , title) VALUES(?, ?)";
			PreparedStatement cmd = kn.cn.prepareStatement(sql);
			cmd.setString(1, maLoai);
			cmd.setString(2, tenLoai);
			int kq = cmd.executeUpdate();
			cmd.close();
			kn.cn.close();
			return kq;
		}

		public int suaLoai(String ma, String ten) throws Exception {
			KetNoi kn = new KetNoi();
			kn.ketnoi();
			String sql = "UPDATE GENREVIDEO SET title = ? WHERE id = ?";
			PreparedStatement cmd = kn.cn.prepareStatement(sql);
			cmd.setString(1, ten);
			cmd.setString(2, ma);
			int kq = cmd.executeUpdate();
			cmd.close();
			kn.cn.close();
			return kq;
		}

		public int xoaLoai(String ma) throws Exception {
			KetNoi kn = new KetNoi();
			kn.ketnoi();
			String sql = "DELETE FROM GENREVIDEO WHERE id = ?";
			PreparedStatement cmd = kn.cn.prepareStatement(sql);
			cmd.setString(1, ma);
			int kq = cmd.executeUpdate();
			cmd.close();
			kn.cn.close();
			return kq;
		}
}

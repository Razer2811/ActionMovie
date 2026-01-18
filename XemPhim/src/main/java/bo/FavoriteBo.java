package bo;

import java.util.ArrayList;

import dao.FavoriteDao;
import modal.Favorite;

public class FavoriteBo {

	private FavoriteDao fdao = new FavoriteDao();

	// 1. Lấy danh sách yêu thích (Legacy)
	public ArrayList<Favorite> getFavorite() throws Exception {
		return fdao.getFavorite();
	}

	// 2. Kiểm tra User đã like video chưa
	public boolean checkLiked(int userId, int videoId) throws Exception {
		return fdao.checkLiked(userId, videoId);
	}

	// 3. Thêm lượt thích (Like)
	public void insertLike(int userId, int videoId) throws Exception {
		fdao.insertLike(userId, videoId);
	}

	// 4. Xóa lượt thích (Unlike)
	public void deleteLike(int userId, int videoId) throws Exception {
		fdao.deleteLike(userId, videoId);
	}

}
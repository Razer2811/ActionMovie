package bo;

import java.util.ArrayList;

import dao.ShareDao;
import modal.Share;

public class ShareBo {

	private ShareDao sdao = new ShareDao();

	// 1. Lấy danh sách chia sẻ
	public ArrayList<Share> findAll() throws Exception {
		return sdao.getShare();
	}

	// 2. Thêm lượt chia sẻ (Share)
	public void insertShare(int userId, int videoId, String emails) throws Exception {
		sdao.insertShare(userId, videoId, emails);
	}

	// 3. Đếm tổng số Share của một video
	public int countShare(int videoId) throws Exception {
		return sdao.countShare(videoId);
	}

}
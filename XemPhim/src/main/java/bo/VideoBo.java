package bo;

import java.util.ArrayList;

import dao.VideoDao;
import modal.Video;

public class VideoBo {

	private VideoDao vdao = new VideoDao();
	private final int PAGE_SIZE = 15;

	// 1. Lấy tất cả video
	public ArrayList<Video> getVideo() throws Exception {
		return vdao.getVideo();
	}

	// 2. Tìm kiếm theo tên
	public ArrayList<Video> timTen(String key) throws Exception {
		return vdao.timTen(key);
	}

	// 3. Tìm kiếm theo thể loại (ID)
	public ArrayList<Video> timMa(int genreId) throws Exception {
		return vdao.getVideosByGenreID(genreId);
	}

	// 4. Thêm Video mới
	public int themVideo(Video v) throws Exception {
		return vdao.themVideo(v);
	}

	// 5. Xóa Video
	public void xoaVideo(String mavideo) throws Exception {
		vdao.xoaVideo(mavideo);
	}

	// 6. Lấy phim theo trang (phân trang)
	public ArrayList<Video> getVideosByPage(int page) throws Exception {
		return vdao.getVideosByPage(page, PAGE_SIZE);
	}

	// 7. Tính tổng số trang
	public int getTotalPages() throws Exception {
		int totalRecords = vdao.countAll();
		int pages = (int) Math.ceil((double) totalRecords / PAGE_SIZE);
		return pages == 0 ? 1 : pages;
	}

	// 8. Lấy Video theo ID
	public Video getVideoById(int id) throws Exception {
		return vdao.getVideoById(id);
	}

	// 9. Tăng lượt xem
	public void updateView(int id) throws Exception {
		vdao.updateView(id);
	}

	// 10. Lấy video liên quan
	public ArrayList<Video> getRelatedVideos(int idGenre, int currentVideoId) throws Exception {
		return vdao.getRelatedVideos(idGenre, currentVideoId);
	}

	// 11. Lấy tổng lượt thích
	public int getTotalFavorites(int videoId) throws Exception {
		return vdao.getTotalFavorites(videoId);
	}

	// 12. Lấy tổng lượt chia sẻ
	public int getTotalShares(int videoId) throws Exception {
		return vdao.getTotalShares(videoId);
	}
    public ArrayList<Video> getLikeVideosByUserId(int userId) throws Exception {
    	return vdao.getLikedVideosByUserId(userId);
    }
    public ArrayList<Video> getShareVideosByUserId(int userId) throws Exception {
    	return vdao.getShareVideosByUserId(userId);
    }
    	
}
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FavoriteDao;
import dao.GenreVideoDao;
import dao.ShareDao;
import dao.VideoDao;
import modal.Users;
import modal.Video;

@WebServlet("/DetailMovie")
public class DetailMovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// --- GET: Dùng để hiển thị trang chi tiết phim ---
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		try {
			// 1. Lấy ID video từ URL
			String idStr = request.getParameter("id");
			if (idStr == null || idStr.isEmpty()) {
				response.sendRedirect("Home");
				return;
			}

			// 2. Parse ID thành integer (Tránh SQL Injection)
			int videoId;
			try {
				videoId = Integer.parseInt(idStr);
			} catch (NumberFormatException e) {
				response.sendRedirect("Home");
				return;
			}

			// 3. Khởi tạo DAO
			VideoDao vdao = new VideoDao();
			GenreVideoDao gdao = new GenreVideoDao();
			FavoriteDao fdao = new FavoriteDao();

			// 4. Tăng lượt xem và lấy thông tin phim
			vdao.updateView(videoId);
			Video video = vdao.getVideoById(videoId);

			if (video != null) {
				// 5. Lấy danh sách phim liên quan
				ArrayList<Video> listRelated = vdao.getRelatedVideos(video.getIdgenre(), video.getId());
				request.setAttribute("listVideo", listRelated);

				// 6. Kiểm tra User đã Like chưa
				HttpSession session = request.getSession();
				Users user = (Users) session.getAttribute("user");
				boolean isLiked = false;
				if (user != null) {
					isLiked = fdao.checkLiked(user.getId(), video.getId());
				}
				request.setAttribute("isLiked", isLiked);
			}

			// 7. Lấy menu thể loại
			request.setAttribute("dsGenre", gdao.getGenreVideo());
			request.setAttribute("movie", video);
			request.getRequestDispatcher("DetailMovie.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("Home");
		}
	}

	// --- POST: Dùng để xử lý AJAX (Like / Share) ---
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// Dùng PrintWriter để trả về text cho AJAX
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain; charset=UTF-8");

		try {
			// 1. Lấy tham số
			String action = request.getParameter("action");
			String idStr = request.getParameter("id");
			
			// 2. Parse video ID
			int videoId;
			try {
				videoId = Integer.parseInt(idStr);
				if (videoId <= 0) {
					response.setStatus(400);
					out.print("error");
					return;
				}
			} catch (NumberFormatException e) {
				response.setStatus(400);
				out.print("error");
				return;
			}

			// 3. Lấy user từ session
			HttpSession session = request.getSession();
			Users user = (Users) session.getAttribute("user");

			// 4. Khởi tạo DAO
			FavoriteDao fdao = new FavoriteDao();
			ShareDao sdao = new ShareDao();

			// === XỬ LÝ LIKE ===
			if ("like".equals(action)) {
				if (user == null) {
					response.setStatus(401);  // Chưa đăng nhập
					out.print("unauthorized");
					return;
				}

				try {
					boolean isLiked = fdao.checkLiked(user.getId(), videoId);
					if (isLiked) {
						fdao.deleteLike(user.getId(), videoId);
						out.print("unliked");
					} else {
						fdao.insertLike(user.getId(), videoId);
						out.print("liked");
					}
				} catch (Exception e) {
					e.printStackTrace();
					response.setStatus(500);
					out.print("error");
				}
			}
			
			// === XỬ LÝ SHARE ===
			else if ("share".equals(action)) {
				try {
					// ✅ FIX: Kiểm tra user và lấy email
					if (user == null) {
						response.setStatus(401);
						out.print("unauthorized");
						return;
					}

					int userId = user.getId();
					String userEmail = user.getEmail();  // ✅ Lấy email của user

					// ✅ Kiểm tra videoId hợp lệ trước khi insert
					if (videoId <= 0) {
						response.setStatus(400);
						out.print("error");
						return;
					}

					// ✅ Thêm share vào database với email của user
					sdao.insertShare(userId, videoId, userEmail);
					
					// ✅ Đếm lại tổng share để cập nhật giao diện
					int newCount = sdao.countShare(videoId);
					
					// ✅ Kiểm tra newCount hợp lệ
					if (newCount < 0) {
						response.setStatus(500);
						out.print("error");
					} else {
						out.print(newCount);
					}
				} catch (Exception e) {
					e.printStackTrace();
					response.setStatus(500);
					out.print("error");
				}
			}
			else {
				response.setStatus(400);
				out.print("error");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			out.print("error");
		} finally {
			out.flush();
			out.close();
		}
	}
}
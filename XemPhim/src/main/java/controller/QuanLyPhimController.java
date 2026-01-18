package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GenreVideoDao;
import dao.VideoDao;
import modal.GenreVideo;
import modal.Users;
import modal.Video;

@WebServlet("/QuanLyPhim")
public class QuanLyPhimController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");

            // 1. Check Admin
            HttpSession session = request.getSession();
            Users user = (Users) session.getAttribute("user");
            if (user == null || !user.isAdmin()) {
                response.sendRedirect("Home");
                return;
            }

            VideoDao videoDao = new VideoDao();
            GenreVideoDao genreDao = new GenreVideoDao();

            // 2. Lấy danh sách thể loại cho Dropdown
            ArrayList<GenreVideo> listGenre = genreDao.getGenreVideo();
            request.setAttribute("dsGenre", listGenre);

            // 3. Lấy ID thể loại từ request
            // --- ĐÂY LÀ DÒNG CẦN SỬA ---
            // Đổi "typeMovie" thành "genre" để khớp với name="genre" bên JSP
            String genreIdStr = request.getParameter("genre"); 
            
            int genreId = -1; 
            
            if (genreIdStr != null && !genreIdStr.isEmpty()) {
                try {
                    genreId = Integer.parseInt(genreIdStr);
                } catch (NumberFormatException e) {
                    genreId = -1; // Nếu lỗi format thì mặc định là tất cả
                }
            }

            // 4. Lấy dữ liệu và Lọc
            ArrayList<Video> allVideos = videoDao.getVideo();
            ArrayList<Video> resultList = new ArrayList<>();

            if (allVideos != null) {
                // Nếu genreId = -1 (Tất cả) -> Lấy hết
                if (genreId == -1) {
                    resultList = allVideos;
                } else {
                    // Nếu có chọn thể loại -> Lọc
                    for (Video v : allVideos) {
                        if (v.getIdgenre() == genreId) {
                            resultList.add(v);
                        }
                    }
                }
            }

            // 5. Trả dữ liệu về JSP
            request.setAttribute("dsvideo", resultList);
            request.setAttribute("check", genreId); // Để giữ trạng thái selected của dropdown
            
            // Set session active = true (để hiện nút Ẩn màu đỏ)
            session.setAttribute("active", true);

            request.getRequestDispatcher("QuanLyPhim.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Home");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
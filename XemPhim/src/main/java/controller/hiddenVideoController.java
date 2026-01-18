package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.VideoDao;
import modal.Users;

@WebServlet("/hiddenVideo") // URL này khớp với href="hiddenVideo?id=..."
public class hiddenVideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // 1. Kiểm tra quyền Admin (Bảo mật)
            HttpSession session = request.getSession();
            Users user = (Users) session.getAttribute("user");
            
            // Nếu không phải admin thì đá về trang chủ
            if (user == null || !user.isAdmin()) {
                response.sendRedirect("Home");
                return;
            }

            // 2. Lấy ID phim từ URL
            String idStr = request.getParameter("id");
            
            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr);
                
                // 3. Gọi DAO để ẩn (Set active = 0)
                VideoDao dao = new VideoDao();
                dao.unActiveVideo(id); // Gọi hàm số 16 trong VideoDao
            }

            // 4. Load lại trang Quản Lý Phim (để thấy phim biến mất khỏi danh sách hiện hành)
            response.sendRedirect("QuanLyPhim");

        } catch (Exception e) {
            e.printStackTrace();
            // Nếu lỗi cũng quay về trang quản lý
            response.sendRedirect("QuanLyPhim");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
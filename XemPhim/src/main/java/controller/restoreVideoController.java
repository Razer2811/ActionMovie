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

@WebServlet("/restoreVideo") // URL này khớp với href="restore?id=..."
public class restoreVideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // 1. Kiểm tra quyền Admin
            HttpSession session = request.getSession();
            Users user = (Users) session.getAttribute("user");
            
            if (user == null || !user.isAdmin()) {
                response.sendRedirect("Home");
                return;
            }

            // 2. Lấy ID phim
            String idStr = request.getParameter("id");
            
            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr);
                
                // 3. Gọi DAO để khôi phục (Set active = 1)
                VideoDao dao = new VideoDao();
                dao.restoreVideo(id); // Gọi hàm số 17 trong VideoDao
            }

            // 4. Load lại trang UnActive (để thấy phim biến mất khỏi danh sách ẩn)
            response.sendRedirect("UnActive");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("UnActive");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
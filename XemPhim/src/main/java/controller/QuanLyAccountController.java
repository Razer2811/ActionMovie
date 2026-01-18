package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import modal.Users;

@WebServlet("/QuanLyAccount")
public class QuanLyAccountController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");

            // 1. Kiểm tra quyền Admin
            HttpSession session = request.getSession();
            Users admin = (Users) session.getAttribute("user");
            if (admin == null || !admin.isAdmin()) {
                response.sendRedirect("Home"); 
                return;
            }

            UserDao dao = new UserDao();
            String action = request.getParameter("action");
            String idStr = request.getParameter("id"); // Lấy ID dạng String trước

            // 2. Xử lý các hành động GET (Delete, Edit)
            if (action != null && idStr != null) {
                try {
                    int id = Integer.parseInt(idStr); // ÉP KIỂU SANG INT

                    if (action.equals("delete")) {
                        if (id != admin.getId()) {
                            dao.delete(id); // Gọi hàm delete nhận int
                            request.setAttribute("message", "Đã xóa tài khoản ID: " + id);
                        } else {
                            request.setAttribute("error", "Không thể xóa tài khoản đang đăng nhập!");
                        }
                    } else if (action.equals("edit")) {
                        // Gọi hàm findById nhận int
                        Users userToEdit = dao.findById(id); 
                        request.setAttribute("user", userToEdit);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID không hợp lệ: " + idStr);
                }
            }

            // 3. Load danh sách và forward
            List<Users> listUsers = dao.getUser();
            request.setAttribute("listUsers", listUsers);
            request.getRequestDispatcher("Account.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("HomeAdmin");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            
            UserDao dao = new UserDao();
            String action = request.getParameter("action");
            
            // 4. Xử lý hành động Update từ Form
            if ("update".equals(action)) {
                String idStr = request.getParameter("id");
                String fullname = request.getParameter("fullname");
                String email = request.getParameter("email");
                boolean isAdmin = request.getParameter("admin") != null;
                
                try {
                    int id = Integer.parseInt(idStr); // ÉP KIỂU INT

                    Users u = new Users();
                    u.setId(id); // Set ID là int
                    u.setFullname(fullname);
                    u.setEmail(email);
                    u.setAdmin(isAdmin);
                    
                    dao.update(u); 
                    request.setAttribute("message", "Cập nhật thành công user ID: " + id);
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Lỗi ID không phải số!");
                }
            }
            
            doGet(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi: " + e.getMessage());
            doGet(request, response);
        }
    }
}
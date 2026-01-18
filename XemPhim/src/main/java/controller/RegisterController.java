package controller;

import java.io.IOException;
import java.sql.Date; // Lưu ý import java.sql.Date

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

@WebServlet("/Register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Nếu ai đó cố tình vào link này bằng method GET, chuyển về trang đăng ký
        RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 1. Lấy dữ liệu từ Form
        String user = request.getParameter("username");
        String pass = request.getParameter("pass");
        String rePass = request.getParameter("repass");
        String email = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        String birthStr = request.getParameter("birth");

        // 2. Đẩy ngược dữ liệu lại request để giữ thông tin trên Form nếu có lỗi
        request.setAttribute("username", user);
        request.setAttribute("email", email);
        request.setAttribute("fullname", fullname);
        request.setAttribute("birth", birthStr);

        String baoLoi = "";
        UserDao dao = new UserDao();

        // 3. Kiểm tra logic
        // Kiểm tra username tồn tại
        try {
			if (dao.checkTaiKhoanTonTai(user)) {
			    baoLoi += "Tên đăng nhập đã tồn tại! <br/>";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Kiểm tra khớp mật khẩu
        if (pass == null || !pass.equals(rePass)) {
            baoLoi += "Mật khẩu nhập lại không khớp! <br/>";
        }

        if (baoLoi.length() > 0) {
            // Có lỗi -> Quay lại trang đăng ký
            request.setAttribute("tb", baoLoi);
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } else {
            // Không có lỗi -> Tiến hành xử lý dữ liệu và lưu
            try {
                // Mã hóa mật khẩu trước khi lưu (Giống code 1)
                String passMaHoa = util.MaHoa.toSHA1(pass); 
                
                Date birth = (birthStr != null && !birthStr.isEmpty()) ? Date.valueOf(birthStr) : new Date(System.currentTimeMillis());
                String avatar = "macdinh.jpg";
                Date starday = new Date(System.currentTimeMillis());

                dao.themUser(user, passMaHoa, email, fullname, birth, avatar, starday);

                request.setAttribute("tb", "Đăng ký thành công!");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("tb", "Lỗi hệ thống, vui lòng thử lại sau.");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            }
        }
    }
}
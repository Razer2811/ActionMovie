package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.UserBo;
import modal.Users;
import util.MaHoa;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            String username = request.getParameter("username");
            String pass = request.getParameter("pass");

            String passMaHoa = MaHoa.toSHA1(pass);

            HttpSession session = request.getSession();
            String url = "";

            if (username != null && pass != null) {
                UserBo usbo = new UserBo();
                Users us = usbo.ktdangnhap(username, passMaHoa);

                if (us != null) {
                    session.setAttribute("user", us);
                    if (us.isAdmin()) {
                        response.sendRedirect("HomeAdmin");
                    } else {
                        response.sendRedirect("Home");
                    }
                    return;
                } else {
                    // Đăng nhập thất bại
                    request.setAttribute("tb", "Tên đăng nhập hoặc mật khẩu không đúng!");
                    url = "/Login.jsp";
                }
            } else {
                url = "/Login.jsp";
            }
            
            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

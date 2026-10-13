package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/Chatbot")
public class ChatbotServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Gson GSON = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        
        String message = request.getParameter("message");
        JsonObject response_json = processQuestion(message);
        
        PrintWriter out = response.getWriter();
        out.print(GSON.toJson(response_json));
        out.flush();
        out.close();
    }
    
    private JsonObject processQuestion(String question) {
        JsonObject result = new JsonObject();
        
        if (question == null || question.trim().isEmpty()) {
            result.addProperty("answer", "Vui lòng nhập câu hỏi!");
            return result;
        }
        
        question = question.toLowerCase().trim();
        
        if (question.contains("phim mới")) {
            result.addProperty("answer", "🎬 Xem tất cả phim mới cập nhật!");
            result.addProperty("link", "Home?page=1");
            result.addProperty("linkText", "Xem Phim Mới");
            return result;
        }
        
        if (question.contains("hot") || question.contains("trending") || question.contains("thịnh hành")) {
            result.addProperty("answer", "🔥 Các phim đang được xem nhiều nhất!");
            result.addProperty("link", "Home?page=1");
            result.addProperty("linkText", "Xem Phim Hot");
            return result;
        }
        
        if (question.contains("tìm") || question.contains("search") || question.contains("kiếm")) {
            result.addProperty("answer", "🔍 Dùng thanh tìm kiếm ở header để tìm phim yêu thích!");
            result.addProperty("link", "Home");
            result.addProperty("linkText", "Về Trang Chủ");
            return result;
        }
        
        if (question.contains("yêu thích") || question.contains("like") || question.contains("thích")) {
            result.addProperty("answer", "❤️ Click ❤️ ở trang chi tiết phim để thêm vào yêu thích!");
            return result;
        }
        
        if (question.contains("chia sẻ") || question.contains("share")) {
            result.addProperty("answer", "📤 Chia sẻ phim với bạn bè qua nút Share!");
            result.addProperty("link", "ShareMovie");
            result.addProperty("linkText", "Phim Đã Chia Sẻ");
            return result;
        }
        
        if (question.contains("đăng nhập") || question.contains("login")) {
            result.addProperty("answer", "🔐 Đăng nhập để truy cập tất cả tính năng!");
            result.addProperty("link", "Login");
            result.addProperty("linkText", "Đăng Nhập");
            return result;
        }
        
        if (question.contains("đăng ký") || question.contains("register") || question.contains("tài khoản")) {
            result.addProperty("answer", "📝 Tạo tài khoản mới để bắt đầu!");
            result.addProperty("link", "Register");
            result.addProperty("linkText", "Đăng Ký");
            return result;
        }
        
        if (question.contains("thể loại") || question.contains("genre") || question.contains("category")) {
            result.addProperty("answer", "🎭 Chọn thể loại yêu thích của bạn!");
            result.addProperty("link", "GenreVideo");
            result.addProperty("linkText", "Xem Danh Mục Thể Loại");
            return result;
        }
        
        if (question.contains("quản lý") || question.contains("admin")) {
            result.addProperty("answer", "⚙️ Admin có thể quản lý phim và tài khoản!");
            result.addProperty("link", "QuanLyPhim");
            result.addProperty("linkText", "Quản Lý Phim");
            return result;
        }
        
        if (question.contains("chất lượng") || question.contains("quality") || question.contains("hd")) {
            result.addProperty("answer", "📺 Phim có sẵn ở các chất lượng: HD, FHD, 4K!");
            result.addProperty("link", "Home");
            result.addProperty("linkText", "Xem Phim");
            return result;
        }
        
        if (question.contains("giá") || question.contains("price") || question.contains("cost")) {
            result.addProperty("answer", "💰 Giá phim tùy thuộc vào chất lượng và nội dung!");
            result.addProperty("link", "Home");
            result.addProperty("linkText", "Xem Chi Tiết");
            return result;
        }
        
        result.addProperty("answer", "❓ Xin lỗi, tôi không hiểu. Hãy hỏi về: phim mới, phim hot, tìm phim, yêu thích, chia sẻ, đăng nhập, đăng ký...");
        return result;
    }
}
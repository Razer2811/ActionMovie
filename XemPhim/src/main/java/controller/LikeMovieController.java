package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.GenreVideoBo; // Nếu có Genre BO thì dùng, ko thì dùng DAO
import bo.VideoBo;
import dao.GenreVideoDao;
import modal.GenreVideo;
import modal.Users;
import modal.Video;

@WebServlet("/LikeMovie")
public class LikeMovieController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");

            HttpSession session = request.getSession();
            Users us = (Users) session.getAttribute("user");

            if (us == null) {
                response.sendRedirect("Login");
                return;
            }

            VideoBo vbo = new VideoBo();
            GenreVideoDao gdao = new GenreVideoDao(); 

            ArrayList<Video> likedVideos = vbo.getLikeVideosByUserId(us.getId());
            
            ArrayList<GenreVideo> listGenre = gdao.getGenreVideo();

            request.setAttribute("dsvideo", likedVideos);
            request.setAttribute("dsGenre", listGenre); 
            
            request.getRequestDispatcher("LikeMovie.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
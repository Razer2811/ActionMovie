package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.GenreVideoBo;
import bo.VideoBo;
import dao.GenreVideoDao;
import modal.GenreVideo;
import modal.Users;
import modal.Video;

/**
 * Servlet implementation class ShareMovieController
 */
@WebServlet("/ShareMovie")
public class ShareMovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");

			HttpSession session = request.getSession();
			Users us = (Users)session.getAttribute("user");
			
			if(us == null) {
				response.sendRedirect("Login");
				return;
			}
			
			VideoBo vbo = new VideoBo();
			GenreVideoBo gbo = new GenreVideoBo(); 
			
			ArrayList<Video> shareVideos = vbo.getShareVideosByUserId(us.getId());
			ArrayList<GenreVideo> listGenre = gbo.getGenreVideo();
			
			request.setAttribute("dsvideo", shareVideos);
			request.setAttribute("dsGenre", listGenre);
			
			request.getRequestDispatcher("ShareMovie.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi: " + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

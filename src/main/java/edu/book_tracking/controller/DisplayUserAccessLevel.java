package edu.book_tracking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;

import edu.book_tracking.model.UserModel;
import edu.book_tracking.service.LevelDAO;

@WebServlet("/DisplayUserAccessLevelServlet")
public class DisplayUserAccessLevel extends HttpServlet {

	protected void  doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LevelDAO services = new LevelDAO();
		List<UserModel>  List = services.getusersaccess();
		request.setAttribute("useraccess", List);
		RequestDispatcher dispatcher= request.getRequestDispatcher("/DisplayUserAccessLevel.jsp");
		dispatcher.forward(request,response);		
	}

}

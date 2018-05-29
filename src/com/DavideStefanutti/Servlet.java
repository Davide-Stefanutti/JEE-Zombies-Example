package com.DavideStefanutti;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		ZombieManager zb = (ZombieManager) request.getSession().getAttribute("zombieManager");
		String res = request.getParameter("btn");
		
		if(zb == null || res.equals("reset")) {
			zb = new ZombieManager();
			zb.newZombie();
		}else {
			zb.run();
		}
		
		
		List<Zombie> zombies = zb.getZombies();
		
		//SAVE DATA
		zb.DBsetup();
		zb.DBsave(zombies);
		zb.DBexit();
		//---------
		
		request.getSession().setAttribute("zombieManager", zb);
		request.getSession().setAttribute("zombies", zombies);
		request.getSession().setAttribute("win_status", zb.getWinStatus());
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

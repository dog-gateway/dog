/**
 * 
 */
package it.polito.elite.dog.web.admin.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bonino
 *
 */
public class StatusServlet extends HttpServlet
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public StatusServlet()
	{
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		resp.getOutputStream().println("<h1>Hello world</h1><img src=\"img/glyphicons-halflings.png\"><p>"+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()));
	}
	
	
	
}

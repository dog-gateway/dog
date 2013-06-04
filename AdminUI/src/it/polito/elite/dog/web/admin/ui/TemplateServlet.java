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
public class TemplateServlet extends HttpServlet
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public TemplateServlet()
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		TemplatePartEnum part = TemplatePartEnum.valueOf(req.getParameter("part"));
		
		switch (part)
		{
			case HEADER:
			{
				resp.getOutputStream().println(this.getTemplateHeader());
				break;
			}
			case FOOTER:
			{
				resp.getOutputStream().println(this.getTemplateFooter());
			}
		}
		
	}
	
	private String getTemplateHeader()
	{
		StringBuffer responseBuffer = new StringBuffer();
		
		// build the response
		responseBuffer
				.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
		responseBuffer.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
		responseBuffer.append("\t<head>\n");
		responseBuffer.append("\t\t<title>\n");
		responseBuffer.append("\t\t</title>\n");
		responseBuffer.append("\t\t<meta name=\"description\" content=\"\" />\n");
		responseBuffer.append("\t\t<meta name=\"author\" content=\"\" />\n");
		responseBuffer.append("\t\t<!-- HTML5 shim, for IE6-8 support of HTML elements -->\n");
		responseBuffer.append("\t\t<!--[if lt IE 9]>\n");
		responseBuffer.append("\t\t<script src=\"http://html5shim.googlecode.com/svn/trunk/html5.js\"></script>\n");
		responseBuffer.append("\t\t<![endif]-->\n");
		
		responseBuffer.append("\t\t<!-- CSS Styles -->\n");
		responseBuffer.append("\t\t<link href=\"css/bootstrap.css\" rel=\"stylesheet\"/>\n");
		responseBuffer.append("\t\t<link href=\"css/bootstrap-responsive.css\" rel=\"stylesheet\"/>\n");
		//responseBuffer.append("\t\t<link href=\"/css/custom.css\" rel=\"stylesheet\"/>\n");
		
		responseBuffer.append("\t\t<!-- Fav and touch icons\n");
		responseBuffer.append("\t\t<link rel=\"shortcut icon\" href=\"img/favicon.ico\"/>\n");
		responseBuffer.append("\t\t<link rel=\"apple-touch-icon\" href=\"img/apple-touch-icon.png/\">\n");
		responseBuffer
				.append("\t\t<link rel=\"apple-touch-icon\" sizes=\"72x72\" href=\"img/apple-touch-icon-72x72.png/\">\n");
		responseBuffer
				.append("\t\t<link rel=\"apple-touch-icon\" sizes=\"114x114\" href=\"img/apple-touch-icon-114x114.png/\">-->\n");
		
		responseBuffer.append("\t</head>\n");
		responseBuffer.append("\t<body>");
		
		return responseBuffer.toString();
	}
	
	private String getTemplateFooter()
	{
		StringBuffer responseBuffer = new StringBuffer();
		responseBuffer.append("\n");
		responseBuffer.append("\t\t<script src=\"js/jquery.js\"></script>\n");
		responseBuffer.append("\t\t<script src=\"js/ajax.js\"></script>\n");
		responseBuffer.append("\t\t<script src=\"js/bootstrap.js\"></script>\n");
		responseBuffer.append("\t</body>\n</html>\n");
		return responseBuffer.toString();
	}
}

package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	@WebServlet("/input")
	public class InputServlet extends HttpServlet {
	
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			// obtain parameter values
			String input = request.getParameter("input");
			
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<head><meta charset=\"UTF-8\"><title>Result</title></head>");
			out.println("<body>");

			out.println(input);
			out.printf("<hr/>");
			out.printf("<a href='input.html'>back</a>");

			out.println("</body></html>");
		}
		
		/*protected void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			// obtain parameter values
			String input = request.getParameter("input");
			
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<head><meta charset=\"UTF-8\"><title>Result</title></head>");
			out.println("<body>");

			out.println(input);
			out.printf("<hr/>");
			out.printf("<a href='input.html'>back</a>");

			out.println("</body></html>");
		}*/

	}


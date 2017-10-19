package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc1")
public class Calc extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int x = 0;
		int y = 0;
		int result = 0;
		String op = "";
		try {

			x = Integer.parseInt(req.getParameter("x"));
			y = Integer.parseInt(req.getParameter("y"));
			op = req.getParameter("op");
			switch (op) {
			
			case "-":
				result = x - y;
				break;
			case "+":
				result = x +y;
				break;
			case "*":
				result = x * y;
				break;
			case "/":
				result = x / y;
				break;
				default: result = 0;
				break;
			}
			System.out.println(
					"http://localhost:8080/Practice9/calc?" + "x=" + x + "&y=" + y + "&op=" + req.getParameter("op") + " ===> " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

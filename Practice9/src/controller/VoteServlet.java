package controller;

import entity.Vote;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class VoteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = request.getServletContext();
        String[] list = servletContext.getInitParameter("list").split(" ");


        PrintWriter writer = response.getWriter();
        writer.write("<!DOCTYPE HTML><html><head><title></title></head><body>");
        writer.write("<form name=\"vote\" method=\"post\" action=\"vote\"><table border=\"1\">");

        for (String item : list) {
            writer.write("<tr><td>");
            writer.write("<input type=\"radio\" name=\"vote\" value=\"" + item + "\">" + item);
            writer.write("</tr></td>");
        }

        writer.write("</table>Name: <input type=\"text\" name=\"name\">");

        writer.write("<input type=\"submit\"></form>");
        writer.write("</body></html>");
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = request.getServletContext();
        String[] list = servletContext.getInitParameter("list").split(" ");

        Map<String, Vote> votes;

        if (servletContext.getAttribute("votes") == null) {
            votes = new HashMap<String, Vote>();

            for (String item : list) {
                votes.put(item, new Vote());
            }

            servletContext.setAttribute("votes", votes);
        } else {
            votes = (Map<String, Vote>) servletContext.getAttribute("votes");
        }

        String vote = request.getParameter("vote");
        String name = request.getParameter("name");

        Set<String> voted;
        HttpSession session = request.getSession();

        if (session.getAttribute("voted") == null) {
            voted = new HashSet<String>();
            session.setAttribute("voted", voted);
        } else {
            voted = (Set<String>) session.getAttribute("voted");
        }

        if (voted.contains(name)) {
            request.getRequestDispatcher("info.html").forward(request, response);
        } else {
            voted.add(name);
        }

        System.out.println(voted);

        votes.get(vote).vote(name);

        PrintWriter writer = response.getWriter();
        writer.write("<!DOCTYPE HTML><html><head><title></title></head><body>");
        writer.write("<table border=\"1\">");

        for (Map.Entry<String, Vote> entry : votes.entrySet()) {
            writer.write("<tr><td>" + entry.getKey() + "</td><td>" + entry.getValue().getCount() + "</td><td>");

            List<String> voters = entry.getValue().getVoters();
            for (int i = 0; i <  voters.size(); i++) {
                if (i == voters.size() - 1) {
                    writer.write(voters.get(i));
                } else {
                    writer.write(voters.get(i) + ", ");
                }
            }

            writer.write("</td></tr>");
        }

        writer.write("</table>");
        writer.write("</body></html>");
        writer.close();
    }
}

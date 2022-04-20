package com.maktab.servlet.crud.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import repositories.impl.UsersRepoImpl;
import service.UserService;
import service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/read-user")
public class ReadUserServlet extends HttpServlet {
    private final UserService service = new UserServiceImpl(new UsersRepoImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>User Found</title></head><body>");

        User toFind;
        try {
            if (req.getParameter("idOrUsername").equals("username")) {
                toFind = service.findByUsername(req.getParameter("username"));
            } else if (req.getParameter("idOrUsername").equals("id")) {
                toFind = service.findById(Integer.parseInt(req.getParameter("id")));
            } else throw new ReadingMethodException();
            if (toFind != null) {
                out.println("ID: " + toFind.getId() + "\n");
                out.println("First Name: " + toFind.getFirstName() + "\n");
                out.println("Last Name: " + toFind.getLastName() + "\n");
                out.println("Username: " + toFind.getUsername() + "\n");
            } else {
                out.println("user not found!");
            }
        } catch (NumberFormatException e) {
            out.println("id parameter must be specified and be an Integer");
        } catch (ReadingMethodException e) {
            out.println("idOrUsername method must be specified correctly by (id/username)");
        } finally {
            out.println("</body></html>");
        }
    }
}

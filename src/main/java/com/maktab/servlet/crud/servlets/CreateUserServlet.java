package com.maktab.servlet.crud.servlets;

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

@WebServlet("/create-user")
public class CreateUserServlet extends HttpServlet {
    private final UserService service = new UserServiceImpl(new UsersRepoImpl());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User();
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));

        try {
            var toInsert = service.insert(user);

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<html><head><title>Create User</title></head><body>");

            out.println("<p>User created with ID: ");
            out.println(toInsert.getId());
            out.println("</p>");

            out.println("</body></html>");
        } catch (Exception e) {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<html><head><title>Error</title></head><body>");

            out.println("<p>Something went wrong with the database</p>");

            out.println("</body></html>");
        }
    }
}

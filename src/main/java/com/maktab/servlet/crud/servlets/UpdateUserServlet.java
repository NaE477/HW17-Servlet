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

@WebServlet("/update-user")
public class UpdateUserServlet extends HttpServlet {
    private final UserService service = new UserServiceImpl(new UsersRepoImpl());
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>Create User</title></head><body>");

        User toUpdate;
        try {
            toUpdate = service.findById(Integer.parseInt(req.getParameter("id")));
            if (toUpdate != null) {
                toUpdate.setUsername(req.getParameter("username") != null ? req.getParameter("username") : toUpdate.getUsername());
                toUpdate.setPassword(req.getParameter("password") != null ? req.getParameter("password") : toUpdate.getPassword());
                toUpdate.setFirstName(req.getParameter("firstname") != null ? req.getParameter("firstname") : toUpdate.getFirstName());
                toUpdate.setLastName(req.getParameter("lastname") != null ? req.getParameter("lastname") : toUpdate.getLastName());

                var updated = service.update(toUpdate);
                if (updated != null) out.println("user updated");
                else out.println("update didn't happen");
            } else {
                out.println("<p>");
                out.println("User ID not found");
                out.println("</p>");
            }
        } catch (NumberFormatException e) {
            out.println("<p>");
            out.println("id parameter must be an integer");
            out.println("</p>");
        } catch (Exception e) {
            out.println("<p>");
            out.println("Something went wrong with the database");
            out.println("</p>");
        } finally {
            out.println("</body></html>");
        }
    }
}

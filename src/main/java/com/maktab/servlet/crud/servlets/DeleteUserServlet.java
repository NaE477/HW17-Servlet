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

@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet {
    private final UserService service = new UserServiceImpl(new UsersRepoImpl());

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>Create User</title></head><body>");

        User toDelete;
        try {
            if (req.getParameter("idOrUsername").equals("username")) {
                toDelete = service.findByUsername(req.getParameter("username"));
            } else if (req.getParameter("idOrUsername").equals("id")) {
                toDelete = service.findById(Integer.parseInt(req.getParameter("id")));
            } else {
                throw new ReadingMethodException();
            }

            if (toDelete != null) {
                service.delete(toDelete);
                out.println("user deleted");
            } else {
                out.println("user not found!");
            }
        } catch (NumberFormatException e) {
            out.println("id parameter must be Integer");
        } catch (ReadingMethodException e) {
            out.println("idOrUsername method must be specified correctly by (id/username)");
        } finally {
            out.println("</body></html>");
        }
    }
}

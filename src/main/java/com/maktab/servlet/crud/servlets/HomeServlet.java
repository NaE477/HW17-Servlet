package com.maktab.servlet.crud.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>Welcome</title></head><body>");
        out.println("<p>");
        out.println("Now that you're an <span style=\"font-weight: bold;\">HTML5 ninja</span>, it's time for you to show us what you can do. Have a look at the html files we created throughout the course as reference. Now go and try making your own website with just HTML5! Once you are done, share your website with the slack community and fellow students of this course! I will also be checking the html channel with any new submissions. You can send your files as a message or wait until later on in the course where I show you how to put it online for free.");
        out.println("</p>");
        out.println("<p style=\"font-weight: bold;\">");
        out.println("I will be picking my favourite ones and showcasing them in a future video in the course.");
        out.println("</p>");
        out.println("<p>Good luck!</p>");
    }
}

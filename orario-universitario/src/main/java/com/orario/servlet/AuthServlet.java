package com.orario.servlet;

import com.orario.model.Student;
import com.orario.service.StudentService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        try {
            if ("register".equals(action)) {
                String user = req.getParameter("username").trim();
                String pass = req.getParameter("password");
                int year = Integer.parseInt(req.getParameter("year"));

                boolean ok = StudentService.register(user, pass, year);
                if (ok) {
                    req.getSession().setAttribute("msg", "Registrazione completata! Accedi ora.");
                    resp.sendRedirect("index.jsp");
                } else {
                    req.setAttribute("error", "Username già in uso.");
                    req.getRequestDispatcher("register.jsp").forward(req, resp);
                }

            } else if ("login".equals(action)) {
                String user = req.getParameter("username").trim();
                String pass = req.getParameter("password");
                Student s = StudentService.login(user, pass);
                if (s != null) {
                    req.getSession().setAttribute("student", s);
                    resp.sendRedirect("schedule?sem=1");
                } else {
                    req.setAttribute("error", "Credenziali errate.");
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                }

            } else if ("logout".equals(action)) {
                req.getSession().invalidate();
                resp.sendRedirect("index.jsp");
            }

        } catch (Exception e) {
            req.setAttribute("error", "Errore server: " + e.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
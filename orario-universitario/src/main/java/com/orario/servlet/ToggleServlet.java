package com.orario.servlet;

import com.orario.model.Student;
import com.orario.service.StudentService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/toggle")
public class ToggleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Student student = (Student) req.getSession().getAttribute("student");
        if (student == null) { resp.sendRedirect("index.jsp"); return; }

        String id  = req.getParameter("lessonId");
        String sem = req.getParameter("sem");

        if (id != null) {
            student.toggleLesson(id);
            try {
                StudentService.saveSelectedLessons(student);
            } catch (Exception e) {
                System.err.println("Errore salvataggio Firestore: " + e.getMessage());
            }
        }
        resp.sendRedirect("schedule?sem=" + (sem != null ? sem : "1"));
    }
}
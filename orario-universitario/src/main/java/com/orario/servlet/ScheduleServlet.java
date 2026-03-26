package com.orario.servlet;

import com.orario.model.*;
import com.orario.service.LessonRepository;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Student student = (Student) req.getSession().getAttribute("student");
        if (student == null) { resp.sendRedirect("index.jsp"); return; }

        // Determina semestre corrente dal mese
        int month = LocalDate.now().getMonthValue();
        String currentSem = (month >= 3 && month <= 6) ? "2" : "1";

        // Usa il semestre richiesto solo se coincide con quello corrente
        String sem = req.getParameter("sem");
        if (sem == null || !sem.equals(currentSem)) sem = currentSem;

        List<Lesson> lessons = LessonRepository.getFiltered(
            student.getEnrolledYear(), sem
        );
        req.setAttribute("lessons", lessons);
        req.setAttribute("sem", sem);
        req.setAttribute("currentSem", currentSem);
        req.getRequestDispatcher("schedule.jsp").forward(req, resp);
    }
}
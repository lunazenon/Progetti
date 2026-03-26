package com.orario.servlet;

import com.orario.model.*;
import com.orario.service.LessonRepository;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/my-lessons")
public class MyLessonsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Student student = (Student) req.getSession().getAttribute("student");
        if (student == null) { resp.sendRedirect("index.jsp"); return; }

        List<Lesson> selected = student.getSelectedIds().stream()
            .map(LessonRepository::findById)
            .filter(Objects::nonNull)
            .sorted(Comparator.comparing(Lesson::getDay)
                              .thenComparing(Lesson::getTimeStart))
            .collect(Collectors.toList());

        req.setAttribute("selected", selected);
        req.getRequestDispatcher("my-lessons.jsp").forward(req, resp);
    }
}
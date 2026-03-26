<%@ page contentType="text/html;charset=UTF-8"
         import="com.orario.model.*,java.util.*,java.util.stream.*" %>
<%
  Student student = (Student) session.getAttribute("student");
  if (student == null) { response.sendRedirect("index.jsp"); return; }
  List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");
  String sem = (String) request.getAttribute("sem");
  String currentSem = (String) request.getAttribute("currentSem");
  String[] days = {"Lunedì","Martedì","Mercoledì","Giovedì","Venerdì"};
%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <title>Orario – <%= sem %>° Semestre</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<nav>
  <span class="brand">ITI/ICD Orario</span>
  <div class="nav-links">
    <a href="my-lessons">Le mie lezioni</a>
    <form method="post" action="auth" style="display:inline">
      <input type="hidden" name="action" value="logout">
      <button type="submit">Logout (<%= student.getUsername() %>)</button>
    </form>
  </div>
</nav>
<div class="container">
  <p class="page-title"><%= sem %>° Semestre</p>
  <p class="page-sub">
    Anno: <strong><%= student.getEnrolledYear() == 0 ? "Fuori corso" : student.getEnrolledYear() + "°" %></strong>
    &nbsp;·&nbsp; Clicca su una lezione per selezionarla/deselezionarla
  </p>

  <div class="tabs">
    <a href="schedule?sem=1"
       class="<%= "1".equals(sem) ? "active" : "" %>"
       style="<%= !"1".equals(currentSem) ? "opacity:.4;pointer-events:none" : "" %>">
      1° Semestre <%= !"1".equals(currentSem) ? "(non disponibile)" : "" %>
    </a>
    <a href="schedule?sem=2"
       class="<%= "2".equals(sem) ? "active" : "" %>"
       style="<%= !"2".equals(currentSem) ? "opacity:.4;pointer-events:none" : "" %>">
      2° Semestre <%= !"2".equals(currentSem) ? "(non disponibile)" : "" %>
    </a>
  </div>

  <div class="schedule-grid">
    <% for (String day : days) {
         List<Lesson> dl = lessons.stream()
           .filter(l -> l.getDay().equals(day))
           .sorted(Comparator.comparing(Lesson::getTimeStart))
           .collect(Collectors.toList()); %>
    <div>
      <div class="day-header"><%= day %></div>
      <div class="day-col" style="margin-top:.5rem">
        <% if (dl.isEmpty()) { %>
          <div style="color:#aaa;font-size:.82rem;padding:.5rem;text-align:center">—</div>
        <% }
           for (Lesson l : dl) {
             boolean sel = student.isSelected(l.getId()); %>
        <div class="lesson-card <%= sel ? "selected" : "" %>">
          <h4><%= l.getSubject() %></h4>
          <span class="time"><%= l.getTimeStart() %> – <%= l.getTimeEnd() %></span><br>
          <span class="badge"><%= l.getCourse() %></span>
          <span class="badge"><%= l.getYear() %>° Anno</span>
          <form method="post" action="toggle">
            <input type="hidden" name="lessonId" value="<%= l.getId() %>">
            <input type="hidden" name="sem" value="<%= sem %>">
            <button type="submit" class="btn btn-sm <%= sel ? "btn-selected" : "btn-outline" %>">
              <%= sel ? "Selezionata" : "Seleziona" %>
            </button>
          </form>
        </div>
        <% } %>
      </div>
    </div>
    <% } %>
  </div>
</div>
</body>
</html>
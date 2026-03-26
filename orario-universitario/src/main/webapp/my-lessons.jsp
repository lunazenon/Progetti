<%@ page contentType="text/html;charset=UTF-8" import="com.orario.model.*,java.util.*" %>
<%
  Student student = (Student) session.getAttribute("student");
  if (student == null) { response.sendRedirect("index.jsp"); return; }
  List<Lesson> selected = (List<Lesson>) request.getAttribute("selected");
  String[] dayOrder = {"Lunedì","Martedì","Mercoledì","Giovedì","Venerdì"};
%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <title>Le mie lezioni</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<nav>
  <span class="brand">ITI/ICD Orario</span>
  <div class="nav-links">
    <a href="schedule?sem=1">Orario</a>
    <form method="post" action="auth" style="display:inline">
      <input type="hidden" name="action" value="logout">
      <button type="submit">Logout (<%= student.getUsername() %>)</button>
    </form>
  </div>
</nav>
<div class="container">
  <p class="page-title">Le mie lezioni</p>
  <p class="page-sub">Riepilogo delle lezioni selezionate, ordinate per giorno.</p>

  <% if (selected == null || selected.isEmpty()) { %>
    <div class="alert alert-error">Nessuna lezione selezionata.
      <a href="schedule?sem=1" style="color:inherit;font-weight:700">Vai all'orario</a>
    </div>
  <% } else {
       for (String day : dayOrder) {
         final String d = day;
         List<Lesson> dl = new ArrayList<>();
         for (Lesson l : selected) if (l.getDay().equals(d)) dl.add(l);
         if (dl.isEmpty()) continue; %>
    <h3 style="color:var(--primary);margin:1.2rem 0 .6rem"><%= day %></h3>
    <div class="lesson-list">
      <% for (Lesson l : dl) { %>
      <div class="lesson-row">
        <div class="info">
          <h4><%= l.getSubject() %></h4>
          <span><%= l.getTimeStart() %> – <%= l.getTimeEnd() %>
            &nbsp;|&nbsp; Sem. <%= l.getSemester() %>
            &nbsp;|&nbsp; Anno <%= l.getYear() %>
            &nbsp;|&nbsp; <%= l.getCourse() %>
          </span>
        </div>
        <form method="post" action="toggle">
          <input type="hidden" name="lessonId" value="<%= l.getId() %>">
          <input type="hidden" name="sem" value="<%= l.getSemester() %>">
          <button type="submit" class="btn btn-sm" style="background:#c62828">Rimuovi</button>
        </form>
      </div>
      <% } %>
    </div>
  <% } } %>
  <div style="margin-top:2rem">
    <a href="schedule?sem=1" class="btn btn-outline">Torna all'orario</a>
  </div>
</div>
</body>
</html>
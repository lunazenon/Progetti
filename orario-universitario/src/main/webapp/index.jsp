<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <title>ITI/ICD – Accedi</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<nav><span class="brand">ITI/ICD Orario</span></nav>
<div class="card">
  <h2>Accedi</h2>
  <% String msg = (String) session.getAttribute("msg");
     if (msg != null) { session.removeAttribute("msg"); %>
    <div class="alert alert-success"><%= msg %></div>
  <% } %>
  <% String err = (String) request.getAttribute("error");
     if (err != null) { %>
    <div class="alert alert-error"><%= err %></div>
  <% } %>
  <form method="post" action="auth">
    <input type="hidden" name="action" value="login">
    <div class="form-group">
      <label>Username</label>
      <input type="text" name="username" required autofocus>
    </div>
    <div class="form-group">
      <label>Password</label>
      <input type="password" name="password" required>
    </div>
    <button class="btn" type="submit">Accedi</button>
  </form>
  <p style="margin-top:1rem;font-size:.9rem;">Non hai un account?
    <a href="register.jsp" style="color:var(--primary);font-weight:600;">Registrati</a>
  </p>
</div>
</body>
</html>
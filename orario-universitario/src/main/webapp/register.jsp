<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <title>ITI/ICD – Registrati</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<nav><span class="brand">ITI/ICD Orario</span></nav>
<div class="card">
  <h2>Registrati</h2>
  <% String err = (String) request.getAttribute("error");
     if (err != null) { %>
    <div class="alert alert-error"><%= err %></div>
  <% } %>
  <form method="post" action="auth">
    <input type="hidden" name="action" value="register">
    <div class="form-group">
      <label>Username</label>
      <input type="text" name="username" required autofocus>
    </div>
    <div class="form-group">
      <label>Password</label>
      <input type="password" name="password" required>
    </div>
    <div class="form-group">
      <label>Anno di corso</label>
      <select name="year">
        <option value="1">1° Anno</option>
        <option value="2">2° Anno</option>
        <option value="3">3° Anno</option>
        <option value="0">Fuori corso</option>
      </select>
    </div>
    <button class="btn" type="submit">Registrati</button>
  </form>
  <p style="margin-top:1rem;font-size:.9rem;">Hai già un account?
    <a href="index.jsp" style="color:var(--primary);font-weight:600;">Accedi</a>
  </p>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu</title>
</head>
<body>
<div>
    <h3>Menu</h3>
    <a href="/ricercaProdotti">Ricerca Prodotti</a><br>
    <c:if test="${utente != null && utente.ruolo.nome == 'administrator'}">
        <a href="/creaProdotto">Crea Prodotto</a><br>
    </c:if>
    <a href="/login">Login</a><br>
    <a href="/registrazione">Registrazione</a><br>
</div>
</body>
</html>
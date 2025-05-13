<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Esito Operazione</title>
</head>
<body>
    <h1>Esito Operazione</h1>
    <p style="color: green;">${messaggio}</p>
    <p style="color: red;">${errore}</p>
	<a href="${pageContext.request.contextPath}/menu">Torna al Menu</a>
</html>
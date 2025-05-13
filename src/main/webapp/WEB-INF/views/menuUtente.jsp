<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Menu Utente</title>
</head>
<body>
    <h1>Benvenuto ${utente.username}</h1>
    <ul>
        <li><a href="ricercaProdotti">Ricerca Prodotti</a></li>
        <li><a href="visualizzaProdotti">Visualizza prodotti</a></li>
        <li><a href="logout">Logout</a></li>
    </ul>
</body>
</html>
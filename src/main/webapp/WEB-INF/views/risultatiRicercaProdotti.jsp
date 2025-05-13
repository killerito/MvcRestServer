<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Risultati Ricerca Prodotti</title>
</head>
<body>
    <h1>Risultati Ricerca Prodotti</h1>
    <table>
        <tr>
            <th>Nome</th>
            <th>Prezzo</th>
        </tr>
        <c:forEach var="prodotto" items="${prodotti}">
            <tr>
                <td>${prodotto.nome}</td>
                <td>${prodotto.prezzo}</td>
            </tr>
        </c:forEach>
    </table>

    <p style="color: red;">${errore}</p>
</body>
</html>
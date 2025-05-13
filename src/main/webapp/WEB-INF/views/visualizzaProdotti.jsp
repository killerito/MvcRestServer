<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>visualizzaProdotti</title>
</head>
<body>
    <h1>Visualizza Prodotti</h1>
    <table border="1" width ="200px">
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
</body>
</html>
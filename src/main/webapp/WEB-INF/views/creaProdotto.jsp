<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crea Prodotto</title>
</head>
<body>
    <h1>Crea un nuovo prodotto</h1>
    <form action="${pageContext.request.contextPath}/creaProdotto" method="post">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required><br><br>

        <label for="prezzo">Prezzo:</label>
        <input type="number" step="0.01" id="prezzo" name="prezzo" required><br><br>

        <button type="submit">Crea Prodotto</button>
    </form>

    <p style="color: red;">${errore}</p>
    <p style="color: green;">${messaggio}</p>
</body>
</html>
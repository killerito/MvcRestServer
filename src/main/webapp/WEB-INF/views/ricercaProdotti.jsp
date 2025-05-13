<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ricerca Prodotti</title>
</head>
<body>
    <h1>Ricerca Prodotti</h1>
    <form action="/ricercaProdotti" method="post">
        <label for="nome">Nome prodotto:</label>
        <input type="text" id="nome" name="nome"><br><br>

        <label for="prezzo">Prezzo:</label>
        <input type="number" step="0.01" id="prezzo" name="prezzo"><br><br>

        <button type="submit">Ricerca</button>
    </form>

    <p style="color: red;">${errore}</p>
</body>
</html>
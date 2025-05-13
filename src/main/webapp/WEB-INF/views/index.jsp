<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catalogo Prodotti</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
        }
        table {
            margin: 0 auto;
            border-collapse: collapse;
            width: 50%;
        }
        th, td {
            border: 1px solid #333;
            padding: 12px;
            background-color: #f2f2f2;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        a {
            text-decoration: none;
            color: #007BFF;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Benvenuto nel sito web del supermercato!</h1>
    <table>
        <tr>
            <th>Pagina</th>
            <th>Link</th>
        </tr>
        <tr>
            <td>Menu</td>
            <td><a href="menu">Vai al Menu</a></td>
        </tr>
        <tr>
            <td>Registrazione</td>
            <td><a href="registrazione">Registrati</a></td>
        </tr>
        <tr>
            <td>Login</td>
            <td><a href="login">Accedi</a></td>
        </tr>
    </table>
</body>
</html>
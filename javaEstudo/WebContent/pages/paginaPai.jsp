<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Página Pai</title>

<!-- Adicionando JQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

</head>
<body>
<h1>Página Pai Load JQuery</h1>

<input type="button" value="Cerregar Página" onclick="carregar();">

<!-- Local de carregamento da página filha -->
<div id="mostrarPaginaFilha"></div> 
</body>

<script type="text/javascript">

function carregar() {
	$("#mostrarPaginaFilha").load('paginaFilha.jsp');//load da página em JQuery
}

</script>
</html>
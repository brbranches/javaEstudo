<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet"	href="http://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css">
<script	src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script	src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>


<meta charset="ISO-8859-1">
<title>DataTable JQuery</title>
</head>
<body>

	<table id="minhatabela" class="display" style="width: 100%">
		<thead>
			<tr>
				<th>Id</th>
				<th>Login</th>
				<th>Senha</th>
			</tr>
		</thead>
		
	</table>

</body>

<script type="text/javascript">
$(document).ready(function() {
    $('#minhatabela').DataTable( { //faz o processamento na hora que abre a tela
        "processing": true,
        "serverSide": true,
        "ajax": "carregarDadosDataTable"//Retorno dos dados do servidor. URL de retorno (Resposta do servidor)
//      "ajax": "scripts/server_processing.php"//Retorno dos dados do servidor. URL de retorno (Resposta do servidor). Aqui está em PHP.
    } );
} );
</script>
</html>
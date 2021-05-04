<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload files</title>

<!-- Adicionando JQuery -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

</head>
<body>
	<h2>Upload files</h2>

	<input type="file" id="file" name="file" onchange="uploadFile();" />

	<img alt="imagem" id="target" src="" width="200px" height="200px">


</body>

<!-- Incio da função javascript upload file -->
<script type="text/javascript">
	function uploadFile() {

		var target = document.querySelector("img");
		var file = document.querySelector("input[type=file]").files[0];

		var reader = new FileReader();

		reader.onloadend = function() {
			target.src = reader.result;

			//-----------Upload Ajax---------

			$.ajax({
				method : "POST",
				url : "fileUpload", //URL é pra qual servlet ele vai ser disparado
				data : {
					fileUpload : reader.result
				}
			//fileUpload é o parâmetro que vamos caputrar na servlet. target.src é o valor que está vindo da tela, 
			//Dentro do id #txtvalor.
			}).done(function(response) { //esse método done capta o retorno quando o processamento da certo
				//todos os dados da resposta retornam dentro dessa variável response. 

				alert("Sucesso: " + response); //Aqui mostar a resposta na tela
				//Se der ok, fazer algo.
			}).fail(function(xrh, status, errorThrown) { //esse método fail capta o retorno quando o processamento da errado 
				//todos os dados da resposta retornam dentro dessa variável response. 

				alert("Error: " + xrh.responseText); //Aqui mostar a resposta na tela. o xrh.responseText, pega o valor da menagem que está
				//vindo da servlet no getwriter.write.
				//Se der errado, fazer algo.
			});

			//--------Fim Upload Ajax---------

		};

		if (file) {

			reader.readAsDataURL(file);
		} else {
			target.src = "";
		}
	}
</script>

</html>
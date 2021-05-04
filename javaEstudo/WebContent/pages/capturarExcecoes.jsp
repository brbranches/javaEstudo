<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<!-- Adicionando JQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	
<title>Capturando Exce��es com JQuery</title>
</head>
<body>

<h3>Capturando Exce��es com JQuery</h3>

<input type="text" value="valor informado" id="txtvalor">
<input type="button" onclick="testarExcecao();" value="Testar Excec�o">

</body>

<script type="text/javascript">

	function testarExcecao(){
		
		valorInformado = $('#txtvalor').val();
		
		
		$.ajax({
			method: "POST",
			url:"capturarExcecao", //URL � pra qual servlet ele vai ser disparado
			url:"capturarExcecao", //URL � pra qual servlet ele vai ser disparado
			data:{valorParam :  valorInformado } //ValorParam � o par�metro que vamos caputrar na servlet. ValorInformado � o valor que est� vindo da tela, 
												//Dentro do id #txtvalor.
		})
			.done(function(response){ //esse m�todo done capta o retorno quando o processamento da certo
										//todos os dados da resposta retornam dentro dessa vari�vel response. 
				
				alert("Sucesso: " + response); //Aqui mostar a resposta na tela
				//Se der ok, fazer algo.
			})
			.fail(function(xrh, status, errorThrown){ //esse m�todo fail capta o retorno quando o processamento da errado 
										//todos os dados da resposta retornam dentro dessa vari�vel response. 
				
				alert("Error: " + xrh.responseText); //Aqui mostar a resposta na tela. o xrh.responseText, pega o valor da menagem que est�
													//vindo da servlet no getwriter.write.
				//Se der errado, fazer algo.
			});
		
	}
</script>
</html>
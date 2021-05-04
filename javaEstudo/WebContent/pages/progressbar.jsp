<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Barra de Progresso</title>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<style type="text/css">
#myprogress {
	width: 100%;
	background-color: #ddd;
}

#mybar {
	width: 0.5%;
	height: 30px;
	background-color: #4CAF50;
}

.ui-progressbar {
	position: relative;
}

.progress-label {
	position: relative;
	left: 50%;
	top: 4px;
	font-weight: bold;
	text-shadow: 1px 1px 0 #fff;
}
</style>

</head>
<body>
	<h1>Exemplo de barra de progresso com com javascript</h1>

	<div id="myprogress">
		<div id="mybar"></div>
	</div>
	<br />

	<button onclick="exibirBarra()">Inicar a barra de progresso</button>


	<h1>----------------------------------------------------------------------</h1>

	<h1>Exemplo de barra de progresso com com JQuery</h1>
	<div id="progressbar">
		<div class="progress-label">Carregando ...</div>
	</div>


	<script type="text/javascript">

	<!-- 	------------ INÍCIO DO SCRIPT DA BARRA DE PROGRESSO POR JQUERY --------------- -->
		$(function() {
			var progressbar = $("#progressbar"), progresslabel = $(".progress-label");

			progressbar.progressbar({ //Cria a barra na div
				value : false,
				change : function() {
					progresslabel.text(progressbar.progressbar('value') + "%");
				},
					complete : function () {
						progresslabel.text('Completo!');
					}
			});
			
			function progress() {
				var val = progressbar.progressbar("value") || 0;
				
				progressbar.progressbar("value", val + 2);
				
				if (val < 99) {
					setTimeout(progress,80);
				}
			}
			
			setTimeout(progress, 2000);//Chamado ao abrir a tela
			
		});
	<!-- 	------------ FIM DO SCRIPT DA BARRA DE PROGRESSO POR JQUERY --------------- -->

		
		
	<!-- 	------------ FIM DO SCRIPT DA BARRA DE PROGRESSO POR JAVASCRIPT --------------- -->
	
		function exibirBarra() {

			var elem = document.getElementById("mybar");
			var width = 1;
			var id = setInterval(frame, 10);

			function frame() {
				if (width >= 100) {
					clearInterval(id);
				} else {
					width++;
					elem.style.width = width + '%';
				}
			}
		}

	<!-- 	------------ FIM DO SCRIPT DA BARRA DE PROGRESSO POR JAVASCRIPT --------------- -->
	</script>
</body>

</html>
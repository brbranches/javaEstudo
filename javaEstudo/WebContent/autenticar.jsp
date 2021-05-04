<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Autenticar Usuário</title>
</head>
<body>

	<h2>Autenticar Usuário</h2>

	<form action="ServletAutenticacao" method="post">
	<input readonly="readonly" id="url" name="url" type="hidden"  value="<%= request.getParameter("url")%>">
		<table>
			<tr>
				<td>Login</td>
				<td> <input type="text" id="login" name="login"> </td>
			</tr>
			<tr>
				<td>Senha</td>
				<td> <input type="password" id="senha" name="senha"> </td>
			</tr>
			
			<tr>
				<td></td>
				<td><input type="submit" id="logar" name="logar" value="logar"></td>
			</tr>
		</table>
	</form>
</body>
</html>
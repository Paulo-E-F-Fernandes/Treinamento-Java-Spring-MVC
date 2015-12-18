<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adicionar Contas</title>
</head>
<body>

	<h3>Adicionar Contas</h3>
	<form action="adicionarConta" method="post">
		Descrição:
		<br>
		<textarea name="descricao" rows="2" cols="100"></textarea>
		<br>
		
		Valor:
		<br>
		<input type="text" name="valor" />
		<br>
		
		Tipo:
		<br>
		<select name="tipo">
			<option value="ENTRADA">Entrada</option>
			<option value="SAIDA">Saida</option>
		</select>
		<br><br>
		
		<input type="submit" value="Adicionar" />
	</form>	

</body>
</html>
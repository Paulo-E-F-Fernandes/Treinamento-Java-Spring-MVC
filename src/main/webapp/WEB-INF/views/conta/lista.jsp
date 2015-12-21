<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="resources/js/jquery.js"></script>
<script type="text/javascript">
	function deuCertoCallback(dadosDaResposta) {
		alert("Conta paga com sucesso!");
	}

	function pagaAgora(id) {}
		$.get("pagaContaAjax?id=" + id, deuCertoCallback);
	}
</script>

</head>
<body>

	<table>
		<tr>
			<th>Código</th>
			<th>Descrição</th>
			<th>Valor</th>
			<th>Tipo</th>
			<th>Paga?</th>
			<th>Data de Pagamento</th>
			<th>Ações</th>
		</tr>

		<!-- O c:forEach é chamado de JSTL que é uma biblioteca Java que ajuda a escrever um código mais enxuto na jsp -->
<%--
		<c:forEach items="${todasContasXX}" var="conta">
			<tr>
				<!-- ${...} chamasse EL e é uma maneira elegante de acessar um atributo de um objeto na jsp -->
				<td>${conta.id}</td>
				<td>${conta.descrição}</td>
				<td>${conta.valor}</td>
				<td>${conta.tipo}</td>
				<td>
					<c:if test="${conta.paga eq false}">Não Paga!</c:if>
					<c:if test="${conta.paga eq true}">Paga!</c:if>
				</td>
				<td><fmt:formatDate value="${conta.dataPagamento.time}" pattern="dd/MM/yyyy" /></td>
				<td>
					<a href="removeConta?id=${conta.id}">Deletar</a> |
					<c:if test="${conta.paga eq false}"> 
						<!-- Normal -->
						<!-- <a href="pagaConta?id=${conta.id}">Pagar</a> -->
						<!-- Com Ajax -->
						<a href="#" onclick="pagaAgora(${conta.id})">Pagar</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
--%>
	</table>

</body>
</html>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="domainModel.Produto" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Editar Produto</title>
</head>
<body>
	<%
	Produto produto = (Produto) request.getAttribute("produto");
	%>
	<form action="/Loja/Produtos" method="post">
		<table>
			<tr>
				<td>
					Codigo:
				</td>
				<td>
					<input type="text" name="cod" value="<% if(produto != null){ out.print(produto.getId()); } %>" />
				</td>
			</tr>
			
			<tr>
				<td>
					Nome:
				</td>
				<td>
					<input type="text" name="nome" value="<% if(produto != null){ out.print(produto.getNome()); } %>" />
				</td>
			</tr>
			
			<tr>
				<td>
					Preço Unitário:
				</td>
				<td>
					<input type="text" name="precounitario" value="<% if(produto != null){ out.print(produto.getPrecounitario()); } %>" />
				</td>
			</tr>
		</table>
		<input type="submit" value="Salvar" />
	</form>

</body>
</html>
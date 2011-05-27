<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="domainModel.Produto" %>
<%@page import="java.util.List, java.util.Iterator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Listagem de Produtos</title>
</head>
<body>
<%
	List produtos = (List) request.getAttribute("produtos");
	if(produtos != null){
		%>
		<table>
		<tr><td>ID</td><td>Nome</td><td>Preço Unitário</td></tr>
		<%
		Iterator it = produtos.iterator();
		while(it.hasNext()){
			Produto p = (Produto)it.next();
			%>
				<tr>
					<td><%=p.getId() %></td>
					<td><%=p.getNome() %></td>
					<td><%=p.getPrecounitario() %></td>
					<td><a href="/Loja/Produtos?edit=<%=p.getId() %>">Editar</a></td>
					<td><a href="/Loja/Produtos?del=<%=p.getId() %>">Apagar</a></td>
					
				</tr>
			<%
		}
		%>
		</table>
		<%
	}
%>
<a href="/Loja/Produtos?edit=new">Criar Novo Produto</a>

</body>
</html>
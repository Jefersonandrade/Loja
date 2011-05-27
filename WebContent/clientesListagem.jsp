<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="domainModel.Cliente" %>
<%@page import="java.util.List, java.util.Iterator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Listagem de Clientes</title>
</head>
<body>
<%
	List clientes = (List) request.getAttribute("clientes");
	if(clientes != null){
		%>
		<table>
		<tr><td>ID</td><td>Nome</td></tr>
		<%
		Iterator it = clientes.iterator();
		while(it.hasNext()){
			Cliente c= (Cliente)it.next();
			%>
				<tr>
					<td><%=c.getId() %></td>
					<td><%=c.getNome() %></td>
					<td><a href="/Loja/Clientes?edit=<%=c.getId() %>">Editar</a></td>
					<td><a href="/Loja/Clientes?del=<%=c.getId() %>">Apagar</a></td>
					
				</tr>
			<%
		}
		%>
		</table>
		<%
	}
%>
<a href="/Loja/Clientes?edit=new">Criar Novo Cliente</a>

</body>
</html>
package presentation;

import domainModel.Cliente;
import dataAccess.ClienteRepository;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class clienteController
 */
@WebServlet("/Clientes")
public class clienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	//Declaração do Repositório
	ClienteRepository repositorio;
	
	//Construtor do Servlet
    public clienteController() {
        super();
        
        //Inicialização do Repositório
        repositorio = new ClienteRepository();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		//Verificar se o Parâmetro edit foi passado
		String edit = request.getParameter("edit");
		
		//Codigo que executa quando o parametro edit é passado
		if(edit!= null){
			if(!edit.equalsIgnoreCase("new")){
				//Converter 
				try{
					//carrega o Cliente do BD
					Cliente cliente = repositorio.Open(Integer.parseInt(edit));
					
					//Passa o Cliente para a página JSP
					request.setAttribute("cliente", cliente);
					
				}catch (Exception e){
					e.printStackTrace();
				}
				
			}
		
			//Chamar Página JSP
			RequestDispatcher editar = request.getRequestDispatcher("clienteEditar.jsp");
			editar.forward(request, response);
			return;
			
		}
		//Verifica se o parâmetro del foi passado
		String del = request.getParameter("del");
		if(del != null){
			try {
				//Carrega o cliente do BD 
				Cliente cliente = repositorio.Open(Integer.parseInt(del));
				
				//Apaga cliente carregado da base 
				repositorio.Delete(cliente);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Gera uma listagem de clientes
		List clientes = repositorio.getTop10ByName();
		
		//Passa a listagem para a pagina JSP
		request.setAttribute("clientes", clientes);
		
		//Chamar a página JSP
		RequestDispatcher listagem = request.getRequestDispatcher("clientesListagem.jsp");
		listagem.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Recebe os parâmetros do formulário
			String cod = request.getParameter("cod");
			String nome = request.getParameter("nome");
			
			Cliente cliente;
			
			// Carrega o objeto do banco de dados
			if(cod != null && cod.length() != 0)
				cliente = repositorio.Open(Integer.parseInt(cod));
			else
				cliente = new Cliente();
			
			cliente.setNome(nome);
			
			repositorio.Save(cliente);
			
			// Gera uma listagem de clientes
			List clientes = repositorio.getTop10ByName();
			
			// Passa a listagem para a página JSP
			request.setAttribute("clientes", clientes);
			
			// Chamar a página JSP
			RequestDispatcher listagem = request.getRequestDispatcher("clientesListagem.jsp");
			listagem.forward(request, response);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}

package presentation;

import domainModel.Produto;
import dataAccess.ProdutoRepository;

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
@WebServlet("/Produtos")
public class produtoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	//Declara��o do Reposit�rio
	ProdutoRepository repositorio;
	
	//Construtor do Servlet
    public produtoController() {
        super();
        
        //Inicializa��o do Reposit�rio
        repositorio = new ProdutoRepository();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		//Verificar se o Par�metro edit foi passado
		String edit = request.getParameter("edit");
		
		//Codigo que executa quando o parametro edit � passado
		if(edit!= null){
			if(!edit.equalsIgnoreCase("new")){
				//Converter 
				try{
					//carrega o Produto do BD
					Produto produto = repositorio.Open(Integer.parseInt(edit));
					
					//Passa o Produto para a p�gina JSP
					request.setAttribute("produto", produto);
					
				}catch (Exception e){
					e.printStackTrace();
				}
				
			}
		
			//Chamar P�gina JSP
			RequestDispatcher editar = request.getRequestDispatcher("produtoEditar.jsp");
			editar.forward(request, response);
			return;
			
		}
		//Verifica se o par�metro del foi passado
		String del = request.getParameter("del");
		if(del != null){
			try {
				//Carrega o produto do BD 
				Produto produto = repositorio.Open(Integer.parseInt(del));
				
				//Apaga produto carregado da base 
				repositorio.Delete(produto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Gera uma listagem de produtos
		List produtos = repositorio.getTop10ByName();
		
		//Passa a listagem para a pagina JSP
		request.setAttribute("produtos", produtos);
		
		//Chamar a p�gina JSP
		RequestDispatcher listagem = request.getRequestDispatcher("produtosListagem.jsp");
		listagem.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Recebe os par�metros do formul�rio
			String cod = request.getParameter("cod");
			String nome = request.getParameter("nome");
			String precounitario = request.getParameter("precounitario");
			
			Produto produto;
			
			// Carrega o objeto do banco de dados
			if(cod != null && cod.length() != 0)
				produto = repositorio.Open(Integer.parseInt(cod));
			else
				produto = new Produto();
			
			produto.setNome(nome);
			produto.setPrecounitario(Float.parseFloat(precounitario));
			
			repositorio.Save(produto);
			
			// Gera uma listagem de produtos
			List produtos = repositorio.getTop10ByName();
			
			// Passa a listagem para a p�gina JSP
			request.setAttribute("produtos", produtos);
			
			// Chamar a p�gina JSP
			RequestDispatcher listagem = request.getRequestDispatcher("produtosListagem.jsp");
			listagem.forward(request, response);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}


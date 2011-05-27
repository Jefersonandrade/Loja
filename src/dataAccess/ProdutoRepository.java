package dataAccess;

import domainModel.Produto;
import java.util.List;
import javax.persistence.*;

public class ProdutoRepository {
	
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;

	public ProdutoRepository(){
		factory = Persistence.createEntityManagerFactory("Loja");
		manager = factory.createEntityManager();
	}
	
	public void Save(Produto obj) throws Exception{
		try{
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(obj);
			transaction.commit();
		}
		catch(Exception ex){
			transaction.rollback();
			throw ex;
		}
	}
	
	public void Delete(Produto obj) throws Exception {
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			manager.remove(obj);
			transaction.commit();
		}
		catch(Exception ex){
			transaction.rollback();
			throw ex;
		}
	}
	
	public Produto Open(int id) throws Exception {
		try {
			return manager.find(Produto.class, id);
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	public List getTop10ByName() {
		return manager.createQuery("select p from Produto p order by p.nome")
		.setMaxResults(10).getResultList();
	}

}

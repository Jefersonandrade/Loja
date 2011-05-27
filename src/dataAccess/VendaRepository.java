package dataAccess;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domainModel.Venda;
public class VendaRepository {
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;

	public VendaRepository(){
		factory = Persistence.createEntityManagerFactory("Loja");
		manager = factory.createEntityManager();
	}
	
	public void Save(Venda obj) throws Exception{
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
	
	public void Delete(Venda obj) throws Exception {
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
	
	public Venda Open(int id) throws Exception {
		try {
			return manager.find(Venda.class, id);
		}
		catch(Exception ex){
			throw ex;
		}
	}

}

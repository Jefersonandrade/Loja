package dataAccess;
import javax.persistence.*;

import domainModel.ItemVenda;

public class ItemVendaRepository {
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;

	public ItemVendaRepository(){
		factory = Persistence.createEntityManagerFactory("Loja");
		manager = factory.createEntityManager();
	}
	
	public void Save(ItemVenda obj) throws Exception{
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
	
	public void Delete(ItemVenda obj) throws Exception {
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
	
	public ItemVenda Open(int id) throws Exception {
		try {
			return manager.find(ItemVenda.class, id);
		}
		catch(Exception ex){
			throw ex;
		}
	}

}

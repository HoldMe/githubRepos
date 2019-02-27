package boot.com.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import boot.com.dao.BaseDao;

@Repository
public class BaseDaoImpl implements BaseDao{
	
	@PersistenceContext
	private EntityManager mainEntityManager;
	
	@PersistenceContext(name="secondDs")
	private EntityManager secondEntityManager;
	
	public void save(Object entity){
		mainEntityManager.persist(entity);
	}
	
	public void delete(Object entity){
		mainEntityManager.remove(entity);
	}
	
	public Object getById(Long id,Object obj){
		return mainEntityManager.find(obj.getClass(), id);
	}
	
	public void update(Object obj){
		mainEntityManager.merge(obj);
	}
	
	public void save(Object entity,String dataSource){
		if(dataSource.equals("main")){
			mainEntityManager.persist(entity);
		}else{
			secondEntityManager.persist(entity);
		}
	}
	
	

}

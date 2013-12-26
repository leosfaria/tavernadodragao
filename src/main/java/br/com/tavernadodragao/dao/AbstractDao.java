package br.com.tavernadodragao.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class AbstractDao<T> {

//	protected Session session;
	@SuppressWarnings("rawtypes")
	protected Class clazz;

	@Autowired
	private SessionFactory sessionFactory;

//	@PostConstruct
//	public void init() {
//		session = sessionFactory.openSession();
//	}

	@SuppressWarnings("rawtypes")
	protected AbstractDao(Class clazz) {
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public T find(Long pk) {
		T obj = (T) session().load(clazz, pk);
//		session.flush();
//		sessionClose();
		return obj;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public T save(T clazz) {
		session().saveOrUpdate(clazz);
		return clazz;
	}

	protected Session session() {
		Session session = sessionFactory.getCurrentSession();
//		session.setFlushMode(FlushMode.MANUAL);
		return session;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public T update(T clazz) {
		session().merge(clazz);
//		session.flush();
//		sessionClose();
		return clazz;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(T clazz) {
		session().delete(session().merge(clazz));
//		session.flush();
	}

	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED)
	public List list() {
		session().clear();
		Criteria crit = session().createCriteria(clazz);
		List lista = crit.list();
		return lista;
	}

//	public void sessionClose() {
//		System.out.println("Morri");
//		session.close();
//	}

}

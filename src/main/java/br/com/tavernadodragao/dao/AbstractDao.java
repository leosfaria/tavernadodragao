package br.com.tavernadodragao.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class AbstractDao<T> {

	@SuppressWarnings("rawtypes")
	protected Class clazz;

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("rawtypes")
	protected AbstractDao(Class clazz) {
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	public T find(Long pk) {
		T obj = (T) session().load(clazz, pk);
		return obj;
	}

	public T save(T clazz) {
		session().saveOrUpdate(clazz);
		session().flush();
		return clazz;
	}

	protected Session session() {
		Session session = sessionFactory.getCurrentSession();
		session.setFlushMode(FlushMode.MANUAL);
		return session;
	}

	public T update(T clazz) {
		session().merge(clazz);
		session().flush();
		return clazz;
	}

	public void delete(T clazz) {
		session().delete(session().merge(clazz));
	}

	@SuppressWarnings("rawtypes")
	@Transactional(propagation = Propagation.REQUIRED)
	public List list() {
		session().clear();
		Criteria crit = session().createCriteria(clazz);
		List lista = crit.list();
		return lista;
	}
}
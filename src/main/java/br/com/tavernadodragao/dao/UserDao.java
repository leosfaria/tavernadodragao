package br.com.tavernadodragao.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.tavernadodragao.model.User;

@Repository
public class UserDao extends AbstractDao<User> {

	public UserDao() {
		super(User.class);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteById(Long id) {
		super.delete(super.find(id));
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public User save(User user) {
		return super.save(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public User update(User user) {
		return super.update(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public User findUserById(Long id) {
		Criteria crit = session().createCriteria(User.class);

		crit.add(Restrictions.eq("id", id));
		return (User) crit.uniqueResult();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public User findUser(User user) {
		Criteria crit = session().createCriteria(User.class);

		crit.add(Restrictions.eq("email", user.getEmail()));
		crit.add(Restrictions.eq("password", user.getPassword()));

		User result = (User) crit.uniqueResult();
		return result;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<User> findUsersByUsername(String username, User excludeUser) {
		Criteria crit = session().createCriteria(User.class);

		crit.add(Restrictions.like("username", "%" + username + "%"));
		crit.add(Restrictions.not(Restrictions.in("id", new Long[] { excludeUser.getId() })));
		return (List<User>) crit.list();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean existsUser(User user) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("email", user.getEmail()));
		user = (User) crit.uniqueResult();
		return user != null;
	}
}

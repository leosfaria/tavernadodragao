package br.com.tavernadodragao.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.tavernadodragao.model.Activity;
import br.com.tavernadodragao.model.User;

@Repository
public class ActivityDao extends AbstractDao<Activity> {

	protected ActivityDao() {
		super(Activity.class);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteById(Long id) {
		super.delete(super.find(id));
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Activity save(Activity activity) {
		return super.save(activity);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public List<Activity> findActivitiesFromUser(User user) {
		Criteria crit = session().createCriteria(Activity.class);

		crit.add(Restrictions.eq("userId", user.getId()));
		crit.addOrder(Order.desc("date"));
		
		return (List<Activity>) crit.list();
	}

}

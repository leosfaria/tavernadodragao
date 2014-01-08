package br.com.tavernadodragao.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.tavernadodragao.model.Campaign;
import br.com.tavernadodragao.model.User;

@Repository
public class CampaignDao extends AbstractDao<Campaign> {

	public CampaignDao() {
		super(Campaign.class);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteById(Long id) {
		super.delete(super.find(id));
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Campaign save(Campaign campaign) {
		return super.save(campaign);
	}

//	public boolean existsUser(User user) {
//		return user != null;
//	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean existsCampaign(Campaign campaign, User master) {
		Criteria crit = session().createCriteria(Campaign.class);
		crit.add(Restrictions.eq("name", campaign.getName()));
		crit.add(Restrictions.eq("masterId", master.getId()));
		campaign = (Campaign) crit.uniqueResult();
		return campaign != null;
	}
}

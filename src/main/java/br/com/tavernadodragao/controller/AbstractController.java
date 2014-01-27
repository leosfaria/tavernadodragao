package br.com.tavernadodragao.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tavernadodragao.dao.ActivityDao;
import br.com.tavernadodragao.dao.UserDao;
import br.com.tavernadodragao.model.Activity;
import br.com.tavernadodragao.model.Campaign;
import br.com.tavernadodragao.model.User;

public abstract class AbstractController {

	@Autowired
	protected UserDao userDao;
	
	public String url = "http://172.20.22.65:8081";
	
	@Autowired
	protected ActivityDao activityDao;
	
	protected List<User> getFriendsFromUser(User user) {
		return userDao.find(user.getId()).getFriends();
	}
	
	protected User getUserFromSession(HttpSession session) {
		return (User)session.getAttribute("userLogged");
	}
	
	protected User getLoggedUser(HttpSession session) {
		User loggedUser = getUserFromSession(session);
		
		return userDao.findUserById(loggedUser.getId());
	}
	
	protected List<Campaign> getCampaignsFromUser(User user) {
		return userDao.find(user.getId()).getCampaigns();
	}
	
	protected List<Activity> getActivitiesFromUser(User user) {
		return activityDao.findActivitiesFromUser(user);
	}
	
	protected void createNewActivity(Long userId, String message) {
		Activity activity = new Activity();
		
		activity.setUserId(userId);
		activity.setMessage(message);
		activity.setDate(new Date());
		
		activityDao.save(activity);
	}
	
	public String getUrl() {
		return this.url;
	}
}

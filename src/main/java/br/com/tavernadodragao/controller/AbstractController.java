package br.com.tavernadodragao.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tavernadodragao.dao.UserDao;
import br.com.tavernadodragao.model.Campaign;
import br.com.tavernadodragao.model.User;

public abstract class AbstractController {

	@Autowired
	protected UserDao userDao;
	
	protected List<User> getFriendsFromUser(User user) {
		return userDao.find(user.getId()).getFriends();
	}
	
	protected User getUserFromSession(HttpSession session) {
		return (User)session.getAttribute("logged");
	}
	
	protected User getLoggedUser(HttpSession session) {
		User loggedUser = getUserFromSession(session);
		
		return userDao.findUserById(loggedUser.getId());
	}
	
	protected List<Campaign> getCampaignsFromUser(User user) {
		return userDao.find(user.getId()).getCampaigns();
	}
}

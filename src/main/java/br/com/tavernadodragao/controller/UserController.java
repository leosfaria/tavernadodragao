package br.com.tavernadodragao.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.tavernadodragao.model.User;

@Controller
public class UserController extends AbstractController {
	
	@RequestMapping("search")
	public String search(HttpServletRequest request, Model model) {
		
		User user = getUserFromSession(request.getSession());
		
		List<User> userList = userDao.findUsersByUsername(request.getParameter("searchInput"), user);
		
		model.addAttribute("userList", userList);
		
		List<User> friends = null;
		friends = userDao.find(user.getId()).getFriends();
	
		model.addAttribute("friends", friends);
		
		return "main";
	}
	
	@RequestMapping("addFriend")
	public String addFriend(HttpServletRequest request, Model model) {
		if (request.getParameter("friend_id") == null)
			return "redirect:main";

		Long friendId = Long.decode(request.getParameter("friend_id"));

		User friend = userDao.findUserById(friendId);
		friend.setConfirmPassword(friend.getPassword());

		User user = getLoggedUser(request.getSession());
		user.setConfirmPassword(user.getPassword());

		user.getFriends().add(friend);

		userDao.save(user);

		request.getSession().setAttribute("logged", user);

		return "redirect:main";
	}
	
	@RequestMapping("unFriend")
	public String unfriend(HttpServletRequest request, Model model) {
		if (request.getParameter("friend_id") == null)
			return "redirect:main";
		
		Long friendId = Long.decode(request.getParameter("friend_id"));
		
		User user = getLoggedUser(request.getSession());
		user.setConfirmPassword(user.getPassword());
	
		for (User friend : user.getFriends()) {
			if (friend.getId() == friendId) {
				user.getFriends().remove(friend);
				break;
			}
		}

		userDao.save(user);
		
		request.getSession().setAttribute("logged", user);
		
		return "redirect:main";
	}
}

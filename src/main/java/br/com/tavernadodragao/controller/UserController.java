package br.com.tavernadodragao.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.tavernadodragao.dao.UserDao;
import br.com.tavernadodragao.error.ErrorType;
import br.com.tavernadodragao.error.LoginError;
import br.com.tavernadodragao.model.User;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@RequestMapping("search")
	public String search(HttpServletRequest request, Model model) {
		
		User user = (User)request.getSession().getAttribute("logged");
		
		List<User> userList = userDao.findUsersByUsername(request.getParameter("searchInput"), user);
		
		model.addAttribute("userList", userList);
		
		return "main";
	}
	
	@RequestMapping("addFriend")
	public String addFriend(HttpServletRequest request, Model model) {
		if (request.getParameter("friend_id") == null)
			return "redirect:main";
		
		Long friendId = Long.decode(request.getParameter("friend_id"));
		
		User friend = userDao.findUserById(friendId);
		friend.setConfirmPassword(friend.getPassword());
		
//		userDao.sessionClose();
		
		User user = (User)request.getSession().getAttribute("logged");
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
		
		User user = (User)request.getSession().getAttribute("logged");
		user.setConfirmPassword(user.getPassword());
	
		for (User friend : user.getFriends()) {
			if (friend.getId() == friendId) {
				user.getFriends().remove(friend);
				break;
			}
		}

//		userDao.sessionClose();
		userDao.save(user);
		
		request.getSession().setAttribute("logged", user);
		
		return "redirect:main";
	}
}

package br.com.tavernadodragao.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.tavernadodragao.error.EditProfileError;
import br.com.tavernadodragao.error.ErrorType;
import br.com.tavernadodragao.error.LoginError;
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
		
		model.addAttribute("activities", getActivitiesFromUser(user));
		
		return "main";
	}
	
	@RequestMapping("addFriend")
	public String addFriend(HttpServletRequest request, Model model) {
		if (request.getParameter("friend_id") == null)
			return "redirect:main";

		Long friendId = Long.decode(request.getParameter("friend_id"));

		User friend = userDao.findUserById(friendId);
		
		User user = getLoggedUser(request.getSession());
		
		friend.getFriendsRequests().add(user);
		
		userDao.save(friend);
		
		createNewActivity(user.getId(), "You add " + friend.getUsername() + " to your friend list. Request pending...");
		
		request.getSession().setAttribute("userLogged", user);

		return "redirect:main";
	}
	
	@RequestMapping("acceptFriend")
	public String acceptFriend(HttpServletRequest request, Model model) {
		if (request.getParameter("accept_friend_id") == null)
			return "redirect:main";
		
		Long friendId = Long.decode(request.getParameter("accept_friend_id"));

		User friend = userDao.findUserById(friendId);
		
		User user = getLoggedUser(request.getSession());
		
		user.getFriendsRequests().remove(friend);
		
		if(request.getParameter("add") != null)
		{
			user.getFriends().add(friend);
			friend.getFriends().add(user);
			
			userDao.save(friend);
			
			createNewActivity(user.getId(), "You and " + friend.getUsername() + " are friends now!");
			createNewActivity(friendId, user.getUsername() + " approved your friendship request!");
		}

		userDao.save(user);
		
		request.getSession().setAttribute("userLogged", user);

		return "redirect:main";
	}
	
	@RequestMapping("unFriend")
	public String unfriend(HttpServletRequest request, Model model) {
		if (request.getParameter("friend_id") == null)
			return "redirect:main";
		
		Long friendId = Long.decode(request.getParameter("friend_id"));
		User friend = userDao.findUserById(friendId);
		
		User user = getLoggedUser(request.getSession());
		
		user.getFriends().remove(friend);
		friend.getFriends().remove(user);

		userDao.save(user);
		userDao.save(friend);
		
		request.getSession().setAttribute("userLogged", user);
		
		return "redirect:main";
	}
	
	@RequestMapping("editProfile")
	public String editProfile(HttpServletRequest request, Model model) {
		User userLogged = getLoggedUser(request.getSession());
		
		model.addAttribute("currentUser", userLogged);
		
		return "editProfile";
	}
	
	@RequestMapping("updateProfile")
	public String updateProfile(HttpServletRequest request, Model model, @Valid @ModelAttribute User user, BindingResult result) {
	
		User userLogged = getLoggedUser(request.getSession());
		
		EditProfileError error = verifyErrors(userLogged, user, request);
		
		if (error == null)
		{		
			userLogged.setUsername(user.getUsername());
			userLogged.setEmail(user.getEmail());
			
			if(!request.getParameter("newPassword").isEmpty())
				userLogged.setPassword(request.getParameter("newPassword"));
			
			userDao.save(userLogged);
		
			request.getSession().setAttribute("userLogged", userLogged);
			
			return "redirect:main";
		}
			
		model.addAttribute("profileError", error);
		model.addAttribute("currentUser", userLogged);
		
		return "editProfile";
	}
	
	private EditProfileError verifyErrors(User logged, User user, HttpServletRequest request) {
		EditProfileError error = null;
			
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		
		if (user.getUsername() == null || (!user.getUsername().equals(logged.getUsername()) && (user.getUsername().length() < 3 || user.getUsername().length() > 30)))
			error = new EditProfileError(ErrorType.INVALID_USERNAME, "Username deve ter de 3 caracteres a 30");
		else if (user.getEmail() == null || (!user.getEmail().equals(logged.getEmail()) && user.getEmail().length() == 0))
			error = new EditProfileError(ErrorType.INVALID_EMAIL, "Email deve ser um email válido");
		else if (!user.getEmail().equals(logged.getEmail()) && userDao.existsUser(user))
			error = new EditProfileError(ErrorType.EMAIL_ALREADY_IN_USE, "Email already in use");
		else if (newPassword != "" && (newPassword.length() < 3 || newPassword.length() > 15))
			error = new EditProfileError(ErrorType.INVALID_PASSWORD, "Password deve ter de 3 a 15 caracteres");
		else if (newPassword != "" && !newPassword.equals(confirmPassword))
			error = new EditProfileError(ErrorType.PASS_AND_CONFIRM_DOESNT_MATCH, "Password e Confirm Password doesn't match");
		else if (confirmPassword != "" && !newPassword.equals(confirmPassword))
			error = new EditProfileError(ErrorType.PASS_AND_CONFIRM_DOESNT_MATCH, "Password e Confirm Password doesn't match");
		else if (oldPassword != "" && !logged.getPassword().equals(oldPassword))
			error = new EditProfileError(ErrorType.CURRENT_PASS_DOESNT_MATCH, "Old Password doesn't match your current password");
		else if (oldPassword == "" && newPassword != "")
			error = new EditProfileError(ErrorType.CURRENT_PASS_DOESNT_PASSED, "Old Password need to be passed to change password");
		
		return error;
	}
}

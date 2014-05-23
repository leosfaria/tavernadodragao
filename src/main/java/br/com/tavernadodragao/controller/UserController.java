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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import br.com.tavernadodragao.error.EditProfileError;
import br.com.tavernadodragao.error.ErrorType;
import br.com.tavernadodragao.error.LoginError;
import br.com.tavernadodragao.model.User;
import br.com.tavernadodragao.service.UploadFile;

@Controller
public class UserController extends AbstractController {
	
	@Autowired
	private CommonsMultipartResolver multipartResolver;
	
	@RequestMapping("search")
	public String search(HttpServletRequest request, Model model) {
		
		User user = getLoggedUser(request.getSession());
		
		List<User> userList = userDao.findUsersByUsername(request.getParameter("searchInput"), user);
		
		model.addAttribute("userList", userList);
		
		List<User> friends = null;
		friends = userDao.find(user.getId()).getFriends();
	
		model.addAttribute("friends", friends);
		model.addAttribute("activities", getActivitiesFromUser(user));
		model.addAttribute("campaigns", getCampaignsFromUser(user));
		
		if(user.getFriendsRequests().size() > 0) {
			model.addAttribute("friendsRequestsCount", user.getFriendsRequests().size());
			model.addAttribute("friendsRequests", user.getFriendsRequests());
		}
		
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
	
	@RequestMapping(value="updateProfile", method=RequestMethod.POST)
	public String updateProfile(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model, @Valid @ModelAttribute User user, BindingResult result) throws Exception {
		User userLogged = getLoggedUser(request.getSession());
		
		EditProfileError error = verifyErrors(userLogged, user, request);
		
		if (error == null)
		{
			userLogged.setUsername(user.getUsername());
			userLogged.setEmail(user.getEmail());
			
			if(!request.getParameter("newPassword").isEmpty())
				userLogged.setPassword(request.getParameter("newPassword"));
			
			if (file != null && !file.isEmpty()) {
				String imgPath = UploadFile.uploadFile(file, "./src/main/webapp/resources/" + userLogged.getId() + "/avatar/");
				
				userLogged.setAvatarImgPath(imgPath);
			}
			
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
			error = new EditProfileError(ErrorType.INVALID_USERNAME, "Username must have 3 to 30 characters");
		else if (user.getEmail() == null || (!user.getEmail().equals(logged.getEmail()) && user.getEmail().length() == 0))
			error = new EditProfileError(ErrorType.INVALID_EMAIL, "Email must be a valid email account");
		else if (!user.getEmail().equals(logged.getEmail()) && userDao.existsUser(user))
			error = new EditProfileError(ErrorType.EMAIL_ALREADY_IN_USE, "Email already in use");
		else if (!newPassword.isEmpty() && (newPassword.length() < 3 || newPassword.length() > 15))
			error = new EditProfileError(ErrorType.INVALID_PASSWORD, "Password must have 3 to 15 characters");
		else if ((!newPassword.isEmpty() || !confirmPassword.isEmpty()) && !newPassword.equals(confirmPassword))
			error = new EditProfileError(ErrorType.PASS_AND_CONFIRM_DOESNT_MATCH, "Password e Confirm Password doesn't match");
		else if (!oldPassword.isEmpty() && !logged.getPassword().equals(oldPassword))
			error = new EditProfileError(ErrorType.CURRENT_PASS_DOESNT_MATCH, "Old Password doesn't match your current password");
		else if (oldPassword.isEmpty() && !newPassword.isEmpty())
			error = new EditProfileError(ErrorType.CURRENT_PASS_DOESNT_PASSED, "Old Password need to be passed to change password");
		
		return error;
	}
}

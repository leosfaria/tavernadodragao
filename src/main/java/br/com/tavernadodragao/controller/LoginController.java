package br.com.tavernadodragao.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.tavernadodragao.error.ErrorType;
import br.com.tavernadodragao.error.LoginError;
import br.com.tavernadodragao.model.User;

@Controller
public class LoginController extends AbstractController {
	
	@RequestMapping("/")
	public String login(HttpServletRequest request, Model model) {
		if (getUserFromSession(request.getSession()) != null)
			return "redirect:main";
		
		model.addAttribute("user", new User());

		return "login";
	}
	
	@RequestMapping("login")
	public String loginSubmit(HttpServletRequest request, Model model, User user) {
		
		User existingUser = userDao.findUser(user);
		
		if (existingUser != null)
		{
			request.getSession().setAttribute("userLogged", existingUser);
			
			existingUser.setTimeLogged(new Date());
			
			userDao.save(existingUser);
			
			return "redirect:main";
		}
		
		LoginError error = new LoginError(ErrorType.USER_OR_PASS_INVALID, "Email or password invalid");
		
		model.addAttribute("loginError", error);
		
		return "login";
	}
	
	@RequestMapping("main")
	public String loggedPage(Model model,HttpServletRequest request) {
		User user = getLoggedUser(request.getSession());
	
		model.addAttribute("friends", getFriendsFromUser(user));
		model.addAttribute("campaigns", getCampaignsFromUser(user));
		
		if(user.getFriendsRequests().size() > 0) {
			model.addAttribute("friendsRequestsCount", user.getFriendsRequests().size());
			model.addAttribute("friendsRequests", user.getFriendsRequests());
		}
		
		model.addAttribute("activities", getActivitiesFromUser(user));
		
		return "main";
	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("signup")
	public String signup(HttpServletRequest request, Model model, @Valid @ModelAttribute User user, BindingResult result) {
		
		if (!result.hasErrors())
		{
			LoginError error;
			
			try {
				if(!userDao.existsUser(user)) {
					if(user.getPassword().equals(user.getConfirmPassword()))
					{
						user.setTimeLogged(new Date());
						
						if(user.getAvatarImgPath() == null ||  user.getAvatarImgPath().isEmpty())
							user.setAvatarImgPath("../resources/css/images/yourImageDefault.jpg");
						
						userDao.save(user);
						
						request.getSession().setAttribute("userLogged", user);
						
						return "redirect:main";
					}
					else
					{
						error = new LoginError(ErrorType.PASS_AND_CONFIRM_DOESNT_MATCH, "Password e Confirm Password doesn't match");
					}
				}
				else
				{
					error = new LoginError(ErrorType.EMAIL_ALREADY_IN_USE, "Email already in use");					
				}
				
				model.addAttribute("loginError", error);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "login";
	}
}

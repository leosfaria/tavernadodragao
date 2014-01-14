package br.com.tavernadodragao.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.tavernadodragao.dao.CampaignDao;
import br.com.tavernadodragao.error.CampaignError;
import br.com.tavernadodragao.error.ErrorType;
import br.com.tavernadodragao.model.Campaign;
import br.com.tavernadodragao.model.User;

@Controller
public class CampaignController extends AbstractController {
	
	@Autowired
	private CampaignDao campaignDao;
	
	@RequestMapping("campaign")
	public String campaign(HttpServletRequest request, Model model) {
		User user = getUserFromSession(request.getSession());
		
		model.addAttribute("friends", getFriendsFromUser(user));
		
		return "campaign";
	}

	@RequestMapping("enterCampaign")
	public String enterCampaign(HttpServletRequest request, Model model) {
		User user = getUserFromSession(request.getSession());
		
		Campaign campaign = campaignDao.getCampaignByName(request.getParameter("campaignName"));
		
		if (campaign.getMaster() == user.getId()) //Alterar depois para campaign.getMaster() != user.getId()
		{
			model.addAttribute("campaign", campaign);
			model.addAttribute("party", campaign.getPlayers());
			model.addAttribute("master", userDao.findUserById(campaign.getMaster()));
			
			return "campaignPlayer";
		}
		
		return "campaignMaster";
	}
	
	@RequestMapping("campaignChat")
	public String chatCampaign(HttpServletRequest request, Model model) {

		return enterCampaign(request, model);
	}
	
	@RequestMapping("createCampaign")
	public String createCampaign(HttpServletRequest request, Model model, @Valid @ModelAttribute Campaign campaign, BindingResult result) {

		User user = getLoggedUser(request.getSession());
		
		if (!result.hasErrors())
		{		
			CampaignError error;
			
			try {
				if(!campaignDao.existsCampaign(campaign, user)) {
					ArrayList<User> userList = new ArrayList<User>();
					
					campaign.setMaster(user.getId());
					
					for (User friend : user.getFriends()) {
						if (request.getParameter(friend.getUsername()) != null)
							userList.add(friend);
					}
					
					campaign.setPlayers(userList);
					
					user.getCampaigns().add(campaign);
					
					campaignDao.save(campaign);
					userDao.save(user);
					
					for (User friend : userList) {
						friend.getCampaigns().add(campaign);
						
						userDao.save(friend);
						
						createNewActivity(friend.getId(), user.getUsername() + " create a new campaign " + campaign.getName() + " including you!");
					}
					
					createNewActivity(user.getId(), "You create a new campaign: " + campaign.getName() + "!");
					
					return "redirect:main";
				}
				else
				{
					error = new CampaignError(ErrorType.CAMPAIGN_ALREADY_EXISTS, "Campaign already in use");					
				}
				
				model.addAttribute("campaignError", error);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		model.addAttribute("friends", getFriendsFromUser(user));
		
		return "campaign";
	}
}

package br.com.tavernadodragao.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.tavernadodragao.dao.CampaignDao;
import br.com.tavernadodragao.model.Campaign;
import br.com.tavernadodragao.model.Message;
import br.com.tavernadodragao.model.User;

@Controller
public class ChatController extends AbstractController {

	@Autowired
	private CampaignDao campaignDao;
	
	@RequestMapping("chatCampaign")
	public @ResponseBody String chatCampaign(HttpServletRequest request, Model model) {
		User user = getUserFromSession(request.getSession());
		
		Campaign campaign = campaignDao.getCampaignByName(request.getParameter("campaignName"));
		
		String message = request.getParameter("message");
		
		if (message != null && !message.equals(""))
		{
			Message msg = new Message();
			msg.setMessage(message);
			msg.setDate(new Date());
			msg.setUsername(user.getUsername());
			
			campaign.getMessages().add(msg);
			
			campaignDao.save(campaign);
		}
		
		return "{ user: '" + user.getUsername() + "', msg: '" + message + "' }";
	}
}

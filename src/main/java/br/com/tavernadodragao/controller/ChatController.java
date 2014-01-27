package br.com.tavernadodragao.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.tavernadodragao.dao.CampaignDao;
import br.com.tavernadodragao.dao.MessageDao;
import br.com.tavernadodragao.model.Campaign;
import br.com.tavernadodragao.model.Message;
import br.com.tavernadodragao.model.User;

@Controller
public class ChatController extends AbstractController {

	@Autowired
	private CampaignDao campaignDao;
	
	@Autowired
	private MessageDao messageDao;
	
	@RequestMapping("chatCampaign")
	public @ResponseBody String chatCampaign(HttpServletRequest request, Model model) {
		User userLogged = getLoggedUser(request.getSession());
		
		Campaign campaign = campaignDao.getCampaignById(Long.parseLong(request.getParameter("campaignId")));
		
		String message = request.getParameter("message");
		
		String response = "[";
		
		if (message != null && !message.equals(""))
		{
			Message msg = new Message();
			msg.setMessage(message);
			msg.setDate(new Date());
			msg.setFrom(userLogged.getUsername());
			msg.setTo(campaign.getId().toString());
			msg.setType("CAMPAIGN");
			
			messageDao.save(msg);
			
			for (User player : campaign.getPlayers()) {
				if (player.getId() != userLogged.getId()) {
					player.getMessagesPendings().add(msg);
					userDao.save(player);
				}
			}
			
			User master = userDao.findUserById(campaign.getMasterId());
			
			if ( master.getId() != userLogged.getId() )
			{
				master.getMessagesPendings().add(msg);
				userDao.save(master);
			}
			
			response = "[{ user: '" + userLogged.getUsername() + "', msg: '" + message + "' }]";
		}
		else
		{
			ArrayList<Message> msgs = new ArrayList<Message>();
			
			for (Message messagePending : userLogged.getMessagesPendings()) {
				if (messagePending.getType().equals("CAMPAIGN") && messagePending.getTo().equals(campaign.getId().toString()))
				{
					response = response.concat("{ user: '" + messagePending.getFrom() + "', msg: '" + messagePending.getMessage() + "' },");
					msgs.add(messagePending);
				}
			}
			
			for (Message msg : msgs) {
				userLogged.getMessagesPendings().remove(msg);
				userLogged.getMessagesReceived().add(msg);
			}
			
			userDao.save(userLogged);
			
			response = response.concat("]");
		}
		
		return response;
	}
}

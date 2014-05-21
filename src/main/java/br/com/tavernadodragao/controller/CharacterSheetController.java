package br.com.tavernadodragao.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import br.com.tavernadodragao.dao.CampaignDao;
import br.com.tavernadodragao.dao.CharactersheetDao;
import br.com.tavernadodragao.dao.UserDao;
import br.com.tavernadodragao.model.Charactersheet;
import br.com.tavernadodragao.model.User;
import br.com.tavernadodragao.service.UploadFile;

@Controller
public class CharacterSheetController extends AbstractController {

	@Autowired
	private CommonsMultipartResolver multipartResolver;
	
	@Autowired
	private CharactersheetDao charactersheetDao;
	@Autowired
	private CampaignDao campaignDao;
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("character")
	public String character(HttpServletRequest request, Model model) {
		User user = getUserFromSession(request.getSession());
		
		model.addAttribute("existingCampaigns", campaignDao.getCampaigns(user));
		
		return "charactersheet";
	}
	
	@RequestMapping(value="uploadCharacterSheet", method=RequestMethod.POST) 
	public  String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) throws Exception {
		User user = getLoggedUser(request.getSession());
		
		String path = "./src/main/webapp/resources/" + user.getId() + "/";
		String name = file.getOriginalFilename();
		
		UploadFile.uploadFile(file, path);
		
		Charactersheet character = new Charactersheet();
		
		character.setName(name);
		character.setImagePath(path + name);
		
		charactersheetDao.save(character);
		
		user.getCharactersheets().add(character);
		
		userDao.save(user);
		
		model.addAttribute("charactersheet", character);
		model.addAttribute("existingCampaigns", campaignDao.getCampaigns(user));
		
		return "charactersheet";
	}
}

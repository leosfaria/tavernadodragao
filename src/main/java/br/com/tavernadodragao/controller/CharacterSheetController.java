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

import br.com.tavernadodragao.dao.CharactersheetDao;
import br.com.tavernadodragao.model.User;

@Controller
public class CharacterSheetController extends AbstractController {

	@Autowired
	private CommonsMultipartResolver multipartResolver;
	
	@Autowired
	private CharactersheetDao charactersheetDao;
	
	@RequestMapping(value="uploadCharacterSheet", method=RequestMethod.POST) 
	public  String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) throws Exception {
		User user = getUserFromSession(request.getSession());
		
		String dirPath = "./src/main/webapp/resources/" + user.getUsername() + "/";
		File dir = new File(dirPath);
		
		if(!dir.exists())
			dir.mkdir();
		
		File img = new File(dirPath + file.getOriginalFilename());

		file.transferTo(img);
		
		return "main";
	}
}

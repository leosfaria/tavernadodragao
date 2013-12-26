package br.com.tavernadodragao.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.tavernadodragao.dao.TableDao;
import br.com.tavernadodragao.dao.UserDao;
import br.com.tavernadodragao.model.Table;
import br.com.tavernadodragao.model.User;

@Controller
public class TableController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TableDao tableDao;
	
	@RequestMapping("table")
	public String table() {
		return "table";
	}
	
	@RequestMapping("createTable")
	public String createTable(HttpServletRequest request, Model model) {
		Table table = new Table();
		
		table.setName(request.getParameter("tableName"));
		
		ArrayList<User> userList = new ArrayList<User>();
		
		User loggedUser = (User)request.getSession().getAttribute("logged");
		userList.add(loggedUser);
		
		for (User friend : loggedUser.getFriends()) {
			if (request.getParameter(friend.getUsername()) != null)
				userList.add(friend);
		}
		
		table.setUsers(userList);
		
		tableDao.save(table);
		
		return "table";
	}
}

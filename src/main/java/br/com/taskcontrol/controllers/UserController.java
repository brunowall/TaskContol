package br.com.taskcontrol.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import br.com.taskcontrol.models.User;
import br.com.taskcontrol.repository.UserDao;

@RestController
public class UserController {
	@Autowired
	private UserDao userDao;
	@PostMapping("/user")
	public User addUser(@Valid @ModelAttribute("user") User user, BindingResult res){
		if(!res.hasFieldErrors()){ // se não possui erros de validacao
			this.userDao.save(user);
			
		}
		return null;
	}
	
	@PostMapping("/user/login")
	public User login(@Valid @ModelAttribute("user") User user,BindingResult res,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		if(req.getSession().getAttribute("user")!=null 
				&& !res.hasFieldErrors("username") 
				&& !res.hasFieldErrors("password")){
			User userData=this.userDao.getOne(user.getUsername());//usuario com o username passado
			if(userData!=null && userData.getPassword().equals(user.getPassword())){
				req.getSession().setAttribute("user", userData);
				return null;
			}
		}
		
		resp.sendError(401);
		return null;
	}
	
	@GetMapping("/user/logout")
	public User logout(HttpServletRequest req,HttpServletResponse res) throws IOException{
		if(req.getSession().getAttribute("user")!=null){
			req.getSession().invalidate();
		}else{
			res.sendError(401);//codigo de erro nao autorizado
		}
		return null;
	}
	
}

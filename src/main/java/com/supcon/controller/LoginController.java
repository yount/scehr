package com.supcon.controller;

import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.supcon.util.CommonUtil;

@Controller
public class LoginController {
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(HttpSession httpSession) {
		ModelAndView mov = new ModelAndView("login");
		return mov;
	}
	
	@RequestMapping(value = "toRegister")
	public ModelAndView toRegister(HttpSession httpSession) {
		ModelAndView mov = new ModelAndView("register");
		return mov;
	}
}

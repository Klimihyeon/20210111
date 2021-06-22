package com.spring.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	
	@RequestMapping("pre_test")
	public void pre_test() {}
	
	@RequestMapping("post_test")
	public void post_test(Model model) {
		model.addAttribute("turn","after_test");
	}
	
	
	@RequestMapping("after_test")
	public void after_test() throws SQLException{
		throw new SQLException();
	}
	
}

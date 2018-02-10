package net.slipp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	
	@GetMapping("")
	public String home() {
		return "index";
	}

	@GetMapping("user/login") //
	public String login(Model model) {
		//model.addAttribute("users", userRepository.findAll());
		return "/user/login";
	}
	
	/*
	@GetMapping("user/form") // 
	public String form(Model model) {
		//model.addAttribute("users", userRepository.findAll());
		return "/user/form";
	}
	*/
	@PostMapping("user/list") // 
	public String list(Model model) {
	
		return "/user/list";
	}
}

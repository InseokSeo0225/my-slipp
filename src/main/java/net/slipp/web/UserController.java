package net.slipp.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.slipp.User;

@Controller
public class UserController {
	private List<User> users = new ArrayList<User>();

	@PostMapping("/create")
	public String create(User user) {

		System.out.println(user);

		// System.out.println(user.getName()); //되긴 되는 구나...ㅋㅋㅋㅋㅋㅋ , 근데 해줄 필요가 없지.
		users.add(user);

		return "redirect:/list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("users", users);
		return "list";
	}
}   

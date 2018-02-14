package net.slipp.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.domain.User;
import net.slipp.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository; // 그냥 땡겨써~~!! 어딘가 생성이 되이꺼꺼꺼니~~

	@GetMapping("/loginForm")
	public String loginForm() {

		return "/user/login";
	}

	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {

		User user = userRepository.findByUserId(userId);

		System.out.println(userId);
		System.out.println(password);
		// System.out.println(user.getPassword()); //못가지고 오네...

		System.out.println(user);
		if (user == null) {
			return "redirect:/users/loginForm";
		}

		if (!password.equals(user.getPassword())) {
			return "redirect:/users/loginForm";
		}

		System.out.println("Login Success!!");
		session.setAttribute("sessioneduser", user);

		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessioneduser");
		return "redirect:/";
	}

	@PostMapping("") // POST 메서드: 새로운 사용자를 추가하겠구나... 강의 3-3
	public String create(User user) {
		System.out.println(user);
		// System.out.println(user.getName()); //되긴 되는 구나...ㅋㅋㅋㅋㅋㅋ , 근데 해줄 필요가 없지.
		userRepository.save(user);
		return "redirect:/users";
	}

	@GetMapping("") // 이건 get이자나...
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		System.out.println("입력하고 여기.....list(리스트)");
		return "/user/list";
	}

	@GetMapping("/form") // 회원가입
	public String form() {

		return "/user/form";
	}

	@GetMapping("/{id}/form")
	public String updateFrom(@PathVariable Long id, Model model, HttpSession session) {
		
		//User user = session.getAttribute("sessioneduser");
		Object tempUser = session.getAttribute("sessioneduser");
		
		if(tempUser == null) {
			return "redirect:/users/loginForm";
		}
		
		User sessionedUser = (User)tempUser;
		if(!id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("You Can't update another user");
			}
		
		
		
		// model.addAttribute("user", userRepository.findAll());
		System.out.println("사용자 수정 (udpateFrom)");
		User user = userRepository.findOne(id); //or		
		//User user = userRepository.findOne(sessionedUser.getId()); //이렇게 해도 됨.
		model.addAttribute("user", user);
		return "/user/updateForm";
	}

	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User newUser, HttpSession session) {
		
		Object tempUser = session.getAttribute("sessioneduser");
		if(tempUser == null) {
			return "redirect:/users/loginForm";
		}
		User sessionedUser = (User)tempUser;
		if(!id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("You Can't update another user");
			}
		
		User user = userRepository.findOne(id);
		user.update(newUser);
		userRepository.save(user);
		return "redirect:/users";
	}

}

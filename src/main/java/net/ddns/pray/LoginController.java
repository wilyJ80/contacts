package net.ddns.pray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String getLogin() {
		return "login/login";
	}

	@GetMapping(value = "/login", params = "error")
	public String loginError(RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("errorMessage", "Invalid username or password.");
		return "redirect:/login";
	}

	@GetMapping(value = "/login", params = "logout")
	public String getLoginLogout(RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("logoutMessage", "You have been logged out.");
		return "redirect:/login";
	}
}

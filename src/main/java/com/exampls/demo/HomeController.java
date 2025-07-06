package com.exampls.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private LoginRepository loginRepo;

    @GetMapping("/")
    public String home() {
        return "VIVEK"; // loads vivek.html
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute Login login) {
        loginRepo.save(login);
        return "redirect:/";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
    @GetMapping("/members")
    public String membersPage() {
        return "members";
    }

    

    // ❌ Removed the conflicting PostMapping("/feedback")
}

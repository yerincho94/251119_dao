package kr.java.dao.controller;

import jakarta.servlet.http.HttpSession;
import kr.java.dao.model.dto.UserAccountDTO;
import kr.java.dao.service.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping // login, signup..
public class AuthController {
    private final UserAccountService service;

    public AuthController(UserAccountService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserAccountDTO dto, HttpSession session) {
        try {
            if(service.login(dto)) {
                // 세션에 username 넣기
                session.setAttribute("username",dto.getUsername());
                return "redirect:/";
            }
            System.out.println("비밀번호가 틀림");
        } catch (Exception e) {
            System.out.println("해당 유저네임이 없음");
        }
        return "redirect:/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserAccountDTO dto) {
        service.signup(dto);
        return "redirect:/";
    }

}

package edu.kh.project.admin.controller;

import edu.kh.project.admin.model.service.AdminService;
import edu.kh.project.member.model.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("admin")
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
public class AdminController {

    private final AdminService service;

    @PostMapping("login")
    public Member login(@RequestBody Member inputMember, Model model) {

        Member loginMember = service.login(inputMember);

        if(loginMember == null) {
            return null;
        }

        model.addAttribute("loginMember", loginMember);
        return loginMember;
    }
}

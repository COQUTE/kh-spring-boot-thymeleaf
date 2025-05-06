package com.study.project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.project.member.model.dto.Member;
import com.study.project.member.model.service.MemberService;

@Controller
@RequestMapping("member")
@SessionAttributes({"loginMember"})
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@PostMapping("login")
	public String login(Member inputMember, Model model, RedirectAttributes ra) {
		
		Member loginMember = service.login(inputMember);
		
		if(loginMember == null) {
			ra.addFlashAttribute("message", "아이디 또는 비밀번호 불일치");

		} else {
			model.addAttribute("loginMember", loginMember);
		}
		
		return "redirect:/";
	}
}

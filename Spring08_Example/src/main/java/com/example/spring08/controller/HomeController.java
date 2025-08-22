package com.example.spring08.controller;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home(Model model) {
		
		List<String> notice=List.of("공지사항 입니다", "어쩌구..","절씨구.");
		model.addAttribute("notice", notice);
		
	
		//Thymeleaf 템플릿 페이지에서 응답 하기
		return "home";
	}
}

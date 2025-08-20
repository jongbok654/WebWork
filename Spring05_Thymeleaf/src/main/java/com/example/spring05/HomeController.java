package com.example.spring05;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		//오늘의 인물 (응답에 필요한 데이터라고 가정하자)
		String personToday="김구라";
		//응답에 필요한 데이터를 Model 객체에 담기
		model.addAttribute("personToday", personToday);
		
		//공지사항 (DB 에서 읽어온 데이터라고 가정)
		List<String> notice=List.of("Thymeleaf 를 배우자", "어쩌구...", "저쩌구...");
		//응답에 필요한 데이터를 Model 객체에 담기
		model.addAttribute("notice", notice);
		
		// /templates/home.html  thymeleaf 페이지로 응답을 위임하기
		return "home";
	}
}








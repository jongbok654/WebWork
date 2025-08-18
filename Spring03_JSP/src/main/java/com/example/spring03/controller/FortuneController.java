package com.example.spring03.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FortuneController {

	/*
	 * HttpServletRequest 는 HTTP 의 모든 기능을 다루는 객체
	 * Model 은 view page 로 넘길 데이터만 담는 객체( 더 편리하게 사용할 수 있따)
	 * Model 객체도 컨트롤러 메소드와 매개변수에 선언안하면 자동으로 전달
	 */
	@GetMapping("/fortune2")
	public String fortune2(Model model) {
		String fortuneToday="동쪽으로 가면 귀인 출몰";
		model.addAttribute("fortuneToday",fortuneToday);
		
		return "fortune";
	}
	
	@GetMapping("/fortune")
	public String fortune(HttpServletRequest request) {
		
		//DB 에서 읽어온 오늘의 운세라고 가정하자
		String fortuneToday = "서쪽에 가면 귀인 출몰";
		request.setAttribute("fortuneToday", fortuneToday);
		// /WEB-INF/view/fortune.jsp 페이지로 이동함
		return "fortune";
	}
}

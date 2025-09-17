package com.example.spring10.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1")
@RestController
public class HomeController {
	
	@GetMapping("/notice")
	public List<String> notice(){
		return List.of("react 로 app 구현중입니다","어쩌구...","저쩌구...");
	}
}

package com.example.spring09.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring09.dto.MemberListRequest;
import com.example.spring09.dto.MemberPageResponse;
import com.example.spring09.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/v2")
@RestController
@RequiredArgsConstructor
public class RestMemberController2 {
	
	private final MemberService memberService;
	
	@GetMapping("/members")
	public MemberPageResponse list(MemberListRequest request) {
		//@RequestParm(defaultValue = "1") int pageNum
		//만일 pageNum 이 넘어오지 않는다면 기본값 1을 생성한다
		
		return memberService.getPage(request);
	}
}

package com.example.spring08.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring08.dto.BoardDto;
import com.example.spring08.dto.BoardListResponse;
import com.example.spring08.dto.CommentDto;
import com.example.spring08.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService service;
	
	@GetMapping("/board/view")
	public String boardView(int num,Model model) {
		//서비스를 이용해서 응답에 필요한 데이터 얻어내기
		BoardDto dto =service.getdetail(num);
		List<CommentDto> comments = service.getComments(num);
		//모델 객체에 담고
		model.addAttribute("dto",dto);
		model.addAttribute("comments", comments);
		
		//로그인된 userName 얻어내기
		//로그인을 안했으면 "annonymousUser"가 리턴된다
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(userName);
		boolean isLogin = userName.equals("annonymousUser") ? false : true;
		//위의 추가 정보도 모델 객체에 담는다
		model.addAttribute("userName", userName);
		model.addAttribute("isLogin", isLogin);
		//타임리프 페이지에서 응답하기
		return "board/view";
	}
	
	/*
	 * @ModelAttribute 는 view page 에서 필요한 값을 대신 Model 객체에 담아준다
	 */
	@PostMapping("/board/save")
	public String boardSave(@ModelAttribute("dto") BoardDto dto) {
		//글 작성자
		String userName= SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(userName);
		//서비스를 이용해서 글을 저장하기
		service.createdContent(dto);
		return "board/save";
	}
	
	
	@GetMapping("/board/new-form")
	public String newForm() {
		return "board/new-form";
	}
	
	/*
	 * @RequestParm 어노테이션을 이용하면 요청 파라미터를 추출하면서 해당 값이 없으면 defaultValue 를 설정할 수 있따
	 */
	
	@GetMapping("/board/list")
	public String list(Model model,
			@RequestParam(defaultValue ="1") int pagenum,
			@RequestParam(defaultValue ="") String keyword) {
		//응답에 필요한 데이터를 얻어내서
		BoardListResponse listResponse = service.getBoardList(pagenum, keyword);
		//모델 객체에 담고
		model.addAttribute("dto", listResponse);
		
		//타임리프 페이지에 삽입
		return "board/list";
	}
}

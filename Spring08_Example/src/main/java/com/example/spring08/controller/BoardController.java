package com.example.spring08.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring08.dto.BoardDto;
import com.example.spring08.dto.BoardListResponse;
import com.example.spring08.dto.CommentDto;
import com.example.spring08.service.BoardService;
import com.example.spring08.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService service;
	private final CommentService commentService;
	
	//게시글 수정 반영 요청 처리
	@PostMapping("/board/update")
	public String boardUpdate(BoardDto dto,RedirectAttributes ra) {
		//글 수정 반영하고
		service.updateContent(dto);
		//리다일렉트 이동해서 출력할 메시지도 담는다
		ra.addFlashAttribute("message", "게시글을 성공적으로 수정했습니다!");
		//글 자세히 보기로 리다일렉트 이용
		return "redirect:/board/view?num="+dto.getNum();
	}
	
	@GetMapping("/board/edit")
	public String boardEdit(int num,Model model) {
		model.addAttribute("dto", service.getData(num));
		return "board/edit";
	}
	
	
	@GetMapping("/board/delete")
	public String boardDelete(int num) {
		return "board/delete";
	}
	
	
	@GetMapping("/board/view")
	public String boardView(BoardDto requestDto,Model model) {
		
		/*
		 * reqiestDto 에는 자세히 보여줄 글의 num 와
		 * search(검색조건), keyword(검색어)가 들어 있을 수도 있디
		 * 검색어가 없는 경우는 search 와 keyword 에는 null 이 들어있다.
		 */
		
		//서비스를 이용해서 응답에 필요한 데이터 얻어내기
		BoardDto dto =service.getDetail(requestDto);
		
		String query="";
		if(requestDto.getKeyword() != null) {
			query="&search="+requestDto.getSearch()+ "&keyword=" + dto.getKeyword();
		}
		
		model.addAttribute("query", query);
		
		//댓글 목록은 원 그르이 글 번호를 전달해서 얻어낸다
		List<CommentDto> comments = commentService.getComments(requestDto.getNum());
		//모델 객체에 담고
		model.addAttribute("dto",dto);
		//model.addAttribute("comments", comments);
		model.addAttribute("commentList", comments); // 템플릿 키에 맞춤
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
	 * @RequestParam 어노테이션을 이용하면 요청 파라미터를 추출하면서 해당 값이 없으면 defaultValue 를 설정할 수 있따
	 */
	
	@GetMapping("/board/list")
	public String list(Model model,
			@RequestParam(defaultValue ="1") int pageNum,
			BoardDto dto) {
		//BoardDto 객체에는 keyword 와 search 가 있을 수도 있다 (없을 수도 있다 null)
		
		//응답에 필요한 데이터를 얻어내서
		BoardListResponse listResponse = service.getBoardList(pageNum, dto);
		//모델 객체에 담고
		model.addAttribute("dto", listResponse);
			
		//타임리프 페이지에 삽입
		return "board/list";
	}
}

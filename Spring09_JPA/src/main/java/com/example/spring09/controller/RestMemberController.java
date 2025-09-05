package com.example.spring09.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring09.dto.MemberDto;
import com.example.spring09.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

//컨트롤러에서 리턴하는 데이터를 json 으로 응답하고자 할 때 사용하는 어노테이션
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class RestMemberController {
	
	private final MemberService memberService;
	
	/*
	 * @RestController 는 컨트롤러에서 리턴한 List 나 dto 를 json 문자열로 변경해서 응답한다
	 * 
	 *  List 는 [] 로 변경
	 *  Map 이나 dto 는 {} 로 변경
	 *  List<dto> 는 [{},{},{},....] 형식으로 변경
	 *  dto 의 필드 구성에 따라 좀 더 복잡한 형식의 {} 가  구성될 수도 있다
	 *  {
	 *  	"num":1,
	 *  "name":"kim",
	 *  "hobby":["xxx","yyy"],
	 *  "info":{}
	 * 
	 *  }
	 */
	
	@Operation(summary ="회원 정보 조회" , description = "전의 회원용 페이지 없이 반환합니다")
	@GetMapping("/members")
	public List<MemberDto> list(){
		List<MemberDto> list=memberService.getAll();
		return list;
	}
	//회원 추가 요청
	@PostMapping("/members")
	public MemberDto create(@RequestBody MemberDto dto) {
		
		return memberService.addMember(dto);
	}
	
	//회원 한 명의 정보 요청처리
	@GetMapping("/members/{num}")
	public MemberDto detail(@PathVariable int num) {
		return memberService.getMember(num);
	}
	
	//회원 정보 삭제 요청 처리
	@DeleteMapping("/members/{num}")
	public MemberDto delete(@PathVariable int num) {
		return memberService.deleteMember(num);
	}
	
	//회원 정보 전체 수정 요청 처리
	@PutMapping("/members/{num}")
	public MemberDto update(@PathVariable int num,@RequestBody MemberDto dto) {//json 문자열이 전송되면 @RequestBody 어노테이션이 필요하다
		//수정할 회원의 번호를 dto에 넣어준다
		dto.setNum(num);
		memberService.updateMember(dto);
		return dto;
	}
	
	@GetMapping("/members/hello")
	public String hello() {
		
		return "hello,world!";
	}
	@GetMapping("/members/hello2")
	public MemberDto hello2() {
		return MemberDto.builder().num(1).name("김구라").addr("노량진").build();
	}
	@GetMapping("/members/hello3")
	public List<String> hello3(){
		return List.of("김구라","해골","원숭이");
	}
}

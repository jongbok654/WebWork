package com.example.spring09.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring09.dto.ClientDto;
import com.example.spring09.service.ClientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/*
 * -client 목록 요청
 * 
 * Get "/client/list" <=> GET "/clients"
 * 
 * -client 상세 보기
 * 
 * GET"/client/detail?num=x" <=> GET "/clients/x"
 * 
 * -clients 추가 form 요청
 * 
 * GET "/client/new" <=> GET "/clients/new"
 * 
 * -client 실제 추가 요청
 * 
 * POST "/client/save" <=> POST "/clients"
 * 
 * -client 수정 form 요청
 * 
 * GET "/client/deit?num=x" <=> GET "/clients/x/edit:
 * 
 * -cleint 수정 반영 요청
 * Post "/client/update" <=> Post "/clients/x"
 */

@RequestMapping("v1")
@RestController
@RequiredArgsConstructor
public class RestClientController {
	private final ClientService clientService;

	
	@GetMapping("/clients")
	public List<ClientDto> list(){
		List<ClientDto> list= clientService.getClients();
		
		return list;
	}
	
	@GetMapping("/clients/new")
	public String newForm(Model model) {
		//만일 "clientDto" 라는 키값으로 저장되어 있는 값이 model 객체에 없다면
		if(!model.containsAttribute("clientDto")) {
			//빈 ClientDto 객체라도 전달을 해 주어야 한다! 전달해 주지 않으면 thymeleaf 페이지에서 에러 발생
			model.addAttribute("clientDto",new ClientDto());
		}
		return "clients/new";
	}
	
	/*
	 * @Valid 어노테이션을 이용해서 dto 의 필드를 자동 검증하기 위한 의존 dependency 를 pom.xml 에 추가
	 * 
	 * <dependency>
		  <groupId>org.springframework.boot</groupId>
		  <artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		
		@Valid 가 dto 의 어떤 필드를 어떤 조건으로 검증할지 dto 클래스에 표시를 해야 한다
		검증 결과가 BindingResult 객체에 담겨서 전달이 된다
		
		@Valid 로 검증을 한 dto 객쳅젼수 선언 바로 위에 BindingResult 매개변수를 선언해야 한다
	 */
	@PostMapping("/clients")
	public Long create(@RequestBody ClientDto dto) {
		
		return clientService.addClient(dto);
	}
	
	//고객정보 상세보기 요청 처리
	@GetMapping("/clients/{num}")
	public ClientDto detail(@PathVariable Long num) {
		
		return clientService.getClient(num);
	}

	@PutMapping("/clients/{num}")
	public ClientDto update(@PathVariable Long num, @Valid @RequestBody ClientDto dto) {
		
		  dto.setNum(num);
		    clientService.update(dto);    
		return clientService.getClient(num);  
	}
	
	@DeleteMapping("/clients/{num}")
	public ClientDto delete(@PathVariable Long num) {
		return clientService.deleteClient(num);
	}

	
	
}

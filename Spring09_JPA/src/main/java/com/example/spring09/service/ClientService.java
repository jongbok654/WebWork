package com.example.spring09.service;

import java.time.LocalDate;
import java.util.List;

import com.example.spring09.dto.ClientDto;

public interface ClientService {

	//회원 정보 추가
	Long addClient(ClientDto dto);
	
	//회원 목록 조회
	List<ClientDto> getClients();
	
	//개인정보 수정 페이지용: 단건 조회
	ClientDto getClient(long num);
	
	//생일 입력/수정(개인정보 수정)
	void updateBirthDay(Long num,LocalDate birthday);
	
	//전체 수정(이름과 생일)
	void update(ClientDto dto);
}

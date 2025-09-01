package com.example.spring09.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring09.dto.MemberDto;
import com.example.spring09.entity.Member;
import com.example.spring09.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

//서비스 클래스에 붙여줄 어노테이션
@Service
@RequiredArgsConstructor  
// lombok 의 생성자를 자동으로 만들어 주도록 한다
public class MemberServiceImpl implements MemberService{
	
	private final MemberRepository memberRepo;
	
	@Override
	public List<MemberDto> getAll() {
		//전체 회원목록을 얻엉온다 (Entity 의 목록)
		List<Member> list=memberRepo.findAll();
		
		List<MemberDto> dtoList = new ArrayList<>();
		for(Member tmp:list) {
			MemberDto dto=MemberDto.builder()
					.num(tmp.getNum())
					.name(tmp.getName())
					.addr(tmp.getAddr())
					.build();
			dtoList.add(dto);
		}
		
		return dtoList;
	}

	@Override
	public MemberDto getMember(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMember(MemberDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMember(MemberDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMember(int num) {
		// TODO Auto-generated method stub
		
	}

	
	

	
	
}
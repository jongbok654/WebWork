package com.example.spring08.aop;

import org.springframework.stereotype.Component;
import com.example.spring08.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Messenger {


	public void sendGreeting(String msg) {
		//System.out.println("안녕하세요" + msg);
		log.debug("오늘의 인사:"+msg);
	}

	public void sendMember(MemberDto dto) {
		if(dto!=null) {
		log.debug(dto.toString());
		}
	}
	
	public String getMessage() {
		//System.out.println("getMessage() 메소드가 수행합니다");
		log.debug("getMessage() 메소드가 실행 됩니다");
		return "열심히 공부하기";
	}
}

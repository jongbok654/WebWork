package com.example.spring08.service;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.stereotype.Service;

import com.example.spring08.dto.PwdChangeRequest;
import com.example.spring08.dto.UserDto;
import com.example.spring08.exception.PasswordException;
import com.example.spring08.repository.UserDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserDao dao;
	//비밀번호를 암호화 하기 위한 객체로 spring bean container 로 부터 주입 받는다.
	private final PasswordEncoder encoder;
	
	//사용자를 추가하는 메소드
	@Override
	public void createUser(UserDto dto) {
		//날것의 비밀번호를 암호화 해서
		String encodedePwd = encoder.encode(dto.getPassword());
		//dto에 다시 담는다
		dto.setPassword(encodedePwd);
		dao.insert(dto);
		
	}

	@Override
	public UserDto getUser(String name) {
		
		return dao.getByUserName(name);
	}

	@Override
	public void updatePassword(PwdChangeRequest pcr) {
	//로그인된 userName
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		//DB 에 저장된 암호화된 비밀번호를 읽어온다
		UserDto dto=dao.getByUserName(userName);
		String encodedPwd=dto.getPassword();
		//암호화된 비밀번호와 입력한 비밀번호를 비교해서 일치하는지 확인하기
		boolean isdValid=BCrypt.checkpw(pcr.getPassword(), encodedPwd);
		//만일 일치하지 않으면 예외 발생 시키기
		if(!isdValid) {
			throw new PasswordException("기존 비밀번호와 일치하지 않습니다");
		}
		//일치하면 새 비밀번호를 암호화 해서 pcr 객체에 담은 다음 DB 에 수정 반영한다.
		
		
	}

	@Override
	public Map<String, Object> canUseId(String id) {
		// id 를 이용해서 DB 에 해당 아이디로 가입된 정보가 있는지 읽어와 본다 (없으면 null)
		UserDto dto=dao.getByUserName(id);
		//id 가 사용가능한지 여부
		boolean canUse = dto == null ? true : false;
		return Map.of("canUse",canUse);
	}


	
}

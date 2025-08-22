package com.example.spring08.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring08.dto.UserDto;
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
		
		return null;
	}
	
}

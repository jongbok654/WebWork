package com.example.spring08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.example.spring08.test.WritingUtil;

import jakarta.annotation.PostConstruct;

@PropertySource(value="classpath:custom.properties")
@SpringBootApplication
public class Spring08ExampleApplication {

	//의존 객체 주입 받기
	@Autowired
	private WritingUtil util;
	
	//spring 프레임 워크가 준비 되고 난 이후에 호출할 메소드
	@PostConstruct
	public void testAop() {
		util.writeLetter();
		util.writeReport();
		util.writeDiary();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Spring08ExampleApplication.class, args);
	}

}

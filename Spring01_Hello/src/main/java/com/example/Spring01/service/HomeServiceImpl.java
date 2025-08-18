package com.example.Spring01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * 	스프링 프레임 워크가 HomeSerivceImpl 클래스가 존재하는 패키지(com.example.Spring01.service)를
 * component scan 을 하게 되면 적절한 어노테이션(@Component)의 붙어 있는
 *  이 클래스로 객체를 생성해서 해당 객체의 참조값을 spring bean container 에서 관리 하게 된다
 *  
 *   - 어디를 scan 하게 되나?
 *   @SpringBootApplication 어노테이션이 붙어있는 클래스라 존재하는 패키지 혹은 하위 패키지를 모두 scan 한다.
 */

@Component
public class HomeServiceImpl implements HomeService{
	
	//의존 객체 주입 받기
	@Autowired Drill d;
	
	//생성자
	public HomeServiceImpl() {
		System.out.println("HomeServiceImpl 객체가 생성됨");
				
	}
	@Override
	public void hole(String name) {
		System.out.println(name+"에 구멍 뚫음");
		//주입 받은 의존객체를 이용해서 원하는 동작 하기
		d.hole();
	}
	
	@Override
	public void clean(String name) {
		System.out.println(name+"의 집을 청소함");
		
	}

	@Override
	public void wash(String name) {
		System.out.println(name+"의 걸레를 빨음");
		
	}
	

}

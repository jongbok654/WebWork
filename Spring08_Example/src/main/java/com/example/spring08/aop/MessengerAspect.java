package com.example.spring08.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.spring08.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j //보고
@Aspect
@Component
public class MessengerAspect {
	/*
	 * 1. 메소드의 return type 은 String 이고
	 * 2. com.example.spring08.util 패키지에 속해있는 모든 클래스(*) 중에서
	 * 3.get 으로 시작하는 메소드
	 * 4. 메소드의 매개변수는 비어있는 메소드
	 * 
	 * 위의 4가지 조건이 모두 만족하면 아래의 aspect 가 적용된다.
	 */
	@Around("execution(String com.example.spring08.aop.*.get*())")
	public Object checkReturn(ProceedingJoinPoint joinPoint) throws Throwable{
		//aspect 가 적용된 메소드를 실행하고 해당 메소드가 리ㅓㄴ하는 값을 변수에 담기
		Object obj=joinPoint.proceed();
		// 원래 type 으로 casting
		String returnValue = (String)obj;
		log.debug("원래 리턴 된 값: "+returnValue);
		//리턴값이 있는 메소드에 aspect 를 적용하면 반드시 해당 데이터를 리턴해서
		return "무슨 공부여 술먹자";//다른 값을 리턴해 줄 수도 있다
	}
	
	
	// .. 은 매개변수의 모형을 상관하지 않겠다. (갯수와 type 을 제한하지 않음)
	// spring 이 관리하는 bean 의 메소드 중에서 리턴 type 이 void 이고 send 로 시작하는 모든 메소드에 적용한다.
	@Around("execution(void send*(..))") //@Before + @After 기능을 다 하는 어노테이션
	public void checkGreeting(ProceedingJoinPoint joinPoint) throws Throwable {
		
		//메소드에 전달된 인자들 목록을 얻어내기
		Object[] args = joinPoint.getArgs();
		//반복문 돌면서 매개변수에 담긴 값들을 하나하나 참조하면서
		for(Object tmp:args) {
			//찾고 싶은 type 을 확인하다
			if(tmp instanceof String) {//만일 String type 이라면
				//찾았다면
				String msg=(String)tmp;
				log.debug("매개 변수에 전달된 값:"+msg);
				if(msg.contains("똥개")){
					log.error("똥개는 금지된 단어입니다.. 메소드 호출을 차단합니다");
					return; //여기서 리턴하면 아래의 joinPoint.proceed(); 가 호출이 안된다
				}
			}
			if(tmp instanceof MemberDto) {//만일 Member Dto type 이라면
					log.debug("매개변수에 전달된값:"+tmp);
					MemberDto dto=(MemberDto)tmp;
					dto.setName("개구라");
				}
		}
		//이 메소드르 호출하는 시점에 실제로 aspect 가 적용된 메소드가 수행한다.(호출하지 않으면 수령 만듦)
				joinPoint.proceed();
	}
	
}

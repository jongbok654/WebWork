package com.example.spring03;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 * 	1.application.properties 설정 파일을 읽어들여서 동작을 준비한다.
 * 
 * 	2. 현재  이 클래스가 존재하는 패키지를 포함해서 모든 하위패키지를 scan 에서 bean 으로 만들 것 들은 다 만들고
 * 		필요하다면 의존성 주입(DI)도 실행한다.
 * 	3. 이 프로젝트는 spring web 프로젝트이기 떄문에 내장 tomcat도 실행해서 서비스를 준비한다
 */

@SpringBootApplication
public class Spring03JspApplication {

	//Run as spring boot app 메뉴를 선택해서 실행하면 여기 실행됨
	public static void main(String[] args) {
		SpringApplication.run(Spring03JspApplication.class, args);
	}
	
	//크롬 부아저를 자동으로 열어 주는 메소드
	public void openChrome() {
		String url = "http://localhost:9000/";
		// 운영체제의 얻어와서 이름을 소문자로 
		String os = System.getProperty("os.name").toLowerCase();
		ProcessBuilder builder = null;
		try {
			if (os.contains("win")) {
				builder = new ProcessBuilder("cmd.exe", "/c", "start chrome " + url);
			} else if (os.contains("mac")) {
				builder = new ProcessBuilder("/usr/bin/open", "-a", "Google Chrome", url);
			} else {
				System.out.println("지원 하지 않는 운영체제 입니다.");
				return;
			}
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

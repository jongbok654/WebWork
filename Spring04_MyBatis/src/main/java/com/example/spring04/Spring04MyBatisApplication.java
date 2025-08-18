package com.example.spring04;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring04MyBatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring04MyBatisApplication.class, args);
		
		
		
		
	}
	//크롬 브라우저를 자동으로 열어 주는 메소드
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

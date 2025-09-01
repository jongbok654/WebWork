package com.example.spring09.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //테이블명을 지정하지 않으면 클래스명과 동일하게 테이블이 만들어진다.
public class Client {
	//고객번호
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long num;
	
	//고객의 이름
	@Column(nullable = false, length= 20)//null 허용되지 않음, 최대길이 20글자
	private String userName;
	
	//등록일
	@CreationTimestamp // 최초 저장되는 시점의 시간이 자동으로 들어 가도록
	private LocalDateTime createdAt;
	
	//수정일
	@UpdateTimestamp // 수정되는 시점의 시간이 자동으로 들어 가도록
	private LocalDateTime updatedAt;
	
	//생일
	@Column(nullable = true) //처음에는 비워두었다가 나중에 입력
	private LocalDate birthday;
}

package com.example.spring09.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name ="MEMBER_INFO")
public class Member {
	// num 이라는 필드에 대해서 2 개의 어노테이션 추가함
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer num;
	
	private String name;
	private String addr;
}

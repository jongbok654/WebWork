package com.example.spring09.entity;

import com.example.spring09.dto.MemberDto;

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
	
	//dto를 Entity 로 변경하는 메소드
	public static Member toEntity(MemberDto dto) {
		
		/*
		 * Member entity 의 num 이라는 필드에 값이 null  이어애지
		 * .save() 할 때 새로운 row 가 추가된다
		 * 
		 * 따라서 dto 에 num 이 만일 0 이면 0 을 넣는 것이 아니고 null 을 넣어주어야 .save() 가
		 * 우리가 의도한 바 대로 동작한다.
		 */
		
		return Member.builder()
				.num(dto.getNum() == 0 ? null : dto.getNum())
				.name(dto.getName())
				.addr(dto.getAddr())
				.build();
	}
}

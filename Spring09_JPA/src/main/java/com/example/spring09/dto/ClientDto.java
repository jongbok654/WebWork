package com.example.spring09.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.spring09.entity.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(name = "Client", description="고객 정보 DTO")
public class ClientDto {
	private Long num;
	
	@NotBlank(message="이름은 필수 입니다.")
	@Size(max=20, message="이름은 최대 20자 까지 가능합니다")
	private String userName;
	

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy년 MM월 dd일 HH:mm")
	private LocalDateTime createdAt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy년 MM월 dd일 HH:mm")
	private LocalDateTime updatedAt;
	
	/*
	 * @Past
	 * @PastOrPresent
	 * @Future
	 * @FutureORPresent
	 * 중에 하나로 검증할 수 있다
	 * 
	 * input type="date" 의 value 에 th:value="${birthday}" 를 출력할 때 형식을 맞춰 주어야한다.
	 * 사실 ClientDto 의 birthday 라는 필드는 LocalDate type 이기 때문에
	 * 출력할 때 어떤 형식으로 출력할지를 설정해야 웹브라우저가 해당 날짜를 UI 에 제대로 표시할 수 있다.
	 * 그래서 필요한 어노테이션이 @DataTimeFormat 이다
	 */
	@PastOrPresent(message = "생일은 미래일 수 없습니다")
	@DateTimeFormat(iso =DateTimeFormat.ISO.DATE)
	private LocalDate birthday;
	
	//static toDto() 메소드
	public static ClientDto toDto(Client client) {
		return ClientDto.builder()
				.num(client.getNum())
				.userName(client.getUserName())
				.createdAt(client.getCreatedAt())
				.updatedAt(client.getUpdatedAt())
				.birthday(client.getBirthday())
				.build();
	}
	//non static toEntitiy 메소드를 만들어 보세요
	   public Client toEntity() {
	        return Client.builder()
	                .num(this.num)
	                .userName(this.userName)
	                .createdAt(this.createdAt)   // save 시점에는 @CreationTimestamp가 자동 적용되므로 null이어도 됨
	                .updatedAt(this.updatedAt)   // update 시점에도 자동 적용
	                .birthday(this.birthday)
	                .build();
	    }

	   // 단순 출력용 생일 문자 형식 
	   public String getFormattedBirthday() {
		   String result = birthday != null ? birthday.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")) : null;
		   return result;
	   }
	   
}

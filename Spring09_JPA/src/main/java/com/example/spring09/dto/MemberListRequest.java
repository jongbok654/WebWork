package com.example.spring09.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberListRequest {
	//페이지 번호 (default 값1)
	private int pageNum=1;
	//검색조건
	private String condition;
	//검색 키워드
	private String keyword;
}

package com.example.spring09.service;

import java.util.List;

import com.example.spring09.dto.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberPageResponse {
	private List<MemberDto> list; //회원 목록
	private int startPageNum;
	private int endPageNum;	
	private int totlaPageCount;
	private int pageNum;
}

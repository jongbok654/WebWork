package com.example.spring08.service;

import com.example.spring08.dto.BoardDto;
import com.example.spring08.dto.BoardListResponse;

public interface BoardService {
	public BoardListResponse getBoardList(int pageNum,String keyword);
	public void createdContent(BoardDto dto);
	
	
}

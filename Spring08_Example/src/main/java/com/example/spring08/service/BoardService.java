package com.example.spring08.service;

import java.util.List;

import com.example.spring08.dto.BoardDto;
import com.example.spring08.dto.BoardListResponse;
import com.example.spring08.dto.CommentDto;

public interface BoardService {
	public BoardListResponse getBoardList(int pageNum,String keyword);
	public void createdContent(BoardDto dto);
	public BoardDto getdetail(int num);
	public List<CommentDto> getComments(int parentNum);
	
}

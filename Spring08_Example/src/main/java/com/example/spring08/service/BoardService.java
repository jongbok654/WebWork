package com.example.spring08.service;

import java.util.List;

import com.example.spring08.dto.BoardDto;
import com.example.spring08.dto.BoardListResponse;
import com.example.spring08.dto.CommentDto;

public interface BoardService {
	public BoardListResponse getBoardList(int pageNum,BoardDto dto);
	public void createdContent(BoardDto dto);
	public BoardDto getdetail(int num);
	public List<CommentDto> getComments(int parentNum);
	public void createComment(CommentDto dto); //댓글 저장
	public void updateComment(CommentDto dto);//댓글 수정
	public void deleteComment(int num); //댓글 삭제
}

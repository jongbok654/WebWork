package com.example.spring08.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.spring08.dto.CommentDto;
import com.example.spring08.repository.CommentDao;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
	
	private final CommentDao commentDao;
	
	
	@Override
	public List<CommentDto> getComments(int parentNum) {

		return commentDao.selectList(parentNum);
	}

	@Override
	public void createComment(CommentDto dto) {
		//저장할 댓글의 pk 를 미리 받아낸다
		int num=commentDao.getSequence();
		dto.setNum(num);
		//만일 원글의 댓글이면
		if(dto.getGroupNum() == 0) {
			dto.setGroupNum(num); //원글의 댓글은 자신의 글 번호가 댓글의 그룹번호이고
		}
		//댓글 작성자를 얻어내서 dto에 담는다
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(userName);
		//대댓글이면 이미 dto에 댓글의 그룹번호가 들어 있따
		commentDao.insert(dto);
	}

	@Override
	public void updateComment(CommentDto dto) {
		//글 작성자와 로그인된 userName 이 동일한지 비교해서 돌일하지 않으면 예외를 발생시킨다
				String writer = commentDao.getByNum(dto.getNum()).getWriter();
				String userName = SecurityContextHolder.getContext().getAuthentication().getName();
				if(!writer.equals(userName)) {
					throw new RuntimeException("님의 글은 수정할 수 없습니다!");
				}
				//글 삭제하기
				commentDao.update(dto);
		
	}
	@Override
	public void deleteComment(int num) {
		//글 작성자와 로그인된 userName 이 동일한지 비교해서 돌일하지 않으면 예외를 발생시킨다
		String writer = commentDao.getByNum(num).getWriter();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!writer.equals(userName)) {
			throw new RuntimeException("님의 글은 지울 수 없습니다!");
		}
		//글 삭제하기
		commentDao.delete(num);
	}

}

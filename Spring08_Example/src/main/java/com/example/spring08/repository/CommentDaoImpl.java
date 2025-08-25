package com.example.spring08.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring08.dto.CommentDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentDaoImpl implements CommentDao{
	private final SqlSession session;
	@Override
	public List<CommentDto> selectList(int parentNum) {
		
		return session.selectList("comment.selectList", parentNum );
	}

}

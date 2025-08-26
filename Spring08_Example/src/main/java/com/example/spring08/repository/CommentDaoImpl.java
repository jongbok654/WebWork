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
	@Override
	public int delete(int num) {

		return session.delete("comment.delete", num );
	}
	@Override
	public int update(CommentDto dto) {
		
		return session.update("comment.update", dto);
	}
	@Override
	public void insert(CommentDto dto) {
		session.insert("comment.insert", dto);
		
	}
	@Override
	public int getSequence() {
		
		return session.selectOne("comment.getSequence");
	}
	@Override
	public CommentDto getByNum(int num) {
		
		return session.selectOne("comment.getByNum", num);
	}

}

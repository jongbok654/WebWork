package com.example.spring04.repository;

import java.util.List;

import com.example.spring04.dto.BookDto;

public interface BookDao {
	public List<BookDto> selectAll();
	public BookDto getByNum(int num);
	public void insert(BookDto dto);
	public int update(BookDto dto);
	public int deleteByNum(int num);
}

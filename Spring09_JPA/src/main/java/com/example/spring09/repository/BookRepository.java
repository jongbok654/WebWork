package com.example.spring09.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.spring09.entity.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
	
	public List<Book> findAllByOrderByIdDesc();
	public List<Book> findAllByOrderByIdAsc();
	
	@Query("SELECT b FROM BOOK_INFO b ORDER BY b.id DESC")
	public List<Book> findAllQuery();
	
	@Query("SELECT id,name,author FROM BOOK_INFO  ORDER BY id DESC")
	public List<Book> findAllNativeQuery();
}

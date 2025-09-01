package com.example.spring09.dto;

import com.example.spring09.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {
	private long id;
	private String name;
	private String author;
	
	public static BookDto toDto(Book entity) {
		return BookDto.builder()
				.id(entity.getId())
				.name(entity.getName())
				.author(entity.getAuthor())
				.build();
	}
}

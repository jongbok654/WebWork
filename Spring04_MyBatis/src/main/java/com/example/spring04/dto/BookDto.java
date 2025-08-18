package com.example.spring04.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("BookDto")
@Getter
@Setter
public class BookDto {

	private int num;
	private String title;
	private String author;
	private String publisher;
}

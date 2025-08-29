package com.example.spring08.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GalleryViewResponse {
	private GalleryDto dto;
	private List<GalleryImageDto> images;
	private List<CommentDto> commentList;
}

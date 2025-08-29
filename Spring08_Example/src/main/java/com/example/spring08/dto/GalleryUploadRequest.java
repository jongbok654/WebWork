package com.example.spring08.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GalleryUploadRequest {
	private String title;
	private String content;
	//<input type="file" name="images" multiple>
	//images 라는 파라미터명으로 파일이 여러개 전송되니까 MultipartFile[] type 으로 선언한다.
	private MultipartFile[] images;
}

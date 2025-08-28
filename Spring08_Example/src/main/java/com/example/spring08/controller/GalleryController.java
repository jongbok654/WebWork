package com.example.spring08.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring08.dto.GalleryUploadRequest;
import com.example.spring08.service.GalleryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class GalleryController {
	
	//파일을 저장할 위치
	@Value("${file.location}")
	private String fileLocation; //파일을 저장할 위치
	
	private final GalleryService galleryService;
	
	@PostMapping("/gallery/save")
	public String gallerySave(GalleryUploadRequest uploadRequest) {
		//업로드된 파일의 모든 정보를 GalleryUploadRequest 의 images 라는 MultipartFile[] 겍체에 담아서
		//전달해 준다
		return "gallery/ssave";
	}
	
	@GetMapping("/gallery/new-form")
	public String newForm() {
		
		return "gallery/new-form";
	}
	
	
	@GetMapping("/gallery/list")
	public String galleryList(Model model) {
		model.addAttribute("list", galleryService.getGalleryList());
		
		return "gallery/list";
	}

}

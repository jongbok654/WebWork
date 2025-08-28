package com.example.spring08.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.spring08.dto.GalleryDto;
import com.example.spring08.dto.GalleryImageDto;
import com.example.spring08.repository.GalleryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService{
	
	//의존 객체를 생성자로 주입받기
	private final GalleryMapper galleryMapper;
	
	 @Value("${file.location}")
	    private String fileLocation;
	 
	 
	 @Override
	 public List<GalleryDto> getGalleryList() {
		
		return galleryMapper.getListWithImages();
	 }
	 @Override
	 public int upload(GalleryDto dto) {
		// TODO Auto-generated method stub
		return 0;
	 }
	 @Override
	 public List<GalleryDto> getListWithImages() {
		// TODO Auto-generated method stub
		return null;
	 }
	 @Override
	 public List<GalleryImageDto> getImageList(int galleryNum) {
		// TODO Auto-generated method stub
		return null;
	 }
	 @Override
	 public GalleryDto getData(int num) {
		// TODO Auto-generated method stub
		return null;
	 }
	
	
	

}

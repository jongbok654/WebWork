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
	
	private final GalleryMapper mapper;
	 @Value("${file.location}")
	    private String fileLocation;
	
	@Override
	public int upload(GalleryDto dto) {
		
		return mapper.insert(dto);
	}

	@Override
	public String galleryNew() {

		return null;
	}

	@Override
	public List<GalleryDto> getListWithImages() {

		return mapper.getListWithImages();
	}

	@Override
	public List<GalleryDto> getList() {

		return mapper.getList();
	}

	@Override
	public GalleryDto getData(int num) {

		return mapper.getData(num);
	}

	@Override
	public int update(GalleryDto dto) {

		return 0;
	}

	@Override
	public int delete(int num) {

		return 0;
	}

	@Override
	public int deleteImage(int imageNum) {

		return 0;
	}

	@Override
	public int addImage(GalleryImageDto imageDto) {

		return mapper.insertImage(GalleryImageDto imagedto);
	}

	@Override
	public List<GalleryImageDto> getImageList(int galleryNum) {
		
		return null;
	}

}

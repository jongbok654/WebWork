package com.example.spring08.service;

import java.util.List;

import com.example.spring08.dto.GalleryDto;
import com.example.spring08.dto.GalleryImageDto;

public interface GalleryService {
	
    public List<GalleryDto> getGalleryList();
	
	public int upload(GalleryDto dto);
	public List<GalleryDto> getListWithImages();
    public List<GalleryImageDto> getImageList(int galleryNum);
    public GalleryDto getData(int num);
  

}
	
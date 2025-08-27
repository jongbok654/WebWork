package com.example.spring08.service;

import java.util.List;

import com.example.spring08.dto.GalleryDto;
import com.example.spring08.dto.GalleryImageDto;

public interface GalleryService {
	public int upload(GalleryDto dto);
	public String galleryNew();
	public List<GalleryDto> getListWithImages();
    public List<GalleryDto> getList();
    public List<GalleryImageDto> getImageList(int galleryNum);
    public GalleryDto getData(int num);
    int update(GalleryDto dto);

    int delete(int num);
    int deleteImage(int imageNum);
    int addImage(GalleryImageDto imageDto);

}
	
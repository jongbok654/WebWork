package com.example.spring08.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.example.spring08.dto.GalleryDto;

import com.example.spring08.dto.GalleryImageDto;


@Mapper
public interface GalleryMapper {


	 
	/*  
	  @Select("""
	            SELECT num, title, writer, TO_CHAR(createdAt, 'YYYY-MM-DD HH24:MI:SS') AS createdAt
	            FROM gallery
	            ORDER BY num DESC
	  		""")
	  public List<GalleryDto> getList();
	  */
	  
	 
	  
	  
	  /*
	   * 메소드의 리턴 type 이 GalleryDto 이기 때문에
	   * SELECT 된 ROW 1 개의 정보가 자동으로 GalleryDto 객체에 담겨서 리턴된다.
	   * 단) SELECT 문의 컬럼명과 GalleryDto 의 필드명이 일치해야 자동으로 담긴다
	   * 메소드의 매개변수의 type 이 해당 SELECT 문의 parameterType 으로 설정된다 
	   */
	  @Select("""
	            SELECT g.num, title, writer, content, TO_CHAR(g.createdAt, 'YYYY-MM-DD HH24:MI:SS') AS createdAt
	        			,profileImage
	            FROM gallery g
	            JOIN users u ON writer = userName
	            WHERE g.num = #{num}
	            """)
		  public GalleryDto getData(int num);
	  
	  /*
	   * 메소드의 리턴 type 이 SELECT 문의 resultType 으로 설정되기 때문에
	   *  SELECT 된 ROW 의 num 이 리턴된다
	   */
	  @Select("SELECT board_seq.NEXTVAL AS num FROM dual")
	  public  int getSequence();
	  
	  @Insert("""
				INSERT INTO gallery_image (num, galleryNum, saveFileName)
		        VALUES (gallery_image_seq.NEXTVAL, #{galleryNum}, #{saveFileName})		
			""")
			public void insertImage(GalleryImageDto dto);
	  
	  @Insert("""
		        INSERT INTO gallery (num, title, writer, content)
		        VALUES (#{num}, #{title}, #{writer}, #{content})
		        """)
		   
	  public void insert(GalleryDto dto);
	  
	  //이미 GalleryMapper.xml에서 설정을 다 해놨기 떄문에 선언만 하면 됨. => 어노테이션 없이 만들기만 하면 됨
	  public List<GalleryDto> getListWithImages();
	  
	  /*
	   * parameterType 은 int
	   * SELECT 된 Row 가 여러개니까 return type 이 List 이고
	   * List 의 generic type 이 GalleryImageDto 이니까 resultType 은 GalleryImageDto 가 된다.
	   */
	  
	  @Select("""
	            SELECT num, saveFileName, TO_CHAR(createdAt, 'YYYY-MM-DD HH24:MI:SS') AS createdAt
	            FROM gallery_image
	            WHERE galleryNum = #{num}
	            ORDER BY num ASC
	            """)
	  public List<GalleryImageDto> getImageList(int num);
}

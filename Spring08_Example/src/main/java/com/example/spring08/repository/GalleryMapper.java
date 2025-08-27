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

	  @Select("""
		        SELECT num, title, writer,
		               TO_CHAR(createdAt,'YYYY-MM-DD HH24:MI:SS') AS createdAt
		        FROM gallery
		        ORDER BY num DESC
		        """)
	  public List<GalleryDto> getListWithImages();
	  @Insert("""
		        INSERT INTO gallery (num, title, writer, content)
		        VALUES (#{num}, #{title}, #{writer}, #{content})
		        """)
		    @SelectKey(statement = "SELECT gallery_seq.NEXTVAL FROM dual",
		               keyProperty = "num",
		               resultType = int.class,
		               before = true)
	  public int insert(GalleryDto dto);
	  
	  @Insert("""
		        INSERT INTO gallery_image (image_num, gallery_num, save_name)
		        VALUES (#{num}, #{galleryNum}, #{saveName})
		        """)
		    @SelectKey(statement = "SELECT gallery_image_seq.NEXTVAL FROM dual",
		               keyProperty = "num",
		               resultType = int.class,
		               before = true)
	  public void insertImage(GalleryImageDto imagedto);
	  
	  @Select("""
	            SELECT num, title, writer, TO_CHAR(createdAt, 'YYYY-MM-DD HH24:MI:SS') AS createdAt
	            FROM gallery
	            ORDER BY num DESC
	  		""")
	  public List<GalleryDto> getList();
	  
	  @Select("""
            SELECT g.num, title, writer, content, TO_CHAR(g.createdAt, 'YYYY-MM-DD HH24:MI:SS') AS createdAt
        			,profileImage
            FROM gallery g
            JOIN users u ON writer = userName
            WHERE g.num = #{num}
            """)
	  public GalleryDto getData(int num);
	  
	  @Select("""
	            SELECT num, saveFileName, TO_CHAR(createdAt, 'YYYY-MM-DD HH24:MI:SS') AS createdAt
	            FROM gallery_image
	            WHERE galleryNum = #{num}
	            ORDER BY num ASC
	            """)
	  public List<GalleryImageDto> getImageList(int galleryNum);
	  
	  @Select("SELECT board_seq.NEXTVAL AS num FROM dual")
	    int getSequence();
}

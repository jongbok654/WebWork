package test2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import test.util.DbcpBean;
import test2.dto.BookDto;

public class BookDao {

	
	 public BookDto getByNum(int num) {
	        BookDto dto = null;
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try {
	            conn = new DbcpBean().getConn();
	            String sql = """
	                SELECT title, author, publisher
	                FROM book
	                WHERE num=?
	            """;
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, num);
	            rs = pstmt.executeQuery();
	            if (rs.next()) {
	                dto = new BookDto();
	                dto.setNum(num);
	                dto.setTitle(rs.getString("title"));
	                dto.setAuthor(rs.getString("author"));
	                dto.setPublisher(rs.getString("publisher"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (pstmt != null) pstmt.close();
	                if (conn != null) conn.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return dto;
	    }

	    // 전체 책 목록을 List에 담아서 리턴하는 메소드
	    public List<BookDto> selectAll() {
	        List<BookDto> list = new ArrayList<>();
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try {
	            conn = new DbcpBean().getConn();
	            String sql = """
	                SELECT num, title, author, publisher
	                FROM book
	                ORDER BY num ASC
	            """;
	            pstmt = conn.prepareStatement(sql);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	            	
	            	int num = rs.getInt("num");
	            	String title = rs.getString("title");
	            	String author=rs.getString("author");
	            	String publisher = rs.getString("publisher");
	                BookDto dto = new BookDto();
	                dto.setNum(rs.getInt("num"));
	                dto.setTitle(rs.getString("title"));
	                dto.setAuthor(rs.getString("author"));
	                dto.setPublisher(rs.getString("publisher"));
	                list.add(dto);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (pstmt != null) pstmt.close();
	                if (conn != null) conn.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return list;
	    }

	    // 책 정보 수정
	    public boolean update(BookDto dto) {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        int rowCount = 0;
	        try {
	            conn = new DbcpBean().getConn();
	            String sql = """
	                UPDATE book
	                SET title=?, author=?, publisher=?
	                WHERE num=?
	            """;
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, dto.getTitle());
	            pstmt.setString(2, dto.getAuthor());
	            pstmt.setString(3, dto.getPublisher());
	            pstmt.setInt(4, dto.getNum());
	            rowCount = pstmt.executeUpdate();
	        } catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					// null인지 아닌지 체크 안하면 오류가남
					if (pstmt != null)
						pstmt.close();
					if (pstmt != null)
						conn.close();
				} catch (Exception e) {

				}
			}
			// 변화된 rowCount 값을 조사해서 작업의 성공 여부를 알아낼 수 있다.
			if (rowCount >= 0) {
				return true;
			} else if (rowCount <= 0) {
				return false;
			}
			return false;

		}

	    // 책 정보 삽입
	    public static boolean insert(BookDto dto) {
			Connection conn = null;
			PreparedStatement pstmt = null;

			int rowCount = 0;
			try {
				conn = new DbcpBean().getConn();
				String sql = """
						INSERT INTO book
							(num,title,author,publisher)
							VALUES(member_seq.NEXTVAL,?,?,?)
							""";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getAuthor());
				pstmt.setString(3, dto.getPublisher());
				rowCount = pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					// null인지 아닌지 체크 안하면 오류가남
					if (pstmt != null)
						pstmt.close();
					if (pstmt != null)
						conn.close();
				} catch (Exception e) {

				}
			}
			// 변화된 rowCount 값을 조사해서 작업의 성공 여부를 알아낼 수 있다.
			if (rowCount >= 0) {
				return true;
			} else if (rowCount <= 0) {
				return false;
			}
			return false;

		}


	    // 책 삭제
	    public boolean deleteByNum(int num) {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        int rowCount = 0;
	        try {
	            conn = new DbcpBean().getConn();
	            String sql = """
	                DELETE FROM book
	                WHERE num=?
	            """;
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, num);
	            rowCount = pstmt.executeUpdate();
	        } catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					// null인지 아닌지 체크 안하면 오류가남
					if (pstmt != null)
						pstmt.close();
					if (pstmt != null)
						conn.close();
				} catch (Exception e) {

				}
			}
			// 변화된 rowCount 값을 조사해서 작업의 성공 여부를 알아낼 수 있다.
			if (rowCount >= 0) {
				return true;
			} else if (rowCount <= 0) {
				return false;
			}
			return false;

		}

	}
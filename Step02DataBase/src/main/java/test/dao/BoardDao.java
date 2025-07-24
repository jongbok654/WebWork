package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dto.BoardDto;
import test.dto.MemberDto;
import test.util.DbcpBean;

public class BoardDao {
	//자신의 참조값을 저장할 static 필드
	private static BoardDao dao;
	//static 초기화 블럭에서 객체 생성해서 static 필드에 저장
	static {
		dao=new BoardDao();
	}
	

	//외부에서 객체 생성하지 못하도록 생성자의 접근 지정자를 private 로 설정
	private BoardDao() {}
	//참조값을 리턴해주는 static 메소드 제공
	public static BoardDao getInstance() {
		return dao;
	}
	
	
	
	
	
	//특정 글의 조회수를 증가 시키는 메소드
	public boolean addViewCount(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					UPDATE board
					SET viewCount = viewCount+1
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
		} else {
			return false;
		}
	}
	
	//검색 키워드에 부합하는 글의 갯수를 리턴하는 메소드
	public int getCountByKeyword(String keyword) {
		
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql문
			String sql = """
					SELECT MAX(ROWNUM) AS count
					FROM board
					WHERE title LIKE '%' || ? || '%' OR  content LIKE  '%' || ? || '%'

					""";

			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩
			pstmt.setString(1,keyword);
			pstmt.setString(2,keyword);
			// select 문 실행하고 결과를 ResultSet 으로 받아온다
			rs = pstmt.executeQuery();
			// 반복문 돌면서 ResultSet 에 담긴 데이터를 추출해서 리턴해줄 객체에 담는다
			if (rs.next()) {

				
				count=rs.getInt("count");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;
		
		
	}
public int getCount() {
		
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql문
			String sql = """
					SELECT MAX(ROWNUM) AS count
					FROM board
					

					""";

			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩

			// select 문 실행하고 결과를 ResultSet 으로 받아온다
			rs = pstmt.executeQuery();
			// 반복문 돌면서 ResultSet 에 담긴 데이터를 추출해서 리턴해줄 객체에 담는다
			if (rs.next()) {

				
				count=rs.getInt("count");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;
		
		
	}
	
	
	
	//특정 page 와 keyword 에  해당하는 row 만 select 해서 리턴하는 메소드
	//BoardDto 객체에 startRowNum 과 endRowNum 을 담아와서 select
	public List<BoardDto> selectPageByKeyword(BoardDto dto){
		List<BoardDto> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql문
			String sql = """
					SELECT *
					FROM
						(SELECT result1.*, ROWNUM AS rnum
						FROM 
							(SELECT num,writer,title,viewCount,createdAt
							FROM board
							WHERE title LIKE '%' || ? || '%' OR  content LIKE  '%' || ? || '%'
							ORDER BY num DESC) result1)
					WHERE rnum BETWEEN ? AND ?

					""";

			pstmt = conn.prepareStatement(sql);
			//? 에 값 바인딩
			pstmt.setString(1,dto.getKeyword());
			pstmt.setString(2,dto.getKeyword());
			pstmt.setInt(3, dto.getStartRowNum());
			pstmt.setInt(4, dto.getEndRowNum());
			// select 문 실행하고 결과를 ResultSet 으로 받아온다
			rs = pstmt.executeQuery();
			//반복문 돌면서 ResultSet 에 담긴 데이터를 추출해서 리턴해줄 객체에 담는다
			while (rs.next()) {
				BoardDto dto2=new BoardDto();
				dto2.setNum(rs.getInt("num"));
				dto2.setWriter(rs.getString("writer"));
				dto2.setTitle(rs.getString("title"));
				dto2.setViewCount(rs.getInt("viewCount"));
				dto2.setCreatedAt(rs.getString("createdAt"));
				
				list.add(dto2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
		
	}
	
	public List<BoardDto> selectPage(BoardDto dto){
		List<BoardDto> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql문
			String sql = """
					SELECT *
					FROM
						(SELECT result1.*, ROWNUM AS rnum
						FROM 
							(SELECT num,writer,title,viewCount,createdAt
							FROM board
							ORDER BY num DESC) result1)
					WHERE rnum BETWEEN ? AND ?

					""";

			pstmt = conn.prepareStatement(sql);
			//? 에 값 바인딩
			pstmt.setInt(1, dto.getStartRowNum());
			pstmt.setInt(2, dto.getEndRowNum());
			// select 문 실행하고 결과를 ResultSet 으로 받아온다
			rs = pstmt.executeQuery();
			//반복문 돌면서 ResultSet 에 담긴 데이터를 추출해서 리턴해줄 객체에 담는다
			while (rs.next()) {
				BoardDto dto2=new BoardDto();
				dto2.setNum(rs.getInt("num"));
				dto2.setWriter(rs.getString("writer"));
				dto2.setTitle(rs.getString("title"));
				dto2.setViewCount(rs.getInt("viewCount"));
				dto2.setCreatedAt(rs.getString("createdAt"));
				
				list.add(dto2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
		
	}

	
	//글 수정하는 메소드
	public boolean update(BoardDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					UPDATE board
					SET title=?,content=?
					WHERE num=?
										""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNum());

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
		} else{
			return false;
		}


	}
	public boolean deleteByNum(int num) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					DELETE
					FROM board
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
	
	
	//글 전체 목록을 리턴하는 메소드
	public List<BoardDto> selectAll(){
		List<BoardDto> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql문
			String sql = """
					SELECT num,writer,title,viewCount,createdAt
					FROM board
					ORDER BY num DESC

					""";

			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩

			// select 문 실행하고 결과를 ResultSet 으로 받아온다
			rs = pstmt.executeQuery();
			// 반복문 돌면서 ResultSet 에 담긴 데이터를 추출해서 리턴해줄 객체에 담는다
			while (rs.next()) {

				BoardDto dto=new BoardDto();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setViewCount(rs.getInt("viewCount"));
				dto.setCreatedAt(rs.getString("createdAt"));
				// dto에 값이 다 해당이 됐기 때문에 그냥 array인 dto 자체를 arraylist인 arr에 add하면 됨.
				list.add(dto);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	

	//글 하나의 정보를 리턴하는 메소드
	public BoardDto getByNum(int num) {
		BoardDto dto=null;
		//필요한 객체를 담을 지역변수를 미리 만든다 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql문
			String sql = """
				SELECT *
				FROM	
					(SELECT b.num, writer, title, content, viewCount, 
						TO_CHAR(b.createdAt, 'YY"년" MM"월" DD"일" HH24:MI') AS createdAt, 
						profileImage,
						LAG(b.num, 1, 0) OVER (ORDER BY b.num DESC) AS prevNum,
						LEAD(b.num, 1, 0) OVER (ORDER BY b.num DESC) AS nextNum
					FROM board b
					INNER JOIN users u ON b.writer = u.userName) 
				WHERE num=?
			""";
			pstmt = conn.prepareStatement(sql);
			//? 에 값 바인딩
			pstmt.setInt(1, num);
			// select 문 실행하고 결과를 ResultSet 으로 받아온다
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto=new BoardDto();
				dto.setNum(num);
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setViewCount(rs.getInt("viewCount"));
				dto.setCreatedAt(rs.getString("createdAt"));
				dto.setProfileImage(rs.getString("profileImage"));
				dto.setPrevNum(rs.getInt("prevNum"));
				dto.setNextNum(rs.getInt("nextNum"));
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return dto;
	}
	
	//글번호를 미리 select 해서 리턴해주는 메소드
	public int getSequence() {
		//글번호를 저장할 지역변수 미리 만들기
		int num=0;
		//필요한 객체를 담을 지역변수를 미리 만든다 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql문
			String sql = """
				SELECT board_seq.NEXTVAL AS num FROM DUAL
			""";
			pstmt = conn.prepareStatement(sql);
			//? 에 값 바인딩

			// select 문 실행하고 결과를 ResultSet 으로 받아온다
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				num=rs.getInt("num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return num;
	}
	
	//글정보를 저장하는 메소드
	public boolean insert(BoardDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		//변화된 row 의 갯수를 담을 변수 선언하고 0으로 초기화
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
				INSERT INTO board
				(num, writer, title, content)
				VALUES(?, ?, ?, ?)
			""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			// sql 문 실행하고 변화된(추가된, 수정된, 삭제된) row 의 갯수 리턴받기
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}

		//변화된 rowCount 값을 조사해서 작업의 성공 여부를 알아 낼수 있다.
		if (rowCount > 0) {
			return true; //작업 성공이라는 의미에서 true 리턴하기
		} else {
			return false; //작업 실패라는 의미에서 false 리턴하기
		}
	}
}









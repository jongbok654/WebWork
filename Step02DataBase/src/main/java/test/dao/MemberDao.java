package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dto.MemberDto;

import test.util.DbcpBean;

/*
 * Data Access Object 객체를 생성할 클래스 정의하기
 * 
 * DB에 insert,update,delete,select 작업을 하는 코드를 작성한다.
 */
public class MemberDao {

	// 회원번호를 이용해서 회원 1명의 정보를 리턴하는 메소드
	public MemberDto getByNum(int num) {

		MemberDto dto = null;
		// 회원 전체 몰록을 select 해서 List에 담아서 리턴하는 메소드

		// 필요한 객체를 담을 지역변수 미리 만들기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql문
			String sql = """
					SELECT name,addr
					FROM member
					WHERE num=?


					""";

			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩
			pstmt.setInt(1, num);// 번호는 매개변수에 있는 내용을 담으면 됨.

			// select 문 실행하고 결과를 ResultSet 으로 받아온다
			rs = pstmt.executeQuery();
			// 반복문 돌면서 ResultSet 에 담긴 데이터를 추출해서 리턴해줄 객체에 담는다
			while (rs.next()) {

				dto = new MemberDto();
				dto.setNum(num);
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
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
		return dto;

	}

	// 회원 전체 몰록을 select 해서 List에 담아서 리턴하는 메소드
	public List<MemberDto> selectAll() {
		// 필요한 객체를 담을 지역변수 미리 만들기

		List<MemberDto> arr = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql문
			String sql = """
					SELECT num,name,addr
					FROM member
					ORDER BY num ASC


					""";

			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩

			// select 문 실행하고 결과를 ResultSet 으로 받아온다
			rs = pstmt.executeQuery();
			// 반복문 돌면서 ResultSet 에 담긴 데이터를 추출해서 리턴해줄 객체에 담는다
			while (rs.next()) {

				int num = rs.getInt("num");
				String name = rs.getString("name");
				String addr = rs.getString("addr");
				MemberDto dto = new MemberDto();
				dto.setNum(num);
				dto.setName(name);
				dto.setAddr(addr);
				// dto에 값이 다 해당이 됐기 때문에 그냥 array인 dto 자체를 arraylist인 arr에 add하면 됨.
				arr.add(dto);

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

		return arr;
	}

	// 회원 한 명의 정보를 DB에서 삭제하고 성공여부를 리턴하는 메소드
	public boolean update(MemberDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					UPDATE member
					SET name=?,addr=?
					WHERE num=?
										""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
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

	public static boolean insert(MemberDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					INSERT INTO member
						(num,name,addr)
						VALUES(member_seq.NEXTVAL,?,?)
						""";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
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

	public boolean deleteByNum(int num) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					DELETE
					FROM member
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

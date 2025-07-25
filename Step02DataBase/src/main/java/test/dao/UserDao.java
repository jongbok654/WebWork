package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import test.dto.MemberDto;
import test.dto.UserDto;
import test.util.DbcpBean;

public class UserDao {
	//static 초기화 블록 (이클래스가 최초로 솨용될 때 한 1번!,상태되는 펴아이다.}
	private static UserDao dao;
	static {	
		dao=new UserDao();
		
	}
	
	//외부에서 UserDao 객체를 생성하지 못하도록 생성자를 private로 막는다.
	private UserDao() {}
	
	public static UserDao getInstance() {
		
		return dao;
	}
	
	// 이메일과 프로필을 수정하는 메소드
	public boolean updateEmailProfile(UserDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					UPDATE users
					SET email=?, profileImage=?,updatedAt=SYSDATE
					WHERE userName=?
										""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getProfileImage());
			pstmt.setString(3, dto.getUserName());

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

	// 이메일을 수정하는 메소드
	public boolean updateEmail(UserDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					UPDATE users
					SET email=?,updatedAt=SYSDATE
					WHERE userName=?
										""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getUserName());

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

	// 회원정보 추가
	public UserDto getByUserName(String userName) {
		UserDto dto = null;
		// 회원 전체 몰록을 select 해서 List에 담아서 리턴하는 메소드

		// 필요한 객체를 담을 지역변수 미리 만들기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			// 실행할 sql문
			String sql = """
					SELECT num,password,email,profileImage,role,updatedAt,createdAt
					FROM users
					WHERE userName=?


					""";

			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩
			pstmt.setString(1, userName);// 번호는 매개변수에 있는 내용을 담으면 됨.

			// select 문 실행하고 결과를 ResultSet 으로 받아온다
			rs = pstmt.executeQuery();
			// 만일 select 되는 row 가 존재한다면
			if (rs.next()) {

				dto = new UserDto();
				dto.setNum(rs.getLong("num"));
				dto.setUserName(userName);
				dto.setPassword(rs.getString("password"));
				dto.setEmail(rs.getString("email"));
				dto.setProfileImage(rs.getString("profileImage"));
				dto.setRole(rs.getString("role"));
				dto.setUpdatedAt(rs.getString("updatedAt"));
				dto.setCreatedAt(rs.getString("createdAt"));

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

	public boolean insert(UserDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 변화된 row 의 갯수를 담을 변수 선언하고 0으로 초기화
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
						INSERT INTO users
						(num, userName, password, email, updatedAt, createdAt)
						VALUES(users_seq.NEXTVAL, ?, ?, ?, SYSDATE, SYSDATE)
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			pstmt.setString(1, dto.getUserName());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getEmail());
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

		// 변화된 rowCount 값을 조사해서 작업의 성공 여부를 알아 낼수 있다.
		if (rowCount > 0) {
			return true; // 작업 성공이라는 의미에서 true 리턴하기
		} else {
			return false; // 작업 실패라는 의미에서 false 리턴하기
		}
	}

	public boolean updatePassword(UserDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					UPDATE users
					SET password=?,updatedAt=SYSDATE
					WHERE userName=?
										""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getUserName());

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

	

}

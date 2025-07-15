package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.dao.MemberDao;
import test.dto.MemberDto;

@WebServlet("/members") // 경로
public class memberServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		PrintWriter pw = response.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'>");
		pw.println("<title> </title>");
		pw.println("<style>");
		pw.println("table{width:300px;}");
		pw.println("thead{background-color:green;}");
		pw.println("</style>");
		pw.println("</head>");
		pw.println("<body>");
		// Connection 객체를 리턴해주는 메소드
				List<MemberDto> list = new MemberDao().selectAll(); // 정상 작동
				
		
		for(int i=0;i<5;i++) {
			pw.println("<p> hello"+i+" </p> ");
		}
		
		pw.println("<h3>회원 목록</h3>");
		pw.println("<table border='1' cellpadding='8' cellspacing='0'>");


		// thead 영역
		pw.println("<thead>");
		 pw.println("<tr>");
				pw.println("<th>번호</th>");
				pw.println("<th>이름</th>");
				pw.println("<th>주소</th>");
		 pw.println("</tr>");
		pw.println("</thead>");

		// tbody 영역 (예시 데이터 포함)
		pw.println("<tbody>");
		  pw.println("<tr>");
				pw.println("<td>1</td>");
				pw.println("<td>김구라</td>");
				pw.println("<td>서울</td>");
			 pw.println("</tr>");
			 pw.println("<tr>");
				pw.println("<td>2</td>");
				pw.println("<td>해골</td>");
				pw.println("<td>부산</td>");
		  pw.println("</tr>");
		pw.println("</tbody>");
		pw.println("<tbody>");
		for (MemberDto tmp : list) {
			pw.println("<tr>");
			pw.println("<td>"+tmp.getNum()+"</td>");
			pw.println("<td>"+tmp.getName()+"</td>");
			pw.println("<td>"+tmp.getAddr()+"</td>");
			pw.println("</tr>");	
			
		}

		pw.println("</table>");
		
		
		for (MemberDto dto : list) {
			pw.println("<li>" + dto.getNum() + " | " + dto.getName() + " | " + dto.getAddr() + "</li>");
		}
		pw.println("</body>");
		pw.println("</html>");
		pw.close();

	}

}

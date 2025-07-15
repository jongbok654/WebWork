package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/friends")
public class FriendsServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// DB 에서 읽어온 친구 목록이라고 가정하자
		List<String> names = new ArrayList();
		names.add("박종복1");
		names.add("박종복2");
		names.add("박종복3");

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		PrintWriter pw = response.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'>");
		pw.println("<title>친구목록입니다. </title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<ul>");
		pw.println("<li>"+names.get(0)+"</li>");
		pw.println("<li>"+names.get(1)+"</li>");
		pw.println("<li>"+names.get(2)+"</li>");
		pw.println("</ul>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();

	}
}

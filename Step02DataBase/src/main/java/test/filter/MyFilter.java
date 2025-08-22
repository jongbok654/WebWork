package test.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
	
/*
 * 요청을 가로채서 필터링 작업을 할 Filter 정의하기
 * 
 * 1.jakarta.servlet.Filer 인퍼에시르르 구현한다
 * 2.어떤 요청에 대해서 필터링을 할지 @WebFilter 어노테이션으로 한다
 */
@WebFilter("/member/*")
public class MyFilter implements FilteWr {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException,ServletException{
		
		System.out.println("filter 적용하기");
		
		//요청의 흐름 이어가기
		chain.doFilter(request, response);
	}

}

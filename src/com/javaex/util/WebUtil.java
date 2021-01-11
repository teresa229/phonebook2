package com.javaex.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {

	//필드 -> 생략
	
	//생성자: 디폴트 생성자 사용 -> 생략
	
	//메소드 g/s: 필드가 없으니 메소드도 없다 -> 생략
	
	//메소드 일반   -> alt + shift + o
	
	//포워드
	public static void forward(HttpServletRequest request,                         //3개 파라미터 정리
			                   HttpServletResponse response,
			                   String path) throws ServletException, IOException { //path는 작업자가 만드는 이름. 자료형 정리
		                                                                           //예외처리
		RequestDispatcher rd = request.getRequestDispatcher(path); //jsp파일 위치
		rd.forward(request, response);
		
	}
	
	//리다이렉트 : 경로 파일 위치..
	public static void redirect(HttpServletRequest request,
			                   HttpServletResponse response,
			                   String url) throws IOException { //예외처리
		response.sendRedirect(url);
	}
}

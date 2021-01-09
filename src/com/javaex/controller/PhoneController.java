package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")  //임의의 주소를 주었다. 파일이름과 대문자/소문자 동일해야 한다. 이름을 바꿀수도 있다.
public class PhoneController extends HttpServlet { //상속받았다:extends HttpServlet
    
	/* Get 방식 구현 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//언어 정리 : utf-8
		response.setContentType("text/html;charset=utf-8");
		
		//컨트롤러 테스트
		System.out.println("controller");
		
		//파라미터 action값을 읽어서 request에 들어있다.
		String action = request.getParameter("action");
		System.out.println(action);
		
		
		//action은 문자열
		/*
		if("list".equals(action)) { //action.equals("list") -> "list".equals(action) : null이 생기지 않게 위치 바꾼다.
			System.out.println("리스트 처리");
						
			//리스트 출력 처리
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.getPersonList(); //리스트를 준다.
			
			//System.out.println(personList.toString()); //테스트용
			
			//html -->엄청 복잡하다. --> jsp에서 짜는 것이 편하다.
			
			//데이터 전달
			request.setAttribute("pList", personList); // (정해준 이름/주소)
			
			/* forward * : jsp에 포워드 시킨다. 가져다 사용하기  
			//RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/list.jsp"); //jsp파일 위치 : 문자열로 알려준다.
			//rd.forward(request, response);
			
			WebUtil.forward(request, response, "./WEB-INF/list.jsp");

		*/		
		
		 if("wform".equals(action)) {
			System.out.println("등록 폼 처리");
					
			/* forward */
			//RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/writeForm.jsp");
			//rd.forward(request, response);
			
			WebUtil.forward(request, response, "./WEB-INF/writeForm.jsp");
			
		} else if("insert".equals(action)) {
			System.out.println("전화번호 저장");
			
			//파라미터 3개 값
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//personVo묶고
			PersonVo personVo = new PersonVo(name,hp,company);
			
			/* dao: Insert */      //--> 저장 
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personInsert(personVo);
			
			/* redirect */
			//response.sendRedirect("/phonebook2/pbc?action=list");
			
			WebUtil.redirect(request, response, "/phonebook2/pbc?action=list");
			
		} else if("update".equals(action)) {
			System.out.println("전화번호 수정");
			
			//파라미터 4개 값
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			int personId = Integer.parseInt(request.getParameter("id")); //Integer.parseInt String -> int 변환
			
			//personVo묶고
			PersonVo personVo = new PersonVo(personId, name, hp, company);
			
			/* dao: Update */
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personUpdate(personVo);
			
			/* redirect */
			//response.sendRedirect("/phonebook2/pbc?action=list");
			
			WebUtil.redirect(request, response, "/phonebook2/pbc?action=list");
			
		} else if("updateForm".equals(action)) {
			System.out.println("수정 폼 정리");
			
			int personId = Integer.parseInt(request.getParameter("id"));
			
			/* dao: getPerson */   //1명 조회
			PhoneDao phoneDao = new PhoneDao();
			PersonVo personVo = phoneDao.getPerson(personId);
		
			//데이터 전달
			request.setAttribute("updateF", personVo);
			
			/* forward */
			//RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/updateForm.jsp");
			//rd.forward(request, response);
			
			WebUtil.forward(request, response, "./WEB-INF/updateForm.jsp");
			
		} else if("delete".equals(action)) {
			System.out.println("전화번호 삭제");
			
			//1개 값
			int personId = Integer.parseInt(request.getParameter("id"));
			
			/* dao: Delete */
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personDelete(personId);
			
			/* redirect */
			//response.sendRedirect("/phonebook2/pbc?action=list");
			
			WebUtil.redirect(request, response, "/phonebook2/pbc?action=list");
			
		} else {  //주소가 틀렸을 경우 기본값으로 준다. list내용을 모두 옮겨 버렸다.
			
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.getPersonList(); //리스트를 준다.
			
			//System.out.println(personList.toString()); //테스트용
			
			//데이터 전달
			request.setAttribute("pList", personList); // (정해준 이름/주소)
						
			WebUtil.forward(request, response, "./WEB-INF/list.jsp");
		}
	}

	/* Post 방식 구현 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

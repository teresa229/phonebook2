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
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhoneController extends HttpServlet {
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
   		System.out.println("controller");
   		
   		/* 등록 관련 : 주소록....*/
   		/* 리스트 출력 관련 */
   		
   		
   		//파라미터 값을 불러오기
   		String action = request.getParameter("action"); //담아놓기
   		System.out.println(action);
   		
     /* 리스트 출력 관련 */
   		if("list".equals(action)) { //문자열 비교  : if(action="list")는 주소비교하는 것이다. 
   		                            //null이 생기지 않게 위치 바꾸기..(action.equals("list"))->("list".equals(action))
   			//리스트 출력 처리
   	   		PhoneDao phoneDao = new PhoneDao();
   	   		List<PersonVo> personList = phoneDao.getPersonList(); //?
   	   		
   	   		//html -->너무 복잡하다
   	   		
   	   		//데이터 전달
   	   		request.setAttribute("pList", personList); //(정해준 이름/주소)
   	   		
   	   		/* forward */
   	   		RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/list.jsp");
   	   		rd.forward(request, response);
   		
   	  /* wform 출력 관련 */
   		} else if("wform".equals(action)) {
   			System.out.println("등록 폼 처리");
   			 
   			/* forward */
   			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/writeForm.jsp");
   			rd.forward(request, response);

      /* insert 출력 관련 */
   		} else if("insert".equals(action)) {
   			System.out.println("전화번호 저장");
   			
   			// step 1: 파라미터 3개 값
   			String name = request.getParameter("name");
   			String hp = request.getParameter("hp");
   			String company = request.getParameter("company");
   			
   			// step 2: personVo로 묶고
   			PersonVo personVo = new PersonVo(name, hp, company);
   			
   			// step 3: insert --> new Dao 저장
   			PhoneDao phoneDao = new PhoneDao();
   			phoneDao.personInsert(personVo); //personVo로 묶어놓은 값을 불러온다.
   			
   			/* 리다이렉트 */
   			response.sendRedirect("/phonebook0/pbc?action=list");
   		
      /* update 출력 관련 */
   		} else if("update".equals(action)) {
   			System.out.println("전화번호 수정");
   			
   			// step 1: 파라미터 4개 정리
   			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			int personId = Integer.parseInt(request.getParameter("id")); //Integer.parseInt String -> int 변환
			
   			// step 2: vo로 묶기
			PersonVo personVo = new PersonVo(personId, name, hp, company);
   			
   			// step 3: update --> new Dao 저장
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personUpdate(personVo);
			
			response.sendRedirect("/phonebook0/pbc?action=list");
			
      /* updateForm 출력 관련 */
   		} else if("updateForm".equals(action)) {
   			System.out.println("수정 폼 정리");
   	   		
   			int personId = Integer.parseInt(request.getParameter("id"));
   			
   			// getPerson : dao 1명 조회
   			PhoneDao phoneDao = new PhoneDao();
   			PersonVo personVo = phoneDao.getPerson(personId);
   			
   			/* attribute: 데이터 전달 */
   			request.setAttribute("updateF", personVo);
   			   			
   			/* forward */
   			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/updateForm.jsp");
   			rd.forward(request, response);

       /* delete 출력 관련 */   			
   	   	}else if("delete".equals(action)) {
   	   		System.out.println("삭제");
   			
   	   		int personId = Integer.parseInt(request.getParameter("id"));
   	   		
   	   		// step 3: delete --> new Dao 저장 
   	   		PhoneDao phoneDao = new PhoneDao();
   	   		phoneDao.personDelete(personId);
   	   		
   	   		/*리다이렉트*/
   			response.sendRedirect("/phonebook0/pbc?action=list");
   		}
   	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

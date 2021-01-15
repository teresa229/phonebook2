<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<%-- 데이터를 꺼내온다.--%>
<%--  controller 포워드 시켰다. 떠내기만 하면 된다. dao에서 가져올 필요 없다. personList --%>
<%--

<%@ page import = "java.util.List" %>
<%@ page import = "com.javaex.vo.PersonVo" %>

<%
	List<PersonVo> personList = (List<PersonVo>)request.getAttribute("pList"); //get,set 오브젝트로 받아올수 없으므로 형변환을 해준다.
	
	//System.out.println("====list.jsp====");
	//System.out.println(personList); //println은 toString을 불러오게 약속이 되어있다.
%>

   dao에서 불러오는 것은 없다는 것 확인. 에티튜드가 해주었다
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호 리스트</h1>
	
	<p>
		입력한 정보 내역입니다.
	</p>
		<!-- for(int i=0; i<personList.size(); i++ -->
		<c:forEach items="${requestScope.pList}" var="personList">
		<table border = "1">
			<tr>
				<td>이름(name)</td>
				<td>${personList.name}</td>   <!-- personList.get(i).getName() -->
			</tr>
			<tr>
				<td>핸드폰(hp)</td>
				<td>${personList.hp}</td>     <!-- personList.get(i).getHp() -->
			</tr>
			<tr>
				<td>회사(company)</td>
				<td>${personList.company}</td>     <!-- personList.get(i).getCompany( -->
			</tr>
			<tr>
				<td><a href="/phonebook2/pbc?action=updateForm&id=${personList.personId}">수정</a></td>   <!-- personList.get(i).getPersonId() -->
				<td><a href="/phonebook2/pbc?action=delete&id=${personList.personId}">삭제</a></td>  <!-- personList.get(i).getPersonId() -->
			</tr>
		</table>
	</c:forEach>
		<br>
		
	<a href="/phonebook2/pbc?action=wform">추가번호 등록</a>

</body>
</html>
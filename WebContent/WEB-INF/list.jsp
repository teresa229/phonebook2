<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "com.javaex.vo.PersonVo" %>
    
<%-- 데이터를 꺼내온다.--%>
<%--  controller 포워드 시켰다. 떠내기만 하면 된다. dao에서 가져올 필요 없다. personList --%>

<%
	List<PersonVo> personList = (List<PersonVo>)request.getAttribute("pList"); //get,set 오브젝트로 받아올수 없으므로 형변환을 해준다.
	
	//System.out.println("====list.jsp====");
	//System.out.println(personList); //println은 toString을 불러오게 약속이 되어있다.
%>

<%-- dao에서 불러오는 것은 없다는 것 확인. 에티튜드가 해주었다--%>


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
	
	<%for(int i=0; i<personList.size(); i++) {//섞는다고 표현..html에~ %> 
		<table border = "1">
			<tr>
				<td>이름(name)</td>
				<td><%=personList.get(i).getName() %></td>
			</tr>
			<tr>
				<td>핸드폰(hp)</td>
				<td><%=personList.get(i).getHp() %></td>
			</tr>
			<tr>
				<td>회사(company)</td>
				<td><%=personList.get(i).getCompany() %></td>
			</tr>
			<tr>
				<td><a href="/phonebook2/pbc?action=updateForm&id=<%=personList.get(i).getPersonId()%>">수정</a></td>
				<td><a href="/phonebook2/pbc?action=delete&id=<%=personList.get(i).getPersonId()%>">삭제</a></td>
			</tr>
		
		</table>
		<br>
		
	<%} %>
	<a href="/phonebook2/pbc?action=wform">추가번호 등록</a>

</body>
</html>
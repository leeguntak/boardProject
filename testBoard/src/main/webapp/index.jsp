<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>board</title>
    <link rel="stylesheet" href="/testBoard/css/index.css">
</head>
<body>
   <header class="header">
       <div class="login group">
           <ul>
               <li><a href="/testBoard/member/loginForm">로그인 &ensp;</a></li>
               <li><a href="/testBoard/member/signUpForm">회원가입 &ensp;</a></li>
               <li><a href="#">회원정보수정 &ensp;</a></li>
               <li><a href="#">로그아웃 &ensp;</a></li>
               <input type="hidden" class="clear">
           </ul>
       </div>
   </header>

   <nav class="nav">
        <div>
            <a href="/testBoard/index">Home</a>
            <a href="/testBoard/board/boardList">게시판</a>
        </div>
   </nav>

   <section class="section">
       <div class="main_content">
       
	        <c:if test="${not empty display }">
	       		<jsp:include page="${display }" /> 
	        </c:if>
	        
			<c:if test="${empty display }">
				메인화면
				로그인중
				로그아웃
			</c:if>
       

        
       </div>
    </section>
<!--    <footer class="footer">footer</footer> -->
</body>
</html>

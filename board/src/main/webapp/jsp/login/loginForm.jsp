<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/board/css/member/login.css">

<c:if test="${not empty cookie.user_check}">
	<c:set value="checked" var="checked"/>
</c:if>


<div class="loginTable">
    <div class="container">
        <form id="login">
            <table>
                <tr>
                    <td>ID</td>
                    <td>: <input type="text" id="mem_id" name="mem_id" value="${cookie.user_check.value}" placeholder="아이디"></td>
                </tr>
                <tr>
                    <td>PW</td>
                    <td>: <input type="password" id="mem_pwd" name="mem_pwd" value="" placeholder="****"></td>
                </tr>
            </table>
            <div class="spanLoginCheck"></div>
        </form>
        
        <label class="font-weight-bold text-white"> 
			<input type="checkbox" id="remember_us" name="remember_userId" ${checked}> 아이디 기억하기
		</label>

        
        <div class="btnGroup">
            <input type="button" href="/board/member/signUpForm" value="회원가입">
            <input type="button" id="loginBtn" value="로그인" onclick="javascript:loginBtn()">
        </div>
    </div>
</div>
	<!-- 로그인성공여부 -->
    <input type="hidden" id="loginCheck" value="${loginCheck }">
    
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/board/js/member.js"></script>
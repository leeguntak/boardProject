<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" href="/testBoard/css/member/login.css">


<div class="loginTable">
    <div class="container">
        <form id="login" method="post" action="/board/member/login">
            <table>
                <tr>
                    <td>ID</td>
                    <td>: <input type="text" id="mem_id" name="mem_id" value=""></td>
                </tr>
                <tr>
                    <td>PW</td>
                    <td>: <input type="password" id="mem_pwd" name="mem_pwd" value=""></td>
                </tr>
            </table>
        </form>
        <div class="btnGroup">
            <input type="button" href="/board/member/signUpForm" value="회원가입">
            <input type="button" id="loginBtn" value="로그인" onclick="javascript:loginBtn()">
        </div>
    </div>
</div>
	<!-- 로그인성공여부 -->
    <input type="hidden" id="loginCheck" value="${loginCheck }">
    
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/member.js"></script>
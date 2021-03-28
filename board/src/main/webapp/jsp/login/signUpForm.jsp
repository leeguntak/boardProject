<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" href="/board/css/member/signUpForm.css">


<div class="signUpContainer">
    <form id="signUp" method="post" action="/board/member/signUp">
    <h3>회원가입</h3>
        <table>
            <tr>
                <td>아이디</td>
                <td>
                    <input type="text" id="mem_id" name="mem_id">
                    <input type="button" value="중복확인"><br>
                    <div id="idDiv"></div>
                    <!--  아이디 중복체크 여부 -->
                     <input type="hidden" id="checkId" value="">
                </td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td>
                	<input type="password" id="mem_pwd" name="mem_pwd">
                    <div id="pwdDiv"></div>
                </td>
                
            </tr>
            <tr>
                <td>비밀번호 확인</td>
                <td>
                    <input type="password" id="pwdCheck">
                    <div id="pwdCheckDiv"></div>
                </td>
            </tr>
            <tr>
                <td>이름</td>
                <td><input type="text" id="mem_name" name="mem_name"></td>
            </tr>
            <tr>
                <td>핸드폰</td>
                <td class="tel">
                    <input type="text" id="mem_tel1" name="mem_tel1"> - 
                    <input type="text" id="mem_tel2" name="mem_tel2"> - 
                    <input type="text" id="mem_tel3" name="mem_tel3">
                </td>
            </tr>
            <tr class="email">
                <td>이메일</td>
                <td>
                    <input type="text" id="mem_email1" name="mem_email1"> @
                    <input type="text" id="mem_email2" name="mem_email2" placeholder="직접입력">
                    <datalist id="email2">
						<option value="gmail.com">
						<option value="naver.com">
						<option value="hanmail.net">
					</datalist>
                    <input type="button" value="이메일 인증">
                    <input type="hidden" id="checkEmail" value=""><!-- 이메일 인증했는지 체크 -->
                    <div id="emailDiv"></div>
                </td>
            </tr>
            <tr>
                <td>주소</td>
                <td>
                <input type="text" id="mem_postcode" name="mem_postcode" placeholder="우편번호">
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br><br>
				<input type="text" id="mem_address" name="mem_address" placeholder="주소"><br><br>
				<input type="text" id="mem_detailAddress" name="mem_detailAddress" placeholder="상세주소">
				<input type="text" id="mem_extraAddress" name="mem_extraAddress" placeholder="참고항목">
				</td>
            </tr>
        </table>
        <br><br>
        <div class="btnGroup">
            <input type="button" value="돌아가기">
            <input type="button" id="signUpBtn" value="회원가입" >
        </div>    
    </form>
    
    <!-- 회원가입 성공 여부용 -->
    <input type="hidden" id="signUpCheck" value="${signUpCheck }">
</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/member.js"></script>

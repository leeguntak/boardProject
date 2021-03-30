<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/board/css/board/boardList.css">

<div class="boardListDiv">
	<div class="btnGroup">
		<a href="/board/board/boardWriteForm" class="classname">글쓰기</a>
		<select class="selectPrint" id="selectPrint">
		  <option value="10" selected>10개</option>
		  <option value="50">50개</option>
		  <option value="100">100개</option>
		</select>
		
		<form method="get" action="/board/excel/excelDownload">
				<!-- 게시판 글 리스트의 디폴트는 10개 -->
				<input type="hidden" id="viewNum" name="viewNum" value="${viewNum }"><!-- 선택된 selectPrint의 value가 담긴다-->
				<input type="hidden" id="pg" name="pg" value="${pg }"><!-- 디폴트는 1이고 보고있는 페이지의 숫자가 담긴다. -->
			<button type="submit">Excel</button>
		</form>
	</div>
	<div class="table">
	    <table id="boardListTable" >
	        <tr>
	            <th width="100px">글번호</th>
	            <th width="500px">제목</th>
	            <th width="100px">작성자</th>
	            <th width="100px">조회수</th>
	            <th width="100px">작성일</th>
	        </tr>
	    </table>

<!-- 여기에 jstl사용해서 td생성 
값은 에이작스르 통해 제이슨으로 가져오기   책551-->

	<!-- 페이징  -->
	    <br>
	    <div id="boardPagingDiv" class="paging" align="center"></div>
	    
	<!-- 검색 -->
	    <br><br>
	    <form id="boardSearchForm">
	    	<input type="hidden" id="searchPg" name="pg" value="1">
	    	
	    	<div style="text-align: center;">
				<select id="searchType" name="searchType" style="width: 100px;">
					<option value="subject" selected>제목</option>
					<option value="id">아이디</option>
				</select>
		
				<input type="search" id="keyword" name="keyword">
				<input type="button" value="검 색" id="boardSearchBtn">
			</div>
	    </form>
	</div>
</div>


<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/board/js/boardList.js"></script>



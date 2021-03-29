<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="/board/css/board/boardList.css">

<input type="hidden" id="pg" value="${pg }">
<div class="boardListDiv">
	<div class="btnGroup">
		<a href="/board/board/boardWriteForm" class="classname">글쓰기</a>
		<!-- 디폴트 10개 -->
		<input type="hidden" id="viewNumHidden" name="viewNumHidden" value="10">
		<input type="hidden" id="viewNum" name="viewNum" value="${viewNum }"><!-- 선택된 selectPrint-->
			<select class="selectPrint" id="selectPrint">
			  <option value="10" selected>10개</option>
			  <option value="50">50개</option>
			  <option value="100">100개</option>
			</select>
		<form method="get" action="/board/excel/excelDownload">
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



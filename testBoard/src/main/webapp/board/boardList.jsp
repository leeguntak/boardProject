<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        table {
            border-bottom: 1px solid black;
            border-collapse: collapse;
            text-align: center;
        }
        th, td {
            padding: 5px;
            border-bottom: 1px solid black;
        }
    </style>
</head>

<body>
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
    	<input type="hidden" name="pg" value="1">
    	
    	<div style="text-align: center;">
			<select name="searchType" style="width: 100px;">
				<option value="subject" selected>제목</option>
				<option value="id">아이디</option>
			</select>
	
			<input type="search" name="keyword" id="keyword">
			<input type="button" value="검 색" id="boardSearchBtn">
		</div>
    </form>
    
</body>
</html>













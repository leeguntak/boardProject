<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<form name="boardViewForm">
<input type="hidden" name="seq" id="seq" value="${seq }">
<input type="hidden" name="pg" id="pg" value="${pg }">

<div class="main">
	<table border="1" width="500" cellpadding="3" cellspacing="0" frame="hsides" rules="rows">
		<tr>
			<td colspan="3"><h3><span id="subjectSpan"></span></h3></td>
		</tr>

		<tr>
			<td width="150">글번호 : <span id="seqSpan"></span></td>
			<td width="190">작성자 : <span id="idSpan"></span></td>
			<td width="150">조회수 : <span id="hitSpan"></span></td>
		</tr>

		<tr>
			<td colspan="3" height="350" valign="top" 
			style="white-space: pre-wrap; word-break: break-all;"><span id="contentSpan"></span>
			</td>
		</tr>
		
		<tr>
		<td>첨부파일: </td>
        <td colspan="2"> <a class="fileDownload"><span id="fileNamespan"/></a> </td>
   		</tr>
	</table>
	<div class="button">
	<br>
		<input type="button" value="목록" onclick="location.href='../board/boardList?pg=${pg }'">
		
		<span id="boardViewSpan">
			<input type="button" value="글수정" onclick="mode(1)">
			<input type="button" value="글삭제" id="boardDelete">
		</span>
		
		<input type="button" value="답글" onclick="mode(3)">
	</div>
</div>
</form>
    
    

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/board/js/boardView.js"></script>
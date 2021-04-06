<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="indexListAjax"> 
	<c:forEach items="${data.list }" var="item" varStatus="status">
		<div class="wrap"> 
			<div>${item.seq }</div> 
			<div>${item.subject }</div> 
			<div>null</div>
			<div>${item.hit }</div>
			<div>${item.logtime }</div>
		</div> 
	</c:forEach> 
</div>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
$(document).ready(function(){
	alert("1");
});
</script>
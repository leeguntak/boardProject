
//처음 들어가자 마자 페이지 출력
$(document).ready(function(){
	boardListPrint();
});

//10개, 50개, 100개보기 클릭
$('#selectPrint').change(function(){
	var viewNum = $(this).val();
	console.log("출력개수:"+viewNum);
	$('#viewNum').val(viewNum);
	
	boardListPrint();
});

function boardListPrint(){
	$.ajax({
		type:'get',
		url:'/board/board/getBoardList',
		data: {'pg': $('#pg').val(),
			   'viewNum': $('#viewNum').val()},
		dataType:'json',
		success: function(data){
			//alert(JSON.stringify(data.list));
			//alert(JSON.stringify(data.boardPaging));
			$('#boardListTable tr:gt(0)').remove();
			$.each(data.list, function(index, items){
				
				var html ="";
				
				for(i in data.list){
					html += "<tr>";
					html += "<td>"+data.list[i].seq+"</td>";
					html += "<td><a href:'#' id:'subjectA'>"+data.list[i].subject+"</a></td>";
					html += "<td>"+null+"</td>";
					html += "<td>"+data.list[i].hit+"</td>";
					html += "<td>"+data.list[i].logtime+"</td>";
					html += "</tr>";
				}
				$('#boardListTable tr:gt(0)').empty();
				$('#boardListTable').append(html);
				
				$('#boardListTable').on('click', '#subjectA', function(){
					alert($(this).prop('tagName'));
					alert("seq는 "+$(this).parent().prev().text());
					
					let seq = $(this).parent().prev().text();
					let pg = data.pg;
					location.href = '/board/board/boardView?seq='+seq+"&pg="+pg;
				});
				
				//페이징 처리
				$('#boardPagingDiv').html(data.boardPaging.pagingHTML);
				
			});//each
		},
		error: function(err){
			console.log(err);
		}
	});
}

//페이지 번호 클릭
function boardPaging(pg){
	var keyword = document.getElementById("keyword").value;
	if(keyword == ""){
		location.href = '/board/board/boardList?pg='+pg+'&viewNum='+$('#viewNum').val();
	}else{
		$('#boardSearchBtn').trigger('click', 'research');
	}
}

//============================================


//검색
$('#boardSearchBtn').click(function(event, str){
	if(str != 'research'){
	      $('input[name=searchPg]').val(1);
	   }
	if($('#keyword').val() == ''){
		alert("검색어를 입력하세요");
	}else{
		$.ajax({
			type: 'get',
			url: '/board/board/getBoardListSearch',
			data: {'pg': $('#pg').val(),
	               'searchType':$('#searchType').val(),
	               'keyword':$('#keyword').val(),
	               'viewNum': $('#viewNum').val()},
			dataType: 'json',
			success: function(data){
				alert(JSON.stringify(data));
				
				$.each(data.list, function(index, items){
					//페이징 처리
					$('#boardPagingDiv').html(data.boardPaging.pagingHTML);
				});//each
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});




























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
		type:'post',
		url:'/board/board/getBoardList',
		data: {'pg': $('#pg').val(),
			   'viewNum': $('#viewNum').val()},
		dataType:'json',
		success: function(data){
			
			$('#boardListTable tr:gt(0)').remove();
			$.each(data.list, function(index, items){
				$('<tr/>').append(
						$('<td/>',{align: 'center',
								   text: items.seq})).append(
						$('<td/>',{}).append($('<a/>',{href: '#',
											   text: items.subject,id: 'subjectA',}))).append(
						$('<td/>',{align: 'center',
								   text: items.id})).append(
						$('<td/>',{align: 'center',
								   text: items.hit})).append(
						$('<td/>',{align: 'center',
								   text: items.logtime})).appendTo(
						$('#boardListTable'));
				
				$('#boardListTable').on('click', '#subjectA', function(){
					//alert($(this).prop('tagName'));
					//alert("seq는 "+$(this).parent().prev().text());
					
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



























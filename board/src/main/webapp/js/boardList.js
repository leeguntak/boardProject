//목록
$(document).ready(function(){
	$.ajax({
		type:'post',
		url:'/board/board/getBoardList',
		data: {'pg': $('#pg').val()},
		dataType:'json',
		success: function(data){
			
			$.each(data.list, function(index, items){
				$('<tr/>').append($('<td/>',{
					align: 'center',
					text: items.seq
				})).append($('<td/>',{
					}).append($('<a/>',{
						href: '#',
						text: items.subject,
						id: 'subjectA',
					}))
				).append($('<td/>',{
					align: 'center',
					text: items.id
				})).append($('<td/>',{
					align: 'center',
					text: items.hit
				})).append($('<td/>',{
					align: 'center',
					text: items.logtime
				})).appendTo($('#boardListTable'));
				
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
});

//페이지 번호 클릭
function boardPaging(pg){
	var keyword = document.getElementById("keyword").value;
	if(keyword == ""){
		location.href = 'boardList?pg='+pg;
	}else{
		$('input[name=pg]').val(pg);
		$('#boardSearchBtn').trigger('click', 'research');
	}
}

//검색
$('#boardSearchBtn').click(function(){
	if($('#keyword').val() == ''){
		alert("검색어를 입력하세요");
	}else{
		$.ajax({
			type: 'post',
			url: '/board/board/getBoardListSearch',
			data: $('#boardSearchForm').serialize(),
			dataType: 'json',
			success: function(data){
				//alert(JSON.stringify(data));
				$('#boardListTable tr:gt(0)').remove();
				
				$.each(data.list, function(index, items){
					$('<tr/>').append($('<td/>',{
						align: 'center',
						text: items.seq
					})).append($('<td/>',{
						}).append($('<a/>',{
							href: '#',
							text: items.subject,
							id: 'subjectA',
						}))
					).append($('<td/>',{
						align: 'center',
						text: items.id
					})).append($('<td/>',{
						align: 'center',
						text: items.hit
					})).append($('<td/>',{
						align: 'center',
						text: items.logtime
					})).appendTo($('#boardListTable'));
					
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
});




























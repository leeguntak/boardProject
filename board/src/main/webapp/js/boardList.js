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
				
			});//each
		},
		error: function(err){
			console.log(err);
		}
	});
});
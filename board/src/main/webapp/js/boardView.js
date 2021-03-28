$(document).ready(function(){
	$.ajax({
		type: 'post',
		url: '/board/board/getBoard',
		data: 'seq='+$('#seq').val(),
		dataType : 'json',
		success : function(data){
			//alert(JSON.stringify(data));
			
			$('#subjectSpan').text(data.boardTableDTO.subject);
			$('#seqSpan').text(data.boardTableDTO.seq);
			$('#idSpan').text(data.boardTableDTO.id);
			$('#hitSpan').text(data.boardTableDTO.hit);
			$('#contentSpan').text(data.boardTableDTO.content);
			$('#fileNamespan').text(data.boardTableDTO.file1);
			
		},
		error: function(err){
			console.log(err);
		}
	});
});

//파일 다운로드
//$('#filrDownload').click(function(){
//	let fileName = $('#fileDownload').val();
//	window.location = "/board/board/fileDownload?fileName="+"fileName";
////	$.ajax({
////		type: 'post',
////		url: '/board/board/fileDownload',
////		data: {'fileName': $('#fileDownload').val()},
////		success:function(){
////			window.location = 
////		},
////		error: function(){
////			alert("오류가 발생했습니다.");
////		}
////	});
//});

$(document).ready(function(){
	$.ajax({
		type: 'post',
		url: '/board/board/getBoard',
		data: 'seq='+$('#seq').val(),
		dataType : 'json',
		success : function(data){
			alert(JSON.stringify(data));
			
			$('#subjectSpan').text(data.boardTableDTO.subject);
			$('#seqSpan').text(data.boardTableDTO.seq);
			$('#idSpan').text(data.boardTableDTO.id);
			$('#hitSpan').text(data.boardTableDTO.hit);
			$('#contentSpan').text(data.boardTableDTO.content);
			
		},
		error: function(err){
			console.log(err);
		}
	});
});

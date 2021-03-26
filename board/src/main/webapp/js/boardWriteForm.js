$('#boardWriteBtn').click(function(){
	$('#subjectDiv').empty();
	$('#contentDiv').empty();
	
	if($('#subject').val()==''){
		$('#subjectDiv').text('제목을 입력하세요');
		$('#subjectDiv').css('color','red');
		$('#subjectDiv').css('font-size','8pt');
		$('#subjectDiv').css('font-weight','bold');
	}else if($('#content').val()==''){
		$('#contentDiv').text('내용을 입력하세요');
		$('#contentDiv').css('color','red');
		$('#contentDiv').css('font-size','8pt');
		$('#contentDiv').css('font-weight','bold');
	}else{
	
		$.ajax({
			type: 'post',
			url: '/board/board/boardWrite',
			data: {'subject': $('#subject').val(),
				   'content': $('#content').val()},
			success: function(){
				alert('글쓰기 완료');
				location.href='/board/board/boardList';
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});

/*
 	let formData = new FormData($('#writeForm')[0]);
		console.log(formData);
 
 $.ajax({
			type: 'post',
			enctype: 'multipart/form-data',
			processData: false, 
			contentType: false,
			url: '/board/board/boardWrite',
			data: formData,
			success: function(data){
				alert("글등록 완료");
				location.href = '/board/board/boardList';
			},
			error: function(err){
				console.log(err);
			}
		});
 
 
 */
 //회원가입폼
$('#signUpBtn').click(function(){
		$('#idDiv').empty();
		$('#pwdDiv').empty();
		$('#pwdCheckDiv').empty();
		$('#emailDiv').empty();
		
		if($('#mem_id').val()==''){
			$('#idDiv').text('아이디 입력')
					    .css('color','red')
					    .css('font-size','8pt')
					    .css('font-weight','bold');
		}else if($('#mem_pwd').val()==''){
			$('#pwdDiv').text('비밀번호를 입력')
						   .css('color','red')
						   .css('font-size','8pt')
						   .css('font-weight','bold');
		}else if($('#mem_pwd').val() != $('#pwdCheck').val()){
			$('#pwdCheckDiv').text('비밀번호가 동일하지 않습니다.')
							   .css('color','red')
							   .css('font-size','8pt')
							   .css('font-weight','bold');
		}
//		else if($('#checkEmail').val()==''){
//			$('#emailDiv').text('이메일 인증을 하세요')
//						   .css('color','red')
//						   .css('font-size','8pt')
//						   .css('font-weight','bold');
//		}
		else{
			$('#signUp').submit();
		}
});


//회원가입후
if($('#signUpCheck').val()=="1"){
	alert("회원가입을 축하합니다");
	location.href="/board/index.jsp";
}


//다음 주소찾기
function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("mem_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("mem_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('mem_postcode').value = data.zonecode;
            document.getElementById("mem_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("mem_detailAddress").focus();
        }
    }).open();
}


//로그인
function loginBtn(){
	
	if(document.getElementById("mem_id").value == ""){
		document.getElementById("nameDiv").css('border','1px solid red');
	}else if(document.getElementById("mem_pwd").value == ""){
		document.getElementById("pwdDiv").css('border','1px solid red');
	}else{
		$.ajax({
			type : 'post',
			url: '/board/member/login',
			data: {'mem_id': $('#mem_id').val(), 
				   'mem_pwd': $('#mem_pwd').val(),
				   'remember_userId': $('#remember_us').is(':checked')},
			dataType: 'text',
			success: function(data){
				if(data == 'success'){
					location.href = '/board/index.jsp';
					
				}else if(data == 'fail'){
					alert("정보가 일치하지 않습니다.");
				}
			},
			error: function(err){
				console.log(err);
			}
			
		});
	}
}













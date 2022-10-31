	let header = $("meta[name='_csrf_header']").attr("content");
	let token = $("meta[name='_csrf']").attr("content");
	

	/* 이름 유효성 검사 */
	function nameChk(){
		var name = $("#customerName").val();
		var nameMsg = document.querySelector("#nameMsg");
		
		if(!name){
			nameMsg.innerHTML = "이름을 입력해주세요.";
			$("#nameMsg").css("color","#ff7474");
		}else if(!isValidKorean(name)){
			nameMsg.innerHTML = "유효한 이름이 아닙니다.";
			$("#nameMsg").css("color","#ff7474");
			$("#customerName").val("");
		}else{
			nameMsg.innerHTML = "";
		}
		return;
	}
	
	/* 이메일 유효성 검사 & 중복검사 */
    function emailChk(){
    	var email = $("#customerEmail").val();
    	var emailMsg = document.querySelector("#emailMsg");
    	
    	if(!email){
    		emailMsg.innerHTML = "이메일을 입력해주세요.";
			$("#emailMsg").css("color","#ff7474");
    	}else if(!isValidEmail(email)){
			emailMsg.innerHTML = "유효한 이메일이 아닙니다.";
			$("#emailMsg").css("color","#ff7474");
			$("#customerEmail").val("");
		}else{
			 $.ajax({
		            url:'emailChk', //Controller에서 요청 받을 주소
		            type:'POST', //POST 방식으로 전달
		            data:{email:email},
		            beforeSend: function (jqXHR) {
	                    jqXHR.setRequestHeader(header, token);
	                },
		            success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다 
		                if(cnt == 0){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디
		                	emailMsg.innerHTML = "";
							$("#emailDobuleChk").val("true");
		                } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
		                	emailMsg.innerHTML = "이미 가입된 이메일입니다";
							$("#emailMsg").css("color","#ff7474");
							$("#customerEmail").val("");
							$("#emailDobuleChk").val("false");
		                }
		            },
		            error:function(){
		                alert("에러입니다");
		            }
			});
    	}
    	return;
    }
	
	/* 비밀번호 유효성 검사 */
	function pwdChk(){
		var pwd = $("#customerPwd").val();
		var pwdMsg = document.querySelector("#pwdMsg");
		
		if(!pwd){
			pwdMsg.innerHTML = "비밀번호를 입력해주세요(숫자, 영문을 포함한 8~16자)";
			$("#pwdMsg").css("color","#ff7474");
		}else if(!isValidPwd(pwd)){
			pwdMsg.innerHTML = "숫자, 영문을 포함한 8~16자로 입력해주세요.";
			$("#pwdMsg").css("color","#ff7474");
			$("#customerPwd").val("");
		}else{
			pwdMsg.innerHTML = "";
		}
		
		var pwdDoubleMsg = document.querySelector("#pwdDoubleMsg");
		var pwdChk = $("#customerPwdChk").val();
		
		if(pwdChk){
			if((pwd != pwdChk) && pwd){
				pwdDoubleMsg.innerHTML = "비밀번호가 일치하지 않습니다";
				$("#pwdDoubleMsg").css("color","#ff7474");
				$("#customerPwdChk").val("");
			}
		}
		return;
	}
	
	/* 비밀번호 일치 검사 */
	function pwdDoubleChk(){
		var pwd = $("#customerPwd").val();
		var pwdChk = $("#customerPwdChk").val();
		var pwdDoubleMsg = document.querySelector("#pwdDoubleMsg");
		
		if(!pwdChk){
			pwdDoubleMsg.innerHTML = "비밀번호 확인을 해주세요.";
			$("#pwdDoubleMsg").css("color","#ff7474");
		}else if(pwd != pwdChk){
			pwdDoubleMsg.innerHTML = "비밀번호가 일치하지 않습니다";
			$("#pwdDoubleMsg").css("color","#ff7474");
			$("#customerPwdChk").val("");
		}else{
			pwdDoubleMsg.innerHTML = "";
		}
		return;
	}
	
	/* 연락처 유효성 검사 */
	function telChk(){
		var tel = $("#customerTel").val();
		var telMsg = document.querySelector("#telMsg");
		
		if(!tel){
			telMsg.innerHTML = "연락처를 입력해주세요.(예: 01012345678)";
			$("#telMsg").css("color","#ff7474");
		}else if(!isValidTel(tel)){
			telMsg.innerHTML = "올바른 연락처 형식을 입력해주세요.(예: 01012345678)";
			$("#telMsg").css("color","#ff7474");
		}else{
			telMsg.innerHTML = "";
		}
		return;
	}
	
	/* 연락처 인증번호 */
 	function telChkBtn(){
		var customerTel = $("#customerTel").val();
		var telChkMsg = document.querySelector("#telChkMsg");
		var telMsg = document.querySelector("#telMsg");
		
		if(!customerTel){
			telMsg.innerHTML = "연락처를 입력해주세요.(예: 01012345678)";
			$("#telMsg").css("color","#ff7474");
			event.preventDefault();
			return;
		}else if(!isValidTel(customerTel)){
			telMsg.innerHTML = "올바른 연락처 형식을 입력해주세요.(예: 01012345678)";
			$("#telMsg").css("color","#ff7474");
			$("#customerTel").val("");
			event.preventDefault();
			return;
		}else if(customerTel){
			$("#customerTel").attr("readonly",true);
			telChkMsg.innerHTML = "인증번호가 발송되었습니다.";
			$("#telChkMsg").css("color","green");
			event.preventDefault();
		}
		
		$.ajax({
			url:"/check/sendSMS",
			type:"GET",
			data:{customerTel:customerTel},
			success:function(res){
				$("#customerTelChk").blur(function(){
					if(res==$("#customerTelChk").val()){
						telChkMsg.innerHTML = "인증이 완료되었습니다.";
						$("#telChkMsg").css("color","green");
						event.preventDefault();
					}else{
						telChkMsg.innerHTML = "인증번호가 올바르지 않습니다.";
						$("#telChkMsg").css("color","#ff7474");
						$("#customerTelChk").val("");
						event.preventDefault();
					}
				});
			}
		});
		return;
	} 

	
	/* 약관동의 & 회원가입 성공 */
	function sendIt(){
		var f = document.signUpForm;
		
 		var chk = $("#flexCheckDefault").is(":checked");
		var chkMsg = document.querySelector("#chkMsg");
		
		if(!chk){
			chkMsg.innerHTML = "이용약관에 동의해주세요.";
			$("#chkMsg").css("color","#ff7474");
			event.preventDefault();
			return;
		}
		
		if(chk){
			chkMsg.innerHTML = "";
			event.preventDefault();
		}
		
		var name = $("#customerName").val();
		var email = $("#customerEmail").val();
		var pwd = $("#customerPwd").val();
		var pwdChk = $("#customerPwdChk").val();
		var tel = $("#customerTel").val();
		//var telChk = $("#customerTelChk").val();
		

//		if(!name || !email || !pwd || !pwdChk || !tel || !telChk){
		if(!name || !email || !pwd || !pwdChk || !tel){
			alert("내용을 입력해주세요");
			event.preventDefault();
			return;

		} 
		
		alert(name + "님 반갑습니다.\n회원가입해주셔서 감사합니다.");
		event.preventDefault();
		
		f.submit();
	}
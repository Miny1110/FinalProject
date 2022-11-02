    function emailChk(){
    	var email = $("#customerEmail").val();
    	var emailMsg = document.querySelector("#emailMsg");
    		
    	if(!email){
    		emailMsg.innerHTML = "이메일을 입력해주세요.";
			$("#emailMsg").css("color","#ff7474");
    	}else{
    		emailMsg.innerHTML = "";
    	}
    	return;
    }
    
    /* 비밀번호 널값 검사 */
    function pwdChk(){
    	var pwd = $("#customerPwd").val();
		var pwdMsg = document.querySelector("#pwdMsg");
		
		if(!pwd){
			pwdMsg.innerHTML = "비밀번호를 입력해주세요.";
			$("#pwdMsg").css("color","#ff7474");
		}else{
			pwdMsg.innerHTML = "";
		}
		return;
    }
    
    /* 이메일 기억하기 */
    /* 로그인 성공 */
    function sendIt(){
    	var f = document.loginForm;
    	
    	var email = $("#customerEmail").val();
    	var pwd = $("#customerPwd").val();
    	
    	if(!email || !pwd){
    		alert("내용을 입력해주세요");
    		event.preventDefault();
			return;
    	}
    	
    	var rememberEmail = $("#rememberEmail").is(":checked");	// 체크박스가 체크되었는지를 담아준다. ( true/false 로 담긴다.)
    	
    	if(rememberEmail){			// 아이디, 비밀번호 저장 체크박스가 체크 된 경우 (true)
    	    setCookie("cookie", email, 1);	// 쿠키에 저장하는 이벤트를 호출한다. Cookie_mail 이름으로 id가 7일동안 저장
    	}else{				// 체크가 해제 된 경우 (false)
    	    deleteCookie("cookie");	// 쿠키 정보를 지우는 이벤트를 호출한다.
    	}

    	f.submit();
    }
    
    /* 쿠키값 저장 */
    function setCookie(cookieName, value, exdays){
    	var exdate = new Date();
    	exdate.setDate(exdate.getDate() + exdays);	// 쿠키 저장 기간
    	var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
    	document.cookie = cookieName + "=" + cookieValue;
   	}
    
	//쿠키값 읽어오기
	$(document).ready(function(){
		var email = getCookie("cookie");
	
		if(email){
			$("#customerEmail").val(email);
			$("#rememberEmail").attr("checked", true);
		}
	});
	
    /* 쿠키값 읽기 */
    function getCookie(cookieName) {
    	cookieName = cookieName + '=';
    	var cookieData = document.cookie;
    	var start = cookieData.indexOf(cookieName);
    	var cookieValue = '';
    	  
    	if(start != -1){
    	  start += cookieName.length;
    	  var end = cookieData.indexOf(';', start);
    	if(end == -1)end = cookieData.length;
    	 cookieValue = cookieData.substring(start, end);
    	}
    	return unescape(cookieValue);
   	}
    
    /* 쿠키값 삭제 */
    function deleteCookie(cookieName){
    	var expireDate = new Date();
    	expireDate.setDate(expireDate.getDate() - 1);
    	document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
   	}
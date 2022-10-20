package com.exe.cozy.customer;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exe.cozy.domain.CustomerDto;

@Service
public class CustomerChk {

	@Resource CustomerService customerService;
	
	//로그인 아이디, 비밀번호 체크 메소드
	public boolean loginCheck(String customerEmail, String customerPwd) {
		CustomerDto dto = null;

        try {
            dto = customerService.getLogin(customerEmail);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return dto!=null && dto.getCustomerPwd().equals(customerPwd);
	}

	//비밀번호찾기 이메일, 연락처 체크 메소드
	public boolean forgotCheck(String customerEmail, String customerTel) {
        CustomerDto dto = null;

        try {
            dto = customerService.forgot(customerEmail);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return dto!=null && dto.getCustomerTel().equals(customerTel);
    }
	
	//임시비밀번호 발급 메소드
	public String getTmpPwd() {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    	
    	String tmpPwd = "";
    	
    	/* 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 조합 */
        int idx = 0;
        for(int i = 0; i < 10; i++){
            idx = (int) (charSet.length * Math.random());
            tmpPwd += charSet[idx];
        }
        
        return tmpPwd;
	}
	
	//비밀번호 *로 변환
	public String changePwd(String customerPwd) {
		
		int customerPwdLen = customerPwd.length();
    	String changePwd = "";
    	
    	for(int i=0;i<customerPwdLen;i++) {
    		changePwd += "*";
    	}
    	
    	return changePwd;
	}
}

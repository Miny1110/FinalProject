package com.exe.cozy.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exe.cozy.domain.CustomerDto;
import com.exe.cozy.mapper.CustomerMapper;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public int emailChk(String customerEmail) {
		return customerMapper.emailChk(customerEmail);
	}
	
	@Override
	public void insertData(CustomerDto dto) {
		customerMapper.insertData(dto);
	}

	@Override
	public CustomerDto getLogin(String customerEmail) {
		return customerMapper.getLogin(customerEmail);
	}

	@Override
	public CustomerDto forgot(String customerEmail) {
		return customerMapper.forgot(customerEmail);
	}
	
	@Override
	public void updatePwd(CustomerDto dto) {
		customerMapper.updatePwd(dto);
	}

	@Override
	public CustomerDto getReadData(String customerEmail) {
		return customerMapper.getReadData(customerEmail);
	}

	@Override
	public void updateData(CustomerDto dto) {
		customerMapper.updateData(dto);
	}

	@Override
	public void deleteData(String customerEmail) {
		customerMapper.deleteData(customerEmail);
	}

	
	
//	일반메소드
	
	//로그인 아이디, 비밀번호 체크 메소드
	@Override
	public boolean loginCheck(String customerEmail, String customerPwd) {
		CustomerDto dto = null;

        try {
            dto = getLogin(customerEmail);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return dto!=null && dto.getCustomerPwd().equals(customerPwd);
	}

	
	//비밀번호찾기 이메일, 연락처 체크 메소드
	@Override
    public boolean forgotCheck(String customerEmail, String customerTel) {
        CustomerDto dto = null;

        try {
            dto = forgot(customerEmail);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return dto!=null && dto.getCustomerTel().equals(customerTel);
    }
	
	//임시비밀번호 발급 메소드
	@Override
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
	
    

}

package com.exe.cozy.certification;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service("CertificationServiceImpl")
public class CertificationServiceImpl implements CertificationService{

	@Override
	public void certifiedPhoneNumber(String customerTel, String cerNum) {
		String api_key = "NCSL3WPD86IM5CB6";
		String api_secret = "OBIB1RRN7FZ5LVACUE0OPXYNFJDVI1K0";
		
		Message coolsms = new Message(api_key, api_secret);
		
		// 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", customerTel);    // 수신전화번호
        params.put("from", "01076441110");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "COZY HOUSE 휴대폰인증 테스트 메시지 : 인증번호는" + "["+cerNum+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version
        
        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
	}

}

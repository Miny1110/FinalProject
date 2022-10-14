package com.exe.cozy.certification;

import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CertificationController {
	
	@Resource
	private CertificationService certificationService;

	@GetMapping("/check/sendSMS")
    @ResponseBody
    public String sendSMS(String customerTel) {

        Random rand  = new Random();
        String cerNum = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            cerNum+=ran;
        }

        System.out.println("수신자 번호 : " + customerTel);
        System.out.println("인증번호 : " + cerNum);
        certificationService.certifiedPhoneNumber(customerTel,cerNum);
        return cerNum;
    }
    
}

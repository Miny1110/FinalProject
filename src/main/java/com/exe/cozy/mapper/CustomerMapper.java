package com.exe.cozy.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.exe.cozy.domain.CustomerDto;
import com.exe.cozy.domain.ReplyDto;

@Mapper
public interface CustomerMapper {
	
	public int emailChk(String customerEmail); //이메일중복확인
	
	public void insertData(CustomerDto dto); //회원가입
	public CustomerDto getLogin(String customerEmail); //로그인성공
	public CustomerDto forgot(String customerEmail); //비밀번호찾기 회원유무조회
	public void updatePwd(CustomerDto dto); //임시비밀번호발급
	
	public CustomerDto getReadData(String customerEmail); //회원정보 읽어오기
	public void updateData(CustomerDto dto); //회원정보수정
	public void updatePoint(CustomerDto dto); //포인트 수정
	public void deleteData(String customerEmail); //회원탈퇴
	
	public ReplyDto getReviewList(String customerEmail);
}

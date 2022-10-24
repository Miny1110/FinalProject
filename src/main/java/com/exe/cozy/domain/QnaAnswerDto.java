package com.exe.cozy.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaAnswerDto {

	private int qnaAnswerNum;
	private int itemQnaNum;
	private Date answerCreate;
	private String qnaContent;


	
}

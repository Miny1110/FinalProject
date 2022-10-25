package com.exe.cozy.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceQuestionDto {

	private int serviceQueNum;
	private String customerEmail;
	private String serviceQueTitle;
	private String serviceQueContent;
	private Date serviceQueDate;
	
	private ServiceAnswerDto serviceAnswer;

}

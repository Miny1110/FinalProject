package com.exe.cozy.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceAnswerDto {

	private int serviceAnsNum;
	private int serviceQueNum;
	private String serviceAnsContent;
	private Date serviceAnsDate;

	private ServiceQuestionDto serviceQueDto;
}

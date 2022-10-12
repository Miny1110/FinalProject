package com.exe.cozy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@MapperScan(value = {"com.exe.cozy.mapper"})
@SpringBootApplication
public class CozyHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CozyHouseApplication.class, args);
	}

}

package com.exe.cozy.mapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringBootBoardApplication {

	@Autowired
	ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBoardApplication.class, args);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{

		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

		/** 일케쓰던지 밑에꺼로 쓰던지
		Resource[] res = new PathMatchingResourcePatternResolver()
				.getResources("classpath:mybatis/mapper/*.xml");
		sessionFactory.setMapperLocations(res);
		*/
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setMapperLocations(
				applicationContext.getResources("classpath:mybatis/mapper/*.xml"));

		return sessionFactory.getObject();
	}


}
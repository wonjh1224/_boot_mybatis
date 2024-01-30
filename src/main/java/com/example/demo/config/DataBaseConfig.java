package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DataBaseConfig {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Value("${mybatis.mapper-locations}")
	private String mapperLocations;
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	 HikariConfig hikariconfig() {
		return new HikariConfig();
	}
	
	@Bean
	org.apache.ibatis.session.Configuration mybatisConfig(){
		return new org.apache.ibatis.session.Configuration();
	}
	
	@Bean
	DataSource dataSource() throws Exception{
		DataSource dataSource = new HikariDataSource(hikariconfig());
		return dataSource;
	}
	
	@Bean
	SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean(); // sqlSession 객체 생성
		
		sqlSessionFactoryBean.setDataSource(dataSource); // datasource 정보 추가
		
		sqlSessionFactoryBean.setConfigLocation(
				applicationContext.getResource("classpath:/mappers/mybatis-config.xml"));
		
		sqlSessionFactoryBean.addMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		
		return sqlSessionFactoryBean.getObject();
		
	}
	
	@Bean
	SqlSessionTemplate sessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
		
}

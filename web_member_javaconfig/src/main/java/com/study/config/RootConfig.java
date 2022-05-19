package com.study.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@MapperScan("com.study.mapper")     // <mybatis-spring:scan base-package="com.study.mapper"/>
@ComponentScan("com.study.service") // <mybatis-spring:scan base-package="com.study.mapper"/>
@Configuration
public class RootConfig {
	
	@Bean // 객체 생성
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		hikariConfig.setUsername("javadb");
		hikariConfig.setPassword("12345");
		
//		<bean id="ds" class="com.zaxxer.hikari.HikariDataSource" destroy-method="close">
//			<constructor-arg ref="hikariConfig"/>
//		</bean>
		HikariDataSource datasource = new HikariDataSource(hikariConfig);
		return datasource;	
	}
	
//	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
//		<property name="dataSource" ref="ds"/>
//	</bean>
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new  SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		return sqlSessionFactoryBean.getObject();
	}
	
}

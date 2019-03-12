package com.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 程序入口
 * @author Pan Jianneng
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cloud"})
@MapperScan(basePackages = {"com.cloud.mapper"})
@ServletComponentScan(basePackages = {"com.cloud.common"})
@EnableWebMvc
@EnableScheduling
public class Application {

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		LOG.info("成功启动....");
	}
}

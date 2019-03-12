package com.cloud.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.SimpleDateFormat;
import java.util.List;

/**
* @description: Swagger2配置文件
* @author: huangYl
* @date:   2018/10/24 16:30
*/
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    /**
     * 学校LOGO图片地址
     */
    @Value("${org_school_logo_file_path}")
    private String schoolLogo;

    /**
     * 报告模板图片
     */
    @Value("${report_template_img_path}")
    private String reportTemplateImgPath;

    /**
     * 报告详情存放地址
     */
    @Value("${report_detail_path}")
    private String reportDetailPath;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cloud.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    /**
     * 映射静态文件
     * @author Pan Jianneng
     * @date 2018/11/16 11:23 PM
     * @params [registry]
     * @return void
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决 SWAGGER 404报错
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/index/**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //映射图片地址到本地目录
        registry.addResourceHandler("/school/logo/**").addResourceLocations("file:"+schoolLogo+"/school/logo/");
        //映射模板图片地址到本地目录
        registry.addResourceHandler("/reportTemplate/img/**").addResourceLocations("file:"+reportTemplateImgPath+"/");
        //映射报告详情地址到本地目录
        registry.addResourceHandler("/report/**").addResourceLocations("file:"+reportDetailPath+"/");
        super.addResourceHandlers(registry);
    }

    /**
     * 构建api文档的详细信息函数
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("智能采集系统API")
                //版本号
                .version("1.0")
                //描述
                .description("智能采集系统")
                .build();
    }

}

package com.cloud.mybatisgenerator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * 生成lombok实体类
 * 自动生成文件的一些默认注释
 * @author Pan jianneng
 * @time 2018/8/2 18:33
 */
public class LombokPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    /**
     * 实体类的注释
     * @param topLevelClass TopLevelClass其实就是对Java类的DOM封装
     * @param introspectedTable 表示Table
     * @return
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //添加import
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addImportedType("lombok.Builder");
        topLevelClass.addImportedType("lombok.NoArgsConstructor");
        topLevelClass.addImportedType("lombok.AllArgsConstructor");
        //swagger包
        topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");

        //添加注解
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@Builder");
        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@AllArgsConstructor");

        //添加注释
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine("* 请添加描述");
        topLevelClass.addJavaDocLine("* @author zhuJT");
        topLevelClass.addJavaDocLine("* @time "+date2Str(new Date()));
        topLevelClass.addJavaDocLine("*/");

        return true;
    }

    /**
     * mpper的注释
     * @param interfaze
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //Mapper文件的注释
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine("* 请添加描述");
        interfaze.addJavaDocLine("* @author zhuJT");
        interfaze.addJavaDocLine("* @time "+date2Str(new Date()));
        interfaze.addJavaDocLine("*/");
        return true;
    }

    /**
     * 字段注释
     * @param field
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType){
        String remark = introspectedColumn.getRemarks();
        field.addJavaDocLine("/**");
        field.addJavaDocLine("* "+remark);
        field.addJavaDocLine("*/");
        //添加注释，swagger2的注解，生成的API注释用的
        field.addAnnotation("@ApiModelProperty(notes = \""+remark+"\")");
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成getter
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成setter
        return false;
    }

    private String date2Str(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }
}

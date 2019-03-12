//package com.cloud.controller.qualityReport;
//
//import com.cloud.annotation.execl.ExcelResolve;
//import com.cloud.entity.vo.qualityReport.FtpQualityReportVo;
//
//import java.lang.reflect.Field;
//
///**
// * @Author daituo
// * @Date
// **/
//public class Main {
//    public static void main(String[] args) {
//        FtpQualityReportVo ftpQualityReportVo = new FtpQualityReportVo();
//        Class<? extends FtpQualityReportVo> clazz = ftpQualityReportVo.getClass();
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            if (field.isAnnotationPresent(ExcelResolve.class)) {
//                ExcelResolve annotation = field.getAnnotation(ExcelResolve.class);
//                System.out.println(annotation.titleName());
//
//            }
//        }
//    }
//
//}

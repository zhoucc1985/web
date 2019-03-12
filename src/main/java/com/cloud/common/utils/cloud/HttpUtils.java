package com.cloud.common.utils.cloud;
import com.cloud.entity.datamanagement.DescJson;
import com.cloud.entity.datamanagement.HttpDBDataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http 后端发请求工具类
 */
public class HttpUtils {
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    public static void main(String[] args) {
        HttpDBDataSource dataSource=new HttpDBDataSource();
        // 待解释
        dataSource.setSource_id(1l);
        dataSource.setHost("192.168.21.11");
        dataSource.setPort("3306");
        dataSource.setOracle_param_name(null);
        dataSource.setOracle_type(null);
        dataSource.setVersion_type(null);
        dataSource.setDatabase_file(null);
        dataSource.setUser_name("collect");
        dataSource.setPassword("Sunmnet@123");
        dataSource.setDatabase_name("temp_collect_datas");
        dataSource.setDatabase_type(1);
        dataSource.setSource_name("测试");
        dataSource.setDb_target_name("");
        dataSource.setDb_type("1");
        //发送 GET 请求
        //String s=HttpUtils.sendGet("http://localhost:6144/Home/RequestString", "key=123&v=456");
        // System.out.println(s);

        //发送 POST 请求
     //  String sr=HttpUtils.sendGet("https://www.baidu.com/s?ie=utf-8&csq=1&pstg=20&mod=2&isbd=1&cqid=a3ee85e60003f043&istc=1392&ver=R2oaSQkG0eHaje7c6_LYze9W1L2yWiuMDYS&chk=5c74eacb&isid=a46ad0a40003b0ad&ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%E5%B0%8F%E7%B1%B3&oq=12%2526lt%253B06&rsv_pq=a46ad0a40003b0ad&rsv_t=b76a2zoFdt%2FoupPq9fzpdYcehkdOMl6A8PKCMcnW8CzrJGkAEMuuKihNVk4&rqlang=cn&rsv_enter=1&rsv_sug1=13&rsv_sug7=100&rsv_sug3=13&rsv_sug2=0&inputT=42417&rsv_sug4=44163&bs=12306&f4s=1&_ck=56174.0.-1.-1.-1.-1.-1&rsv_isid=1456_21087_28557&isnop=1&rsv_stat=-2&rsv_bp=1", "q=123");
       // System.out.println(sr);
      // System.out.println(dataSource.toString());
    //    String sr=HttpUtils.sendPost("http://172.16.23.28:9988/web/action/TableData/getPushData", dataSource.toString());
     //  System.out.println(sr);
        DescJson descJson =new DescJson();
        descJson.setSource_id(10l);
        descJson.setTableName("test");
        Map<String,List<String>> data= new HashMap<>();
        List<String> lista=new ArrayList<>();
        lista.add("11");
        lista.add("22");
        lista.add("33");
        List<String> listb=new ArrayList<>();
        listb.add("11");
        listb.add("22");
        listb.add("33");
        List<String> listc=new ArrayList<>();
        listc.add("11");
        listc.add("22");
        listc.add("33");
        data.put("a",lista);
        data.put("b",listb);
        data.put("c",listc);
        descJson.setData(data);
        DescJson descJson1 =new DescJson();
        descJson1.setSource_id(49l);
        descJson1.setTableName("test");
        Map<String,List<String>> data1= new HashMap<>();
        List<String> lista1=new ArrayList<>();
        lista1.add("111");
        lista1.add("222");
        lista1.add("333");
        List<String> listb1=new ArrayList<>();
        listb1.add("111");
        listb1.add("222");
        listb1.add("333");
        List<String> listc1=new ArrayList<>();
        listc1.add("111");
        listc1.add("222");
        listc1.add("333");
        data1.put("aa",lista1);
        data1.put("bb",listb1);
        data1.put("cc",listc1);
        descJson1.setData(data);
        List<DescJson> list=new ArrayList<>();
       list.add(descJson);
       list.add(descJson1);
        System.out.println(list.toString());
        String sr=HttpUtils.sendPost("http://172.16.23.28:9988/web/action/TableData/getColumnDesc","DescJson="+list.toString());
        System.out.println(sr);
    }
}

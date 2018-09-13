package com.mhw.boot.commons.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http请求工具类
 *
 * @Author mhw_mhw
 * @Date 2018/6/20 10:08
 * @Version
 */
public class HttpRequestUtil {

    /**
     * 发送HttpPost请求，参数为map
     *
     * @param map 包含多个参数
     * @return String response信息
     * @author mhw_mhw
     * @data 2018/6/19 9:56
     */
    public static String sendPost(String url, Map<String, String> map) {
        //1.获得一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.读取参数，生成一个post请求
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        for(Map.Entry<String, String> entry : map.entrySet()) {
            formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);

        //3.执行post请求并返回结果
        CloseableHttpResponse response = null;
        try{
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4.处理结果
        HttpEntity responseEntity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(responseEntity);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送HttpPost请求，自定义请求头，传入多个参数和文件，
     * 所有map的key值要去Controller里的@RequestParam一致，
     * 文件Map的大小为1，多个文件存放在数组中
     *
     * @param url 访问地址
     * @param headers HTTP请求头以及通用请求属性
     * @param params 传递的参数
     * @param files 上传的文件
     * @return
     * @author mhw_mhw
     * @data 2018/6/19 11:42
     */
    public static String sendPost(String url, Map<String, String> headers, Map<String, String> params,
                                  Map<String, File[]> files) throws IOException {

        //获得一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //执行请求后的响应
        CloseableHttpResponse response = null;
        //方法返回的结果
        String result = null;

        try {
            //生成一个post请求
            HttpPost httpPost = new HttpPost(url);

            //设置请求头、通用请求属性。
            if(headers != null && !headers.isEmpty()) {
                for(Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }

            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            //往form-data里添加需要上传的文件
            if(files != null && !files.isEmpty()) {
                if(files.size() > 1) {
                    //之后用异常枚举代替
                    return "Map大小不能超过1";
                } else {
                    //遍历File数组，将每一个文件都加到multipartEntityBuilder中。
                    //每个文件对用的key值相同
                    for(Map.Entry<String, File[]> file : files.entrySet()) {
                        String key = file.getKey();
                        File[] allFiles = file.getValue();
                        for (File singleFile : allFiles) {
                            multipartEntityBuilder.addPart(key, new FileBody(singleFile));
                        }
                    }
                }
            }
            //往form-data里添加参数
            if(params != null && !params.isEmpty()) {
                for(Map.Entry<String, String> param : params.entrySet()) {
                    StringBody value = new StringBody(param.getValue(), ContentType.create("text/plain", Consts.UTF_8));
                    multipartEntityBuilder.addPart(param.getKey(), value);
                    System.out.println(param.getKey() + " : " + param.getValue());
                }
            }
            HttpEntity reqEntity = multipartEntityBuilder.build();
            httpPost.setEntity(reqEntity);

            //发起请求并返回请求的响应
            response = httpClient.execute(httpPost);
            //处理结果
            HttpEntity responseEntity = response.getEntity();
            result = EntityUtils.toString(responseEntity);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}

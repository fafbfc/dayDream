package cn.edu.gdsdxy.sanlingerproject.util;

import cn.edu.gdsdxy.sanlingerproject.core.exception.AdminUserLogicException;
import cn.edu.gdsdxy.sanlingerproject.core.result.AdminUserResult;
import cn.edu.gdsdxy.sanlingerproject.core.result.Result;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.spi.ior.ObjectId;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public abstract class ToolUtil
{
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
    public static SimpleDateFormat simpleDateFormatTwe = new SimpleDateFormat("yyyy-MM-dd");

//    javabean对象转化map
    public static <T> Map<String, Object> ObjToMap(T obj)
    {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(obj);
            Map<String, Object> map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, String>>(){});
            return map;
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

//    javabean对象转化javabean，顺便去null
    public static <T, K> K ObjToObj(T obj, Class<K> cls)
    {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(obj);
            K k = objectMapper.readValue(jsonStr, cls);
            return k;
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

//    javabean转换json
    public static <T> String ObjToJsonStr(T obj)
    {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            String jsonStr = objectMapper.writeValueAsString(obj);
            return jsonStr;
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

//    json转map
    public static Map<String, Object> JsonStrToMap(String jsonStr)
    {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            Map<String, Object> map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, Object>>(){});
            return map;
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

//    json转set
    public static Set<String> JsonStrToSet(String jsonStr)
    {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            Set<String> set = objectMapper.readValue(jsonStr, new TypeReference<Set<String>>(){});
            return set;
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

//    封装发送http的post请求
    public static Map<String, Object> httpPost(String url, Map data, Header[] headers)
    {
        try {
            CloseableHttpClient client = null;
            CloseableHttpResponse response = null;
            try {
                ObjectMapper objectMapper = new ObjectMapper();

//                指定url和json数据和头信息
                HttpPost httpPost = new HttpPost(url);
                httpPost.setHeaders(headers);

                if( data == null || data.size() > 0 )
                {
                    httpPost.setEntity(new StringEntity(objectMapper.writeValueAsString(data),
                                                        ContentType.create("text/json", "UTF-8")));
                }

//                执行发送数据
                client = HttpClients.createDefault();
                response = client.execute(httpPost);

//                校验response的情况
                if (response == null)
                {
                    throw new AdminUserLogicException(AdminUserResult.HTTP_SEND_MISS, url, data, headers);
                }


//                获取请求体数据
                HttpEntity entity = response.getEntity();
//                这个是json数据
                String jsonStr = EntityUtils.toString(entity, "UTF-8");
//                处理错误码
                if (response.getStatusLine().getStatusCode() < HttpStatus.SC_OK || response.getStatusLine().getStatusCode()>= HttpStatus.SC_BAD_REQUEST)
                    throw new AdminUserLogicException(AdminUserResult.HTTP_SEND_MISS, jsonStr, url, data, headers);

                Map<String, Object> map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, Object>>(){});
                return map;
            } finally {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            }
        } catch (Exception e) {
            log.error("服务端发送http请求失败", e);
            throw new AdminUserLogicException(AdminUserResult.HTTP_SEND_MISS, url);
        }
    }

//    封装发送http的delete请求
    public static Map<String, Object> httpDelete(String url, Header[] headers)
    {
        try {
            CloseableHttpClient client = null;
            CloseableHttpResponse response = null;
            try {
                ObjectMapper objectMapper = new ObjectMapper();

//                指定url和json数据和头信息
                HttpDelete httpDelete = new HttpDelete(url);
                httpDelete.setHeaders(headers);

//                执行发送数据
                client = HttpClients.createDefault();
                response = client.execute(httpDelete);

//                校验response的情况
                if (response == null)
                {
                    throw new AdminUserLogicException(AdminUserResult.HTTP_SEND_MISS, url, headers);
                }


//                获取请求体数据
                HttpEntity entity = response.getEntity();
//                这个是json数据
                String jsonStr = EntityUtils.toString(entity, "UTF-8");
//                处理错误码
                if (response.getStatusLine().getStatusCode() < HttpStatus.SC_OK || response.getStatusLine().getStatusCode()>= HttpStatus.SC_BAD_REQUEST)
                    throw new AdminUserLogicException(AdminUserResult.HTTP_SEND_MISS, jsonStr, url, headers);

                Map<String, Object> map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, Object>>(){});
                return map;
            } finally {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            }
        } catch (Exception e) {
            log.error("服务端发送http请求失败", e);
            throw new AdminUserLogicException(AdminUserResult.HTTP_SEND_MISS, url);
        }
    }

    /**
     * 通过get方式，提交参数
     * @param url
     * @param param
     * @return
     */
    public static String httpGetApple(String url, Map<String, String> param)
    {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        String content = null;
        try {
            // 请求发起客户端
            httpclient = HttpClients.createDefault();
            // 拼接参数
            StringBuffer sb = new StringBuffer();
            boolean isExist = url.indexOf("?") == -1 ? false : true;
            // 遍历参数
            for(Map.Entry<String, String> entry:param.entrySet()){
                if(isExist){
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                } else {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    isExist = true;
                }
            }
            // 创建httpget.
            HttpGet httpGet = new HttpGet(url + sb.toString());
            // 执行get请求.
            response = httpclient.execute(httpGet);
            HttpEntity valueEntity = response.getEntity();
            content = EntityUtils.toString(valueEntity);
            return content;

        } catch (UnsupportedEncodingException e) {
            log.error("获取苹果公钥失败", e);
        } catch (ClientProtocolException e) {
            log.error("获取苹果公钥失败", e);
        } catch (IOException e) {
            log.error("获取苹果公钥失败", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error("关闭流失败", e);
                }
            }
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }

    public static Map<String, Object> httpGet(String url, Header[] headers){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // 请求发起客户端
            httpClient = HttpClients.createDefault();
            // 创建httpGet
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeaders(headers);
            // 执行Get请求
            response = httpClient.execute(httpGet);

            // 校验response的情况
            if (response == null)
            {
                throw new AdminUserLogicException(AdminUserResult.HTTP_SEND_MISS, url, headers);
            }
            // 获取请求体数据
            HttpEntity entity = response.getEntity();
            // 这个是json数据
            String jsonStr = EntityUtils.toString(entity, "UTF-8");
            // 处理错误码
            if (response.getStatusLine().getStatusCode() < HttpStatus.SC_OK || response.getStatusLine().getStatusCode()>= HttpStatus.SC_BAD_REQUEST)
                throw new AdminUserLogicException(AdminUserResult.HTTP_SEND_MISS, jsonStr, url, headers);

            Map<String, Object> map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, Object>>(){});
            return map;

        } catch (IOException e) {
            log.error("服务端发送http请求失败", e);
            throw new AdminUserLogicException(AdminUserResult.HTTP_SEND_MISS, url);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error("关闭流失败", e);
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    log.error("关闭流失败", e);
                }
            }
        }
    }


//    验证登录失败返回的数据
    public static void respWriteJson(HttpServletResponse response)
    {
        response.setContentType("application/json; charset=utf-8");
        AdminUserResult adminUserResult = AdminUserResult.NOT_LOGIN;
        try
        {
            response.getWriter().write(ToolUtil.ObjToJsonStr(adminUserResult));
        } catch (IOException e)
        {
            log.error("验证登录失败返回的数据失败", e);
        }
    }

//    根据时间，返回数据，0——超过时间，1——没有超过时间
    public static Integer checkTimePass(Long longTime)
    {
        if( longTime == null ) return 0;
        Long newTime = new Date().getTime();
        if( newTime > longTime ) return 0;
        else return 1;
    }

//    毫秒值转换可视文字
    public static String gainTimeStr(Long time)
    {
        if( time == null ) return "0s";
        Long liveLengthSec = time/1000;
        if( liveLengthSec > 0 && liveLengthSec < 60 )
        {
            return liveLengthSec.toString() + "s";
        }
        if( liveLengthSec >= 60 && liveLengthSec < 3600 )
        {
            Long liveLengthMix = liveLengthSec/60;
            Long surliveLengthSec = liveLengthSec%60;
            return liveLengthMix + "m " + surliveLengthSec + "s";
        }
        if( liveLengthSec >= 3600 )
        {
            Long liveLengthHour = liveLengthSec/3600;
            Long surliveLengthSec = liveLengthSec%3600;
            Long liveLengthMin = surliveLengthSec/60;
            return liveLengthHour + "h " + liveLengthMin + "m";
        }

        return null;
    }
}

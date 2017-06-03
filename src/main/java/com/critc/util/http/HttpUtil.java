package com.critc.util.http;

import com.critc.util.log.LogUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http工具类
 *
 * @author 孔垂云
 */
public class HttpUtil {

    /**
     * 发送http请求， 参数是json串，获取返回值，返回值是json
     *
     * @param url
     * @param jsonParam
     * @return
     */
    public static String getJsonFromUrlPost(String url, String jsonParam) {
        LogUtil.infoSys("请求url：" + url + ";请求参数：" + jsonParam);
        String getJson = "";
        HttpClient httpclient = null;
        HttpPost httpPost = null;
        try {
            httpclient = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");// 解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            HttpResponse response = httpclient.execute(httpPost);
            response.getStatusLine().getStatusCode();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String str = "";
            while ((str = rd.readLine()) != null) {
                getJson += str;
            }
            LogUtil.infoSys("返回值：" + getJson);
        } catch (Exception e) {
            LogUtil.infoSys(e.getMessage());
        } finally {
            httpPost.abort();
            httpPost.releaseConnection();
        }
        return getJson;
    }

    /**
     * 根据url请求服务器，获取json
     *
     * @param url url地址
     * @return 字符串
     */
    public static String getJsonFromUrl(String url) {
        LogUtil.infoSys("请求url：" + url);
        String getJson = "";
        HttpClient httpclient = null;
        HttpGet httpgets = null;
        try {
            httpclient = new DefaultHttpClient();
            httpgets = new HttpGet(url);
            httpgets.addHeader("Content-Type", "text/html;charset=UTF-8");
            HttpResponse response = httpclient.execute(httpgets);
            response.getStatusLine().getStatusCode();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String str = "";
            while ((str = rd.readLine()) != null) {
                getJson += str;
            }
            LogUtil.infoSys("返回值：" + getJson);
        } catch (Exception e) {
            LogUtil.infoSys(e.getMessage());
        } finally {
            httpgets.abort();
            httpgets.releaseConnection();
        }
        return getJson;
    }

    /**
     * 根据请求，获取map
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(HttpServletRequest request) {
        // 解析结果存储在HashMap
        Map<String, String> map = new HashMap<String, String>();
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();
            // 遍历所有子节点
            for (Element e : elementList)
                map.put(e.getName(), e.getText());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.infoSys("解析xml报错：" + e.getMessage());
            LogUtil.infoSys("解析xml报错：" + inputStream.toString());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            inputStream = null;
        }
        return map;
    }

    /**
     * 解析返回的xml
     *
     * @param protocolXML
     * @return
     */
    public static HashMap<String, String> parseXml(String protocolXML) {
        protocolXML = protocolXML.replaceAll("\\\n", "");
        System.out.println(protocolXML);
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document doc = builder.parse(new InputSource(new StringReader(protocolXML)));
            org.w3c.dom.Element root = doc.getDocumentElement();
            NodeList nodes = root.getChildNodes();
            if (nodes != null) {
                for (int i = 0; i < nodes.getLength(); i++) {
                    Node book = nodes.item(i);
                    hashMap.put(book.getNodeName(), book.getFirstChild().getNodeValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }
}

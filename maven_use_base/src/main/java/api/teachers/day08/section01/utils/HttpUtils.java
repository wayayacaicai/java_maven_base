package api.teachers.day08.section01.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import api.teachers.day08.section01.pojo.ApiCaseDetail;


/**
 * http发包工具类
 * @author happy
 * @date 2019年3月30日
 * @desc 
 * @email
 */
public class HttpUtils {
	
	private static Logger logger = Logger.getLogger(HttpUtils.class);
	//logger:只有我这个类用，并且一个对象好了，提供给类方法和对象方法取用

	/**
	 *  发起get请求
	 * @param apiCaseDetail
	 * @return
	 */
	public static String get(ApiCaseDetail apiCaseDetail) {
		Map<String, String> parametersMap = (Map<String, String>) JSONObject.parse(apiCaseDetail.getRequestData());
		String baseUrl = apiCaseDetail.getApiInfo().getUrl();

		return get(baseUrl, parametersMap);
	}

	/**
	 * get方法
	 * @param baseUrl 基础url
	 * @param parametersMap 参数列表
	 * @return
	 */
	public static String get(String baseUrl, Map<String, String> parametersMap) {
		try {
			//准备一个参数容器，httpclient需要
			List<NameValuePair> parameters = null;
			//如果不为空才需要遍历
			if (parametersMap != null) {
				//实例化容器
				parameters = new ArrayList<NameValuePair>();
				//map的迭代
				Set<String> keySet = parametersMap.keySet();
				for (String key : keySet) {
					String value = parametersMap.get(key);
					//添加名值对到容器
					parameters.add(new BasicNameValuePair(key, value));
				}
			}
			//参数的编码
			String encodeParamStr = URLEncodedUtils.format(parameters, "utf-8");
			//get请求实例化
			HttpGet get = new HttpGet(baseUrl + "?" + encodeParamStr);
			//准备发包客户端
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//获得相应
			CloseableHttpResponse response = httpClient.execute(get);
			//返回相应体
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * post请求
	 * @param apiCaseDetail 测试用例详细信息对象
	 * @return
	 */
	public static String post(ApiCaseDetail apiCaseDetail) {
		Map<String, String> parametersMap = (Map<String, String>) JSONObject.parse(apiCaseDetail.getRequestData());
		String baseUrl = apiCaseDetail.getApiInfo().getUrl();
		return post(baseUrl, parametersMap);
	}

	/**
	 * post请求
	 * @param url 接口地址
	 * @param parametersMap 参数的map
	 * @return
	 */
	public static String post(String url, Map<String, String> parametersMap) {
		HttpPost post = new HttpPost(url);
		try {
			List<NameValuePair> parameters = null;
			if (parametersMap != null) {
				parameters = new ArrayList<NameValuePair>();
				Set<String> keySet = parametersMap.keySet();
				for (String key : keySet) {
					String value = parametersMap.get(key);
					parameters.add(new BasicNameValuePair(key, value));
				}
			}
			post.setEntity(new UrlEncodedFormEntity(parameters));

			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(post);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			//打印堆栈信息
//			e.printStackTrace();
			//记录一个日志：error、warn、info、debug
			//报异常
			logger.error("调用post方法出现异常:"+e.getMessage());
			//主观行为
			//应用层面
		}
		return "";
	}

	public static String delete(String baseUrl, Map<String, String> parametersMap) {
		//TODO
		return "";
	}

	public static String put(String baseUrl, Map<String, String> parametersMap) {
		//TODO
		return "";
	}

	/**
	 * 接口中转站--》分发
	 * 转发不同方法的接口
	 * @param apiCaseDetail
	 * @return
	 */
	public static String request(ApiCaseDetail apiCaseDetail) {
		String type = apiCaseDetail.getApiInfo().getType();
		String entityStr = "";
		if ("get".equalsIgnoreCase(type)) {
			//把接口的详细信息记录下来
			logger.info("发起get请求："+apiCaseDetail.getApiInfo().getApiName());
			entityStr = get(apiCaseDetail);
		}else if("post".equalsIgnoreCase(type)){
			logger.info("发起post请求："+apiCaseDetail.getApiInfo().getApiName());
			entityStr = post(apiCaseDetail);
		}
		return entityStr;
	}
}

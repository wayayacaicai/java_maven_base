package api.advanced.data_check_cookies.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import api.advanced.data_check_cookies.pojo.ApiCaseDetail;
import api.advanced.data_check_cookies.pojo.HeaderOperateObject;
import api.teachers.day09.section01.utils.ParameterUtils;

/**
 * @Desc:请求工具类
 * @author:zpp
 * @time:2019年4月1日 下午8:50:26
 */
public class HttpUtils {
	// 日志对象
	private static Logger logger = Logger.getLogger(HttpUtils.class);

	/**
	 * @Desc get请求方法
	 * @param baseUrl
	 * @param hm
	 * @param apiCaseDetail
	 * @return
	 */
	public static String get(String baseUrl, Map<String, String> hm, ApiCaseDetail apiCaseDetail) {
		List<NameValuePair> params = new ArrayList<NameValuePair>(); // 需要的容器类型
		if (hm != null) { // 判断
			Set<Entry<String, String>> entrySet = hm.entrySet(); // 通用类型转换为需要的容器类型
			for (Entry<String, String> entry : entrySet) {
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		String get_params = URLEncodedUtils.format(params, "utf-8"); // 请求参数拼接
		HttpGet get = new HttpGet(baseUrl + "?" + get_params); // get请求拼接

		// 判定是否有请求头提取信息
		if (!StringUtils.isEmpty(apiCaseDetail.getReqHeader())) {
			setHeaderMethod(get, apiCaseDetail);
		}

		String responseEntityStr = null; // 响应体信息定义
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建客户端
			CloseableHttpResponse response = httpClient.execute(get); // 获取响应对象

			HttpEntity responseEntity = response.getEntity(); // 获取响应体
			responseEntityStr = EntityUtils.toString(responseEntity); // 响应体字符串形式

			// 判定是否有请求头提取信息
			if (!StringUtils.isEmpty(apiCaseDetail.getRespHeader())) {
				getHeaderMethod(response, apiCaseDetail);
			}

		} catch (Exception e) {
			logger.error("工具类get请求调用出现异常" + e.getMessage());
		}
		return responseEntityStr;
	}

	/**
	 * @Desc 得到响应头
	 * @param response
	 * @param apiCaseDetail
	 */
	private static void getHeaderMethod(CloseableHttpResponse response, ApiCaseDetail apiCaseDetail) {
		// 提取响应头的数据
		String respHeader = apiCaseDetail.getRespHeader();
		// 转换为java对象
		List<HeaderOperateObject> headerOperateObjects = JSONObject.parseArray(respHeader, HeaderOperateObject.class);
		for (HeaderOperateObject headerOperateObject : headerOperateObjects) {
			// 需要提取的响应头的key
			String headerName = headerOperateObject.getHeaderName();
			// 需要设置到全局池中的key
			String paramName = headerOperateObject.getParamName();

			// 得到第一个Header
			Header firstHeader = response.getFirstHeader(headerName);
			// Set-Cookie: JSESSIONID=AB5157AFEE38D9DBE7C83ACF30DBFD9A;
			// Path=/futureloan; HttpOnly
			// JSESSIONID=AB5157AFEE38D9DBE7C83ACF30DBFD9A; Path=/futureloan;
			// HttpOnly
			HeaderElement[] elements = firstHeader.getElements();
			HeaderElement firstHeaderElement = elements[0];
			// 回头要设置到其他接口中间的请求头Cookie对应的时:携带cookie
			String cookieValue = firstHeaderElement.getName() + "=" + firstHeaderElement.getValue();
			// 保存到全局数据池
			ParameterUtils.addGlobalData(paramName, cookieValue);
		}
	}

	/**
	 * @Desc 设置请求头
	 * @param httpRequest
	 * @param apiCaseDetail
	 */
	private static void setHeaderMethod(HttpRequest httpRequest, ApiCaseDetail apiCaseDetail) {
		// 提取请求头的数据
		String reqHeader = apiCaseDetail.getReqHeader();
		// 转换为java对象
		List<HeaderOperateObject> headerOperateObjects = JSONObject.parseArray(reqHeader, HeaderOperateObject.class);
		for (HeaderOperateObject headerOperateObject : headerOperateObjects) {
			// 需要设置的请求头的key
			String headerName = headerOperateObject.getHeaderName();
			// 通过全局池中获取请求头值的key
			String paramName = headerOperateObject.getParamName();
			// 获取请求头的值
			String headerValue = ParameterUtils.getGlobalData(paramName);
			// 设置请求头
			httpRequest.setHeader(headerName, headerValue);
		}
	}

	/**
	 * @Desc post请求方法
	 * @param baseUrl
	 * @param hm
	 * @param apiCaseDetail
	 * @return
	 */
	public static String post(String baseUrl, Map<String, String> hm, ApiCaseDetail apiCaseDetail) {
		HttpPost post = new HttpPost(baseUrl); // post请求
		String responseEntityStr = null; // 响应体信息
		List<NameValuePair> params = new ArrayList<NameValuePair>(); // 需要的容器类型
		if (hm != null) { // 不能为空
			Set<Entry<String, String>> entrySet = hm.entrySet(); // 通用类型转换为需要的容器类型
			for (Entry<String, String> entry : entrySet) {
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		try {
			post.setEntity(new UrlEncodedFormEntity(params)); // 设置请求体

			// 判定是否有请求头提取信息
			if (!StringUtils.isEmpty(apiCaseDetail.getReqHeader())) {
				setHeaderMethod(post, apiCaseDetail);
			}

			CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建客户端
			CloseableHttpResponse response = httpClient.execute(post); // 获取响应对象

			HttpEntity responseEntity = response.getEntity(); // 获取响应体
			responseEntityStr = EntityUtils.toString(responseEntity); // 响应体字符串形式

			// 判定是否有请求头提取信息
			if (!StringUtils.isEmpty(apiCaseDetail.getRespHeader())) {
				getHeaderMethod(response, apiCaseDetail);
			}
		} catch (Exception e) {
			logger.error("工具类post请求调用出现异常" + e.getMessage());
		}
		return responseEntityStr;
	}

	/**
	 * @Desc 直接传一个apiCaseDetail对象的get请求方法
	 * @param apiCaseDetail
	 * @return
	 */
	public static String get(ApiCaseDetail apiCaseDetail) {
		// 发送请求之前做参数替换
		String replacedRequestData = ParameterUtils.getReplacedStr(apiCaseDetail.getRequestData());
		String baseUrl = apiCaseDetail.getApiInfo().getUrl();
		Map<String, String> paramsMap = (Map<String, String>) JSONObject.parse(replacedRequestData);
		return get(baseUrl, paramsMap, apiCaseDetail);
	}

	/**
	 * @Desc 直接传一个apiCaseDetail对象的post请求方法
	 * @param apiCaseDetail
	 * @return
	 */
	public static String post(ApiCaseDetail apiCaseDetail) {
		// 发送请求之前做参数替换
		String replacedRequestData = ParameterUtils.getReplacedStr(apiCaseDetail.getRequestData());
		String baseUrl = apiCaseDetail.getApiInfo().getUrl();
		Map<String, String> paramsMap = (Map<String, String>) JSONObject.parse(replacedRequestData);
		return post(baseUrl, paramsMap, apiCaseDetail);
	}

	/**
	 * @Desc 请求方法(判定get or post)
	 * @param apiCaseDetail
	 * @return
	 */
	public static String request(ApiCaseDetail apiCaseDetail) {
		// 得到api类型（get or post）
		String type = apiCaseDetail.getApiInfo().getType();
		// 返回响应值
		String responseData = null;
		if ("get".equalsIgnoreCase(type)) {
			logger.info("发起get请求" + apiCaseDetail.getApiId());
			responseData = get(apiCaseDetail);
		} else if ("post".equalsIgnoreCase(type)) {
			logger.info("发起post请求" + apiCaseDetail.getApiId());
			responseData = post(apiCaseDetail);
		} else {
			System.out.println("没有执行get或者post请求！");
		}
		return responseData;
	}
}

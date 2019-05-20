package base01;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @Desc:基本get请求 
 * @author:zpp 
 * @time:2019年5月14日 下午5:02:12
 */
public class HttpGetTest {
	public static void main(String[] args){
		try {
			//接口地址
			String url = "http://120.78.128.25:8080/futureloan/mvc/api/member/register?mobilephone=13699999999&pwd=123456";
			//get请求
			HttpGet get = new HttpGet(url);
			//客户端
			CloseableHttpClient client = HttpClients.createDefault();
			//执行get请求，得到响应信息
			CloseableHttpResponse response = client.execute(get);
			//状态行信息
			StatusLine statusLine = response.getStatusLine();
			System.out.print(statusLine.getProtocolVersion() + " ");
			System.out.print(statusLine.getStatusCode() + " ");
			System.out.println(statusLine.getReasonPhrase());
			//响应头信息
			Header[] allHeaders = response.getAllHeaders();
			for (Header header : allHeaders) {
				System.out.println(header.getName() + ":" + header.getValue());
			}
			//空行
			System.out.println();
			//响应体信息
			HttpEntity entity = response.getEntity();
			String entityStr = EntityUtils.toString(entity);
			System.out.println(entityStr);
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}

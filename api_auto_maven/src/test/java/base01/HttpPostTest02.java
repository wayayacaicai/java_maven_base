package base01;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @Desc:基本post请求---使用键值对型
 * @author:zpp 
 * @time:2019年5月14日 下午5:22:10
 */
public class HttpPostTest02 {
	public static void main(String[] args){
		try {
			//接口地址
			String url = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";
			//post请求
			HttpPost post = new HttpPost(url);
			//post请求体设置
			StringEntity postBody = new StringEntity("mobilephone=13888855555&pwd=123456&regname=happy", ContentType.APPLICATION_FORM_URLENCODED);
			post.setEntity(postBody);
			//客户端
			CloseableHttpClient client = HttpClients.createDefault();
			//执行post请求，得到响应信息
			CloseableHttpResponse response = client.execute(post);
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

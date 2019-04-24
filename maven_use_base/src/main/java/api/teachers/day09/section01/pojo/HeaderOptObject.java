package api.teachers.day09.section01.pojo;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class HeaderOptObject {
	
//	[{"headerName":"Set-Cookie","paramName":"cookie"},{"headerName":"rememberMe","paramName":"rememberMe"}]
	//请求头或响应头的名称
	private String headerName;
	
	//设值到全局数据池的key
	private String paramName;

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	@Override
	public String toString() {
		return "HeaderOptObject [headerName=" + headerName + ", paramName=" + paramName + "]";
	}
	
	
	public static void main(String[] args) {
		String jsonStr = "[{\"headerName\":\"Set-Cookie\",\"paramName\":\"cookie\"},{\"headerName\":\"rememberMe\",\"paramName\":\"rememberMe\"}]";
		//要把上面的字符串格式化成一个列表，列表中是一个一个的HeaderOptObject
		//解析这个字符串到一个一个的对象去
		List<HeaderOptObject> headerOptObjects = JSONObject.parseArray(jsonStr, HeaderOptObject.class);
		for (HeaderOptObject headerOptObject : headerOptObjects) {
			System.out.println(headerOptObject.getHeaderName() + "---" + headerOptObject.getParamName());
		}
	}
}

package api.advanced.data_check_cookies.utils;

public class StringUtils {
	/**
	 * @Desc 判定字符串是否为空或者是否为一个或者多个空格
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str != null && str.trim().length()>0){
			return false;
		}else{
			return true;
		}
	}
}

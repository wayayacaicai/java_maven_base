package api.teachers.day09.section01.utils;

public class StringUtils {
	
	//如果一个字符串是null，或者空格，或者空字符串，都属于空
	public static boolean isEmpty(String str){
		/*if (str != null && str.trim().length() > 0) {
			return false;
		}else{
			return true;
		}*/
		
		return (str==null) || (str.trim().length() == 0);
			
	}

	public static void main(String[] args) {
		System.out.println(isEmpty(""));
		System.out.println(isEmpty("    "));
		System.out.println(isEmpty(null));
		System.out.println(isEmpty("  h  "));
		System.out.println(isEmpty("happy"));
	}
}

package base07.utils;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import base07.pojo.ApiCaseDetail;
import base07.pojo.CellData;
import base07.pojo.SqlChecker;

/**
 * @Desc:提供数据验证的工具类 
 * @author:zpp 
 * @time:2019年6月8日 下午2:54:38
 */
public class DataCheckerUtils {

	public static void check(ApiCaseDetail apiCaseDetail, String checkInfo) {
		List<SqlChecker> sqlCheckerList = null;
		if("before".equalsIgnoreCase(checkInfo)){
			sqlCheckerList = apiCaseDetail.getBeforeSqlCheckerList();
		}else if("after".equalsIgnoreCase(checkInfo)){
			sqlCheckerList = apiCaseDetail.getAfterSqlCheckerList();
		}else{
			System.out.println("数据错误！");
		}
		if(sqlCheckerList == null){  //如果为空直接返回
			return;
		}
		
		for (SqlChecker sqlChecker : sqlCheckerList) {
			String sql = sqlChecker.getSql(); //需要验证的sql
			String expectedResult = sqlChecker.getExpectedResult(); //期望结果
			List<HashMap<String, String>> actualResultList = DBUtils.sqlQuery(sql); //查询结果
			String actualResult = JSONObject.toJSONString(actualResultList); //查询结果字符串
			//添加实际结果
			DataProviderUtils.addSqlChecker(new CellData(sqlChecker.getRowNo(), 6, actualResult));
			
			if(actualResult.equalsIgnoreCase(expectedResult)){
				DataProviderUtils.addSqlChecker(new CellData(sqlChecker.getRowNo(), 7, "成功"));
			}else {
				DataProviderUtils.addSqlChecker(new CellData(sqlChecker.getRowNo(), 7, "失败"));
			}
		}
	}
	
}

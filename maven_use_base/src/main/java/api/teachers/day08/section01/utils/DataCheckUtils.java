package api.teachers.day08.section01.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import api.teachers.day08.section01.pojo.ApiCaseDetail;
import api.teachers.day08.section01.pojo.CellData;
import api.teachers.day08.section01.pojo.SqlChecker;

public class DataCheckUtils {
	
	/**
	 * 前置验证
	 * @param apiCaseDetail
	 */
	public static void beforeCheck(ApiCaseDetail apiCaseDetail){
		//获得前置验证sql列表
		List<SqlChecker> sqlCheckerList = apiCaseDetail.getBeforeSqlList();
		if (sqlCheckerList == null) {
			return;
		}
		
		//来一个标记：默认是通过
		boolean checkFlag = true;
		//数据验证
		for (SqlChecker sqlChecker : sqlCheckerList) {
			//要验证的sql
			String sql = sqlChecker.getSql();
			//预期结果
			String expectedResult = sqlChecker.getExpectedResult();
			//验证
			List<LinkedHashMap<String, String>> actualResult = DBUtils.select(sql);
			//把list转为json字符串
			String actualResultStr = JSONObject.toJSONString(actualResult);
			//添加实际结果到回写的数据容器
			ApiUtils.addSqlCellData(new CellData(sqlChecker.getRowNo(), 6, actualResultStr));
			//[{count(*)=1}]     [{"count(*)":"1"}]
			if (actualResultStr.equals(expectedResult)) {
				//验证通过
				ApiUtils.addSqlCellData(new CellData(sqlChecker.getRowNo(), 7, "成功"));
			}else {
				//验证不通过
				ApiUtils.addSqlCellData(new CellData(sqlChecker.getRowNo(), 7, "失败"));
				//该测试用例对应的某条数据验证的sql验证不通过--》该测试用例数据验证不通过
				checkFlag = false;
			}
		}
		
		if (checkFlag) {
			//验证通过的：添加回写的结果
			ApiUtils.addCellData(new CellData(apiCaseDetail.getRowNo(), 7, "通过"));
		}else{
			//该测试用例的数据库验证不通过：添加回写的结果
			ApiUtils.addCellData(new CellData(apiCaseDetail.getRowNo(), 7, "不通过"));
		}
	}

	public static void afterCheck(ApiCaseDetail apiCaseDetail) {
		//获得前置验证sql列表
		List<SqlChecker> sqlCheckerList = apiCaseDetail.getAfterSqlList();
		if (sqlCheckerList == null) {
			return;
		}
		
		//数据验证
		for (SqlChecker sqlChecker : sqlCheckerList) {
			//要验证的sql
			String sql = sqlChecker.getSql();
			//预期结果
			String expectedResult = sqlChecker.getExpectedResult();
			//验证
			List<LinkedHashMap<String, String>> actualResult = DBUtils.select(sql);
			//把list转为json字符串
			String actualResultStr = JSONObject.toJSONString(actualResult);
			
			//添加实际结果到回写的数据容器
			ApiUtils.addSqlCellData(new CellData(sqlChecker.getRowNo(), 6, actualResultStr));
			//[{count(*)=1}]     [{"count(*)":"1"}]
			if (actualResultStr.equals(expectedResult)) {
				//验证通过
				ApiUtils.addSqlCellData(new CellData(sqlChecker.getRowNo(), 7, "成功"));
			}else {
				//验证不通过
				ApiUtils.addSqlCellData(new CellData(sqlChecker.getRowNo(), 7, "失败"));
			}
		}
	}

}

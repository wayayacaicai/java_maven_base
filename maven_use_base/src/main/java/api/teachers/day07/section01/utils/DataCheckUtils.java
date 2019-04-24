package api.teachers.day07.section01.utils;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import api.teachers.day07.section01.pojo.ApiCaseDetail;
import api.teachers.day07.section01.pojo.CellData;
import api.teachers.day07.section01.pojo.SqlChecker;

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
		
		//数据验证
		for (SqlChecker sqlChecker : sqlCheckerList) {
			//要验证的sql
			String sql = sqlChecker.getSql();
			//预期结果
			String expectedResult = sqlChecker.getExpectedResult();
			//验证
			List<HashMap<String, String>> actualResult = DBUtils.select(sql);
			//把list转为json字符串
			String actualResultStr = JSONObject.toJSONString(actualResult);
			
			System.out.println(sqlChecker.getRowNo());
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
			List<HashMap<String, String>> actualResult = DBUtils.select(sql);
			//把list转为json字符串
			String actualResultStr = JSONObject.toJSONString(actualResult);
			
			System.out.println(sqlChecker.getRowNo());
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

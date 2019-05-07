package api.advanced.data_check_cookies.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import api.advanced.data_check_cookies.pojo.ApiCaseDetail;
import api.advanced.data_check_cookies.pojo.CellData;
import api.advanced.data_check_cookies.pojo.SqlChecker;

/**
 * @Desc:数据验证工具类
 * @author:zpp
 * @time:2019年4月21日 下午11:30:57
 */
public class DataCheckUtils {
	/**
	 * @Desc 前置验证
	 * @param apiCaseDetail
	 */
	public static void beforeCheck(ApiCaseDetail apiCaseDetail) {
		// 获得前置验证sql列表
		List<SqlChecker> beforeSqlList = apiCaseDetail.getBeforeSqlList();
		if (beforeSqlList == null) {
			return;
		}
		//来一个标记
		boolean checkFlag = true;
		// 数据验证
		for (SqlChecker sqlChecker : beforeSqlList) {
			// 要验证的sql
			String sql = sqlChecker.getSql();
			// 预期结果
			String expectedResult = sqlChecker.getExpectedResult();
			// 验证
			List<LinkedHashMap<String, String>> actualResult = DaoUtils.sqlQuery(sql);
			// 把list转为json
			String actualResultStr = JSONObject.toJSONString(actualResult);

			// 添加实际结果到回写的数据容器
			ApiDataProviderUtils.addSqlData(new CellData(sqlChecker.getRowNo(), 6, actualResultStr));
			if (actualResultStr.equals(expectedResult)) {
				// 验证通过
				ApiDataProviderUtils.addSqlData(new CellData(sqlChecker.getRowNo(), 7, "成功"));
			} else {
				// 验证不通过
				ApiDataProviderUtils.addSqlData(new CellData(sqlChecker.getRowNo(), 7, "失败"));
				checkFlag = false;
			}
		}
		if(checkFlag){
			//验证通过
			ApiDataProviderUtils.addCellData(new CellData(apiCaseDetail.getRowNo(), 7, "通过"));
		}else{
			//验证不通过
			ApiDataProviderUtils.addCellData(new CellData(apiCaseDetail.getRowNo(), 7, "不通过"));
		}
	}

	/**
	 * @Desc 后置验证
	 * @param apiCaseDetail
	 */
	public static void afterCheck(ApiCaseDetail apiCaseDetail) {
		// 获得后置验证sql列表
		List<SqlChecker> afterSqlList = apiCaseDetail.getAfterSqlList();
		if (afterSqlList == null) {
			return;
		}
		//来一个标记
		boolean checkFlag = true;
		// 数据验证
		for (SqlChecker sqlChecker : afterSqlList) {
			// 要验证的sql
			String sql = sqlChecker.getSql();
			// 预期结果
			String expectedResult = sqlChecker.getExpectedResult();
			// 验证
			List<LinkedHashMap<String, String>> actualResult = DaoUtils.sqlQuery(sql);
			// 把list转为json
			String actualResultStr = JSONObject.toJSONString(actualResult);

			// 添加实际结果到回写的数据容器
			ApiDataProviderUtils.addSqlData(new CellData(sqlChecker.getRowNo(), 6, actualResultStr));
			if (actualResultStr.equals(expectedResult)) {
				// 验证通过
				ApiDataProviderUtils.addSqlData(new CellData(sqlChecker.getRowNo(), 7, "成功"));
			} else {
				// 验证不通过
				ApiDataProviderUtils.addSqlData(new CellData(sqlChecker.getRowNo(), 7, "失败"));
				checkFlag = false;
			}
		}
		if(checkFlag){
			//验证通过
			ApiDataProviderUtils.addCellData(new CellData(apiCaseDetail.getRowNo(), 7, "通过"));
		}else{
			//验证不通过
			ApiDataProviderUtils.addCellData(new CellData(apiCaseDetail.getRowNo(), 7, "不通过"));
		}
	}
}

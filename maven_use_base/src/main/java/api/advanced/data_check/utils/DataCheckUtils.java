package api.advanced.data_check.utils;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import api.advanced.data_check.pojo.ApiCaseDetail;
import api.advanced.data_check.pojo.CellData;
import api.advanced.data_check.pojo.SqlChecker;

/**
 * @Desc:数据验证工具类
 * @author:zpp
 * @time:2019年4月21日 下午11:30:57
 */
public class DataCheckUtils {
	public static void beforeCheck(ApiCaseDetail apiCaseDetail) {
		// 获得前置验证sql列表
		List<SqlChecker> beforeSqlList = apiCaseDetail.getBeforeSqlList();
		if (beforeSqlList == null) {
			return;
		}
		// 数据验证
		for (SqlChecker sqlChecker : beforeSqlList) {
			// 要验证的sql
			String sql = sqlChecker.getSql();
			// 预期结果
			String expectedResult = sqlChecker.getExpectedResult();
			// 验证
			List<HashMap<String, String>> actualResult = DaoUtils.sqlQuery(sql);
			// 把list转为json
			String actualResultStr = JSONObject.toJSONString(actualResult);

			// 添加实际结果到回写的数据容器
			ApiUtils.addSqlData(new CellData(sqlChecker.getRowNo(), 6, actualResultStr));
			if (actualResultStr.equals(expectedResult)) {
				// 验证通过
				ApiUtils.addSqlData(new CellData(sqlChecker.getRowNo(), 7, "成功"));
			} else {
				// 验证不通过
				ApiUtils.addSqlData(new CellData(sqlChecker.getRowNo(), 7, "失败"));
			}
		}

	}

	/**
	 * @Desc
	 * @param apiCaseDetail
	 */
	public static void afterCheck(ApiCaseDetail apiCaseDetail) {
		// 获得后置验证sql列表
		List<SqlChecker> afterSqlList = apiCaseDetail.getAfterSqlList();
		if (afterSqlList == null) {
			return;
		}
		// 数据验证
		for (SqlChecker sqlChecker : afterSqlList) {
			// 要验证的sql
			String sql = sqlChecker.getSql();
			// 预期结果
			String expectedResult = sqlChecker.getExpectedResult();
			// 验证
			List<HashMap<String, String>> actualResult = DaoUtils.sqlQuery(sql);
			// 把list转为json
			String actualResultStr = JSONObject.toJSONString(actualResult);

			// 添加实际结果到回写的数据容器
			ApiUtils.addSqlData(new CellData(sqlChecker.getRowNo(), 6, actualResultStr));
			if (actualResultStr.equals(expectedResult)) {
				// 验证通过
				ApiUtils.addSqlData(new CellData(sqlChecker.getRowNo(), 7, "成功"));
			} else {
				// 验证不通过
				ApiUtils.addSqlData(new CellData(sqlChecker.getRowNo(), 7, "失败"));
			}
		}
	}
}

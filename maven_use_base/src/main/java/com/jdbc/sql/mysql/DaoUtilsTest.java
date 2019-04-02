package com.jdbc.sql.mysql;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;;

/**
 * @Desc:测试数据库的封装
 * @author:zpp
 * @time:2019年3月22日 下午10:55:19
 */
public class DaoUtilsTest {
	/**
	 * 查询
	 */
	@Test
	public void testDaoQuery() {
		String sql = "select id,regname from member where id=?";
		List<HashMap<String, String>> aList = DaoUtils.sqlQuery(sql, "150");
		DaoUtils.printQueryResult(aList);
	}

	/**
	 * 增删改
	 */
	@Test
	public void testDaoAddDelUpdate() {
		// 增加测试
		String sql = "insert into member(RegName,Pwd,MobilePhone,Type,LeaveAmount,RegTime) values(?,?,?,?,?,?)";
		// DaoUtils.sqlAddDelUpdate(sql,
		// "roller1","123456","12345678912","1","1000","2019-03-15 21:41:49");
		// 修改测试
		String sql3 = "update member set pwd=? where id >= ? and id <= ?;";
		// DaoUtils.sqlAddDelUpdate(sql3, "546321","152","152");
		// 删除测试
		String sql4 = "delete from member where id between ? and ?";
		DaoUtils.sqlAddDelUpdate(sql4, "152", "152");
	}
}

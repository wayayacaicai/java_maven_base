package com.jdbc.sql.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Desc:预编译查询方法
 * @author:zpp
 * @time:2019年3月22日 下午10:55:51
 */
public class MySqlPrepareStatementQuery {

	public static void main(String[] args) throws Exception {
		String sql = "select * from member where id=？";
		// 此处是把整个当作一个值，而sql注入是判断的单个3
		List<HashMap<String, String>> aList = sqlQuery(sql, " 3 or 1=1");

		System.out.println(aList);
		// 打印
		// for (HashMap<String, String> hm : aList) {
		// Set<Entry<String, String>> entrySet = hm.entrySet();
		// for (Entry<String, String> entry : entrySet) {
		// System.out.print(entry.getKey()+":"+entry.getValue()+",");
		// }
		// System.out.println("");
		// }
	}

	private static List<HashMap<String, String>> sqlQuery(String sql, String... params) {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://120.78.128.25:3306/future";
			String user = "futurevistor";
			String password = "123456";
			Connection conn = DriverManager.getConnection(url, user, password);

			// 预编译
			PreparedStatement pstat = conn.prepareStatement(sql);
			// 设置参数值
			for (int i = 0; i < params.length; i++) {
				pstat.setString(i + 1, params[i]);
			}

			// 返回结果集
			ResultSet rs = pstat.executeQuery();
			// 元数据
			ResultSetMetaData metaData = pstat.getMetaData();
			// 返回的列数
			int count = metaData.getColumnCount();

			List<HashMap<String, String>> aList = new ArrayList<>();
			while (rs.next()) {
				// 用一个hashmap接收每列数据
				HashMap<String, String> hm = new HashMap<>();
				for (int i = 1; i <= count; i++) {
					// 返回的列名
					String key = metaData.getColumnName(i);
					String value = rs.getString(i);
					hm.put(key, value);
				}
				// 用list接收行数据
				aList.add(hm);
			}

			pstat.close();
			conn.close();
			return aList;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}
}

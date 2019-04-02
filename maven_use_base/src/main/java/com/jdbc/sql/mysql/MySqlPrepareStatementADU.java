package com.jdbc.sql.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @Desc:预编译增删改方法
 * @author:zpp
 * @time:2019年3月22日 下午10:55:32
 */
public class MySqlPrepareStatementADU {

	public static void main(String[] args) throws Exception {
		String sql = "insert into member(RegName,Pwd,MobilePhone,Type,LeaveAmount,RegTime) values(?,?,?,?,?,?)";
		// sqlADU(sql,"roller1","123456","12345678912","1","1000","2019-03-15
		// 21:41:49");
		String sql3 = "update member set pwd=? where id >= ? and id <= ?;";
		// sqlADU(sql3, "546321","65","70");
		String sql4 = "delete from member where id between ? and ?";
		// sqlADU(sql4, "65","82");

	}

	private static void sqlADU(String sql, String... params) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://120.78.128.25:3306/future";
		String user = "futurevistor";
		String password = "123456";
		Connection conn = DriverManager.getConnection(url, user, password);

		PreparedStatement pstat = conn.prepareStatement(sql);
		ResultSetMetaData metaData = pstat.getMetaData();
		for (int i = 0; i < params.length; i++) {
			pstat.setString(i + 1, params[i]);
		}
		pstat.execute();

		pstat.close();
		conn.close();
	}

}

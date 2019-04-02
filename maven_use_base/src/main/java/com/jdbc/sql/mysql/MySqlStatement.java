package com.jdbc.sql.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @Desc:数据库基础操作
 * @author:zpp
 * @time:2019年3月22日 下午10:56:13
 */
public class MySqlStatement {

	public static void main(String[] args) throws Exception {
		// 第一步：装载MySql驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 第二步：获取数据库连接
		String url = "jdbc:mysql://120.78.128.25:3306/future";
		String user = "futurevistor";
		String password = "123456";
		Connection conn = DriverManager.getConnection(url, user, password);
		// 第三步：创建陈述对象
		String sql1 = "insert into member(RegName,Pwd,MobilePhone,Type,LeaveAmount,RegTime) values('roller1','123456','12345678912',1,99999.99999,'2019-03-15 21:41:49')";
		String sql2 = "select * from member where id=" + " 3 or 1=1";
		String sql3 = "update member set pwd='123123' where id BETWEEN 89 and 99;";
		String sql4 = "delete from member where id = 95";
		Statement stat = conn.createStatement();
		// 第四步：执行sql
		// 插入
		// stat.execute(sql1);
		// 查询
		ResultSet rs = stat.executeQuery(sql2);
		while (rs.next()) {
			String id = rs.getString(1);
			String regName = rs.getString(2);
			System.out.println("id:" + id + "," + "regName:" + regName);
		}
		// 修改
		// stat.execute(sql3);
		// 删除

		// 第五步：关闭连接
		// stat.execute(sql4);
		stat.close();
		conn.close();
	}

}

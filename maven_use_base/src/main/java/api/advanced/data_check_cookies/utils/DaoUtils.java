package api.advanced.data_check_cookies.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * @Desc:数据库工具类(有序集合、用别名来)
 * @author:ZPP
 * @time:2019年3月22日 下午10:36:30
 */
public class DaoUtils {
	/**
	 * 定义配置文件的成员属性
	 */
	private static String url;
	private static String user;
	private static String password;

	// 用静态代码块加载jdbc驱动(注册驱动，只需要一次)、配置文件
	static {
		try {
			// 读取配置文件
			Properties properties = new Properties();
			properties.load(DaoUtils.class.getResourceAsStream("/config/jdbc.properties"));
			// 读取配置文件属性
			String driver = properties.getProperty("jdbc.driver");
			url = properties.getProperty("jdbc.url");
			user = properties.getProperty("jdbc.user");
			password = properties.getProperty("jdbc.password");
			// 注册驱动
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Desc 获取数据库连接对象
	 * @return
	 */
	private static Connection getConn() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * @Desc 获取预编译对象以及设置sql（？）对应参数----params
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 */
	private static PreparedStatement prepareStatementAndSetString(Connection conn, String sql, String... params) {
		PreparedStatement pstat = null;
		try {
			pstat = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				pstat.setString(i + 1, params[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstat;
	}

	/**
	 * @Desc 数据库增删改方法
	 * @param sql
	 * @param params
	 */
	public static void sqlAddDelUpdate(String sql, String... params) {
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = getConn();
			pstat = prepareStatementAndSetString(conn, sql, params);
			// 执行预编译对象
			pstat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstat);
		}
	}

	/**
	 * @Desc 数据库增删改资源的释放方法
	 * @param conn
	 * @param pstat
	 */
	private static void close(Connection conn, PreparedStatement pstat) {
		if (pstat != null) {
			try {
				pstat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Desc 数据库查询方法
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<LinkedHashMap<String, String>> sqlQuery(String sql, String... params) {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		List<LinkedHashMap<String, String>> aList = null;

		try {
			conn = getConn();
			pstat = prepareStatementAndSetString(conn, sql, params);
			// 返回结果集
			rs = pstat.executeQuery();
			// 获取结果集的元数据
			ResultSetMetaData metaData = rs.getMetaData();
			// 返回的列数
			int count = metaData.getColumnCount();

			aList = new ArrayList<>();
			while (rs.next()) {
				// 用一个LinkedHashMap接收每列数据
				LinkedHashMap<String, String> hm = new LinkedHashMap<>();
				for (int i = 1; i <= count; i++) {
					// 返回的列名
//					String key = metaData.getColumnName(i);
					//得到别名
					String key = metaData.getColumnLabel(i);
					String value = rs.getString(i);
					hm.put(key, value);
				}
				// 用list接收行数据
				aList.add(hm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstat, rs);
		}
		return aList;
	}

	/**
	 * @Desc 数据库查询资源的释放方法
	 * @param conn
	 * @param pstat
	 * @param rs
	 */
	private static void close(Connection conn, PreparedStatement pstat, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstat != null) {
			try {
				pstat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Desc 打印返回的list集合
	 * @param aList
	 */
	public static void printQueryResult(List<HashMap<String, String>> aList) {
		for (HashMap<String, String> hm : aList) {
			Set<Entry<String, String>> entrySet = hm.entrySet();
			for (Entry<String, String> entry : entrySet) {
				System.out.print(entry.getKey() + ":" + entry.getValue() + ",");
			}
			System.out.println("");
		}
	}
}

package api.teachers.day06.section02.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;


public class DBUtils {

	//硬编码，写死的代码--》解耦-》配置文件
	/**
	 * 连接字符串
	 */
	private static String url;
	/**
	 * 用户名
	 */
	private static String user;
	/**
	 * 密码
	 */
	private static String password;

	/**
	 * 上面的这些信息会变吗？？
	 * 		数据库名
	 * 		用户名密码变了
	 * 		数据库服务器ip换了
	 * 		测试环境切换到生产服务器
	 * 		数据库：mysql--》oracle
	 * 测试环境
	 */

	//注册驱动：只需要一次，当你用DBUtils类的时候，就注册好
	//静态代码块：只在类加载到jvm中执行一遍
	static {
		try {
			Properties properties = new Properties();
			properties.load(DBUtils.class.getResourceAsStream("/jdbc.properties"));
			url = properties.getProperty("jdbc.url");
			user = properties.getProperty("jdbc.user");
			password = properties.getProperty("jdbc.password");

			String driver = properties.getProperty("jdbc.driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//获得连接的方法
	private static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 增删改
	 * @param sql
	 * @param parameters
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void excuteSQL(String sql, String... parameters) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < parameters.length; i++) {
				pstmt.setString(i + 1, parameters[i]);
			}
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	

	/**
	 * 查询
	 * @param sql
	 * @param parameters
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static List<HashMap<String, String>> select(String sql, String... parameters) {
		//是可能有多条记录记录--》保存到一个什么数据容器
		List<HashMap<String, String>> allRecordList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			//获得连接
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			//参数设值
			for (int i = 0; i < parameters.length; i++) {
				pstmt.setString(i + 1, parameters[i]);
			}
			//执行查询获取结果集
			resultSet = pstmt.executeQuery();
			//获取结果集的元数据：   元数据（描述数据的数据）
			ResultSetMetaData metaData = resultSet.getMetaData();
			//获得每个字段，需要知道字段数，获得列数
			int columnCount = metaData.getColumnCount();//5
			allRecordList = new ArrayList<HashMap<String, String>>();
			//遍历结果集
			while (resultSet.next()) {
				//一条记录是包装成一个map
				HashMap<String, String> recordMap = new HashMap<String, String>();
				//遍历所有的列
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					String value = resultSet.getString(i);
					//				System.out.print(columnName+":"+value +"    ");
					//把字段名作为key，对应数据作为值： key -value:
					recordMap.put(columnName, value);
				}
				//添加到List容器中
				allRecordList.add(recordMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, resultSet);
		}

		return allRecordList;
	}

	/**
	 * 关闭资源
	 * @param conn
	 * @param pstmt
	 * @param resultSet
	 */
	private static void close(Connection conn, PreparedStatement pstmt, ResultSet resultSet) {
		//关闭资源
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close(conn, pstmt);
	}

	/**
	 * 关闭资源
	 * @param conn
	 * @param pstmt
	 */
	private static void close(Connection conn, PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
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
	 * example
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		String sql = "select count(*) FROM member where MobilePhone='13666666666';";
		List<HashMap<String, String>> recordList = select(sql);
		for (HashMap<String, String> record : recordList) {
			System.out.println(record);
		}
		/**
		 一个测试用例前置sql和后置sql有多少条
		1:sql的编号
		2：可以是任意多条：详细的sql语句
		3：每条sql对应的预期的结果
		 */
	}
}

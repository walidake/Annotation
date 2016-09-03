package com.walidake.annotation.mybatis;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.walidake.dbcp.util.DBUtil;
import com.walidake.dbcp.util.ResultSetMapper;

/**
 * 
 * TODO 可以用工厂模式把代码抽出  在那之前暂时放在这里吧
 * 
 * @author walidake
 *
 */
public final class DaoOperatorFactory {
	
	/**
	 * 针对不同的方法进行不同的操作
	 * 
	 * @param method
	 * @param parameters
	 * @throws SQLException
	 * 
	 * @return object
	 */
	public static Object handle(Method method, Object[] parameters)
			throws SQLException {
		String sql = null;
		if (method.isAnnotationPresent(Insert.class)) {
			sql = checkSql(method.getAnnotation(Insert.class).value(),
					Insert.class.getSimpleName());
			insert(sql, parameters);
			return null;
		} else if (method.isAnnotationPresent(Select.class)) {
			sql = checkSql(method.getAnnotation(Select.class).value(),
					Select.class.getSimpleName());
			Class<?> clazz = method.getReturnType();
			if (java.util.List.class.isAssignableFrom(clazz)) {
				// 查询多条记录
				Class<?> outputClass = (Class<?>) (((ParameterizedType) method
						.getGenericReturnType()).getActualTypeArguments()[0]);
				return selectMultiply(sql, parameters, outputClass);
			} else {
				// 查询单条记录
				return selectSingle(sql, parameters, clazz);
			}
		} else if (method.isAnnotationPresent(Update.class)) {
			sql = checkSql(method.getAnnotation(Update.class).value(),
					Update.class.getSimpleName());
			update(sql, parameters);
		} else if (method.isAnnotationPresent(Delete.class)) {
			sql = checkSql(method.getAnnotation(Delete.class).value(),
					Delete.class.getSimpleName());
			update(sql, parameters);
		}
		return null;
	}
	
	
	/**
	 * 插入记录
	 * 
	 * @param sql
	 * @param parameters
	 * @throws SQLException
	 */
	private static void insert(String sql, Object[] parameters) throws SQLException {
		Connection connection = DBUtil.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		for (int i = 0; parameters != null && i < parameters.length; i++) {
			prepareStatement.setObject(i + 1, parameters[i]);
		}
		prepareStatement.execute();
		connection.close();
	}
	
	/**
	 * 
	 * 查询多条记录
	 * 
	 * @param sql
	 * @param parameters
	 * @param outputClass
	 *            根据方法返回查询出类型
	 * @return
	 * @throws SQLException
	 */
	private static <T> List<T> selectMultiply(String sql, Object[] parameters,
			Class<?> outputClass) throws SQLException {
		Connection connection = DBUtil.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		for (int i = 0; parameters != null && i < parameters.length; i++) {
			prepareStatement.setObject(i + 1, parameters[i]);
		}
		ResultSet resultSet = prepareStatement.executeQuery();
		List<T> pojoList = new ResultSetMapper<T>().mapRersultSetToObject(
				resultSet, outputClass);
		connection.close();
		return pojoList;
	}

	/**
	 * 
	 * 查询单条记录
	 * 
	 * @param sql
	 * @param parameters
	 * @param outputClass
	 *            根据方法返回查询出类型
	 * @return
	 * @throws SQLException
	 */
	private static <T> T selectSingle(String sql, Object[] parameters,
			Class<?> outputClass) throws SQLException {
		List<T> ts = selectMultiply(sql, parameters, outputClass);
		if (ts.size() > 0) {
			return ts.get(0);
		}
		return null;
	}

	/**
	 * 更新记录|删除记录
	 * 
	 * @param sql
	 * @param parameters
	 * @throws SQLException
	 */
	private static void update(String sql, Object[] parameters) throws SQLException {
		Connection connection = DBUtil.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		for (int i = 0; parameters != null && i < parameters.length; i++) {
			prepareStatement.setObject(i + 1, parameters[i]);
		}
		prepareStatement.executeUpdate();
		connection.close();
	}

	/**
	 * 检查sql语句
	 * 
	 * @param sql
	 * @param type
	 * @return
	 * @throws SQLException
	 */
	private static String checkSql(String sql, String type) throws SQLException {
		String type2 = sql.split(" ")[0];
		if (type2 == null || !type2.equalsIgnoreCase(type)) {
			throw new SQLException("Incorrect SQL.");
		}
		return sql;
	}
}

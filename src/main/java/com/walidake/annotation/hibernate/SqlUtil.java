/** 
 *
 * @ClassName: SqlUtil 
 *
 * @Description: TODO
 *
 * @author walidake
 *
 * @date 2016年6月14日 上午10:55:15 
 *
 **/
package com.walidake.annotation.hibernate;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成建表语句
 * 
 * @author walidake
 *
 */
class SqlUtil {

	public static String createTable(Class<?> clazz) {
		StringBuilder sb = new StringBuilder();
		String tableName = getTableName(clazz);
		if (tableName == null || "".equals(tableName))
			return null;
		sb.append("create table ").append(tableName).append("(");
		Map<String, String> columns = getColumnName(clazz);
		for (Map.Entry<String, String> entry : columns.entrySet()) {
			sb.append(entry.getKey());
			sb.append(" ");
			sb.append(entry.getValue());
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		return sb.toString().toLowerCase();
	}

	/**
	 * 获取表名
	 * 
	 * @param clazz
	 * @return
	 * @author walidake
	 */
	private static String getTableName(Class<?> clazz) {
		// 判断是否具有Table注解
		if (clazz.isAnnotationPresent(Table.class)) {
			// 获取注解对象
			Table table = clazz.getAnnotation(Table.class);
			return "".equals(table.name()) ? clazz.getSimpleName() : table
					.name();
		}
		return null;
	}

	/**
	 * 获取列名
	 * 
	 * @param clazz
	 * @return
	 * @author walidake
	 */
	private static Map<String, String> getColumnName(Class<?> clazz) {
		Map<String, String> columns = new HashMap<>();
		// 反射取得所有Field
		Field[] fields = clazz.getDeclaredFields();
		if (fields != null) {
			// 列名 类型
			String columnName, type;
			Column column;
			for (int i = 0; i < fields.length; i++) {
				// 判断是否具有Column注解
				if (fields[i].isAnnotationPresent(Column.class)) {
					// 获取注解对象
					column = fields[i].getAnnotation(Column.class);
					fields[i].setAccessible(true);
					columnName = "".equals(column.name()) ? fields[i].getName()
							: column.name();
					// 根据不同类型生成不同的sql语句
					if (int.class.isAssignableFrom(fields[i].getType())) {
						type = "int";
					} else if (String.class.isAssignableFrom(fields[i]
							.getType())) {
						type = (column.varchar() ? "varchar" : "char") + "("
								+ column.length() + ")";
					} else if (Date.class.isAssignableFrom(fields[i].getType())) {
						type = "date";
					} else {
						throw new RuntimeException("Unspported type:"
								+ fields[i].getType().getSimpleName());
					}
					type += (column.isNull() ? "" : " not null");
					columns.put(columnName, type);
				} else if (fields[i].isAnnotationPresent(PrimaryKey.class)) {
					// PrimaryKey特判
					PrimaryKey primaryKey = fields[i]
							.getAnnotation(PrimaryKey.class);
					fields[i].setAccessible(true);
					columnName = "".equals(primaryKey.name()) ? fields[i]
							.getName() : primaryKey.name();
					type = "int primary key auto_increment";
					columns.put(columnName, type);
				}
			}
		}
		return columns;
	}
}

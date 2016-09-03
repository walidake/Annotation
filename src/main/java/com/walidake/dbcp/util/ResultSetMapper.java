package com.walidake.dbcp.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

public class ResultSetMapper<T> {

	@SuppressWarnings("unchecked")
	public List<T> mapRersultSetToObject(ResultSet rs, Class<?> outputClass) {
		List<T> outputList = null;
		try {
			// 务必保证rs不为空
			if (rs != null) {
				// ResultSetMetaData 有关 ResultSet 中列的名称和类型的信息。
				ResultSetMetaData rsmd = rs.getMetaData();
				while (rs.next()) {
					T bean = (T) outputClass.newInstance();
					for (int i = 0; i < rsmd.getColumnCount(); i++) {
						// 取得列名
						String columnName = rsmd.getColumnName(i + 1);
						// 取得列名对应的值
						Object columnValue = rs.getObject(i + 1);

						Field field = outputClass.getDeclaredField(columnName);
						if (field != null && columnValue != null) {
							BeanUtils.setProperty(bean, field.getName(),
									columnValue);
						}
					}
					if (outputList == null) {
						outputList = new ArrayList<T>();
					}
					outputList.add(bean);
				}

			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if (outputList == null) {
			//返回空集合，而不是null
			return Collections.emptyList();
		}
		return outputList;
	}
}

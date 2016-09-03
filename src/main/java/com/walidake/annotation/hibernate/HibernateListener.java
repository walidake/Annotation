package com.walidake.annotation.hibernate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.walidake.dbcp.util.DBUtil;

public class HibernateListener implements ServletContextListener {

	public static final String MODE = "mode";
	public static final String SCAN_PACKAGE_PATH = "scanPackagePath";

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		String mode = servletContextEvent.getServletContext().getInitParameter(
				MODE);
		String packagePath = servletContextEvent.getServletContext()
				.getInitParameter(SCAN_PACKAGE_PATH);
		try {
			if (mode != null && "create".equals(mode)) {
				ClassScanner classScanner = new ClassScanner();
				classScanner.scanning(packagePath, false);
				Map<String, Class<?>> classes = classScanner.getClasses();
				
				//扫描路径下所有包
				//System.out.println("--->>" + classes);
				for(Map.Entry<String, Class<?>> entry:classes.entrySet()){
					create(entry.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub

	}

	/**
	 * 创建数据库表
	 * 
	 * @param clazz
	 * @throws SQLException
	 * 
	 */
	private void create(Class<?> clazz) throws SQLException {
		Connection connection = null;
		connection = DBUtil.getConnection();
		String createSql = SqlUtil.createTable(clazz);
		if (createSql != null && !"".equals(createSql)) {
			//TODO 需要检查数据库表结构是否存在 存在则删除重新建表
			connection.createStatement().execute(createSql);
			
			//此处不进行代码格式化
			//Hibernate 使用的是开源的语法解析工具 Antlr，有兴趣的话你可以去看看
			//然而这需要进行 SQL 语法分析、解析，将 SQL 语句整理成语法树，还是需要大量的时间了解的
			//其他的操作可以在 @com.walidake.annotation.hibernate.SQLFormatter 找到
			System.out.println(createSql);
		}
	}

}

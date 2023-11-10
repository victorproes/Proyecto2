package com.conexion;

import java.sql.Connection;
import java.sql.SQLException;
 
import javax.sql.DataSource;
 
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
public class Conexion {
	private static BasicDataSource dataSource = null;
	 
	 private static DataSource getDataSource() {
	  if (dataSource == null) {
	   dataSource = new BasicDataSource();
	   dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
	   dataSource.setUsername("root");
	   dataSource.setPassword("123456");
	   dataSource.setUrl("jdbc:mariadb://localhost:3306/nomina");
	   dataSource.setInitialSize(20);
	   dataSource.setMaxIdle(15);
	   dataSource.setMaxTotal(20);
	
	  }
	  return dataSource;
	 }
	 
	 public static Connection getConnection() throws SQLException {
	  return getDataSource().getConnection();
	 }
}

package com.hnb.global;
/**
 * @file_name : Constants.java 
 * @author    : coolbeat@naver.com
 * @date      : 2015. 10. 13.
 * @story     : 
 */
public class Constants {
	/**
	 * 밴더(Vendor : 공급업체) 가 제공하는 low-level 의 접속값
	 */
	public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String ORACLE_ID = "hanbit";
	public static final String ORACLE_PASSWORD = "hanbit";
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/DB_NAME";
	public static final String HSQL_DRIVER = "org.hsqldb.jdbcDriver";
	public static final String HSQL_URL = "jdbc:hsqldb:C:/jse/MyDB;readonly=true";
	public static final String HSQL_ID = "sa";
	public static final String HSQL_PASSWORD = "";
	public static final String VIEW = "/WEB-INF/view/";
}

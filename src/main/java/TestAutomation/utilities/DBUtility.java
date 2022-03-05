package TestAutomation.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtility {
	
	public static String url = "jdbc:oracle:thin:@//localhost:1521/XE";
	private static String driverName = "oracle.jdbc.driver.OracleDriver";
	private static String username = "lalitha";
	private static String password = "lalitha";
	
	private static final DBUtility dbUtility = new DBUtility();
	public DBUtility(){}

	public static DBUtility getInstance(){
		return dbUtility;
	}

	private static Connection connection;
	public  Connection getConnection() throws SQLException {
		getInstance();
		if ( connection == null) {
			createConnection();
		}
		if (connection.isClosed()){
			createConnection();
		}else {
			connection.setAutoCommit(true);
			return connection;
		}
		connection.setAutoCommit(true);
		return connection;
	}
	
	public Connection createConnection(){
		try {
			Class.forName(driverName);
			try {
				connection = java.sql.DriverManager.getConnection(url, username, password);
			} catch (SQLException var2) {
				System.out.println(var2);
			}

			return connection;
		}
		catch (ClassNotFoundException var3) {
			throw new CustomException("Cannot connect to "+url);
		}
	}
	private  ResultSet executeQuery(String queryString,Connection connection) {

		try{
			if(connection != null && !checkIsclosed(connection)){
				Statement stm = connection.createStatement();
				ResultSet rs = stm.executeQuery(queryString);
				return rs;
			}else{
				executeQuery(queryString, getConnection());
			}
		}catch (SQLException e)
		{
			System.out.println("!!!!!!!!!!!!!! There was a problem executing the query '"+queryString+"'");
			throw new CustomException("There was a problem executing the query '"+queryString+"'", e);
		}
		return null;

	}

	private boolean checkIsclosed(Connection connection) throws SQLException
	{
		return connection.isClosed();
	}
	public String getValueFromResultSetString(String query , String requiredColumn) throws SQLException{

		Connection connection = null;

		ResultSet rs = null;	

		try{
			connection = getConnection();
			rs = executeQuery(query, connection);
			String finalValue=null;

			if(rs.next()){

				finalValue=	rs.getNString(requiredColumn);
				System.out.println(finalValue);
			}

			if (!rs.isClosed()){
				rs.close();
			}
			return finalValue;
		}catch (SQLException e)
		{
			throw new CustomException("There was a problem executing the query", e);
		}finally
		{

			if (rs!= null && rs.isClosed()!=true){
				rs.close();
			}

		}

	}
	


}

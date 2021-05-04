package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.ConnectionDataBase;

public class DaoCalculaDataFinal {

	private Connection connection;

	public DaoCalculaDataFinal() {
		connection = ConnectionDataBase.getConnection();
	}
	
	public void gravarDataFinal(String date) throws Exception {
		String sql = "insert into finalprojetos (datafinal) values(?)";
		PreparedStatement gravarData = connection.prepareStatement(sql);
		gravarData.setString(1, date);
		gravarData.execute();
	}
	
	
}

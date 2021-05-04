package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**Classe respons�vel por realizar a conex�o com o banco de dados.
Conex�o PostgreSql para o banco java-estudo**/

public class ConnectionDataBase {

	private static String banco = "jdbc:postgresql://localhost:5432/java-estudo?autoReconnect=true";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;

	static {

		conectar();
	}

	
	
	public ConnectionDataBase() {
		conectar();
	}

	
	
	private static void conectar() {
		try {

			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
			}
			
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar com o banco de dados" + e.getMessage());
		}

	}
	
	/**
	 * Retorna a conex�o do banco de dados. 
	@Return connection SQL.
	*/
	
	public static Connection getConnection() {
		return connection;
	}
}

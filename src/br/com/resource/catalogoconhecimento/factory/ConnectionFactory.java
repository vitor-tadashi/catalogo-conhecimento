package br.com.resource.catalogoconhecimento.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection createConnection() throws SQLException, ClassNotFoundException {
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		Class.forName(driver);

		String url = "jdbc:sqlserver://192.168.80.233:1433;instanceName = CatalogoConhecimentos;Database=CatalogoConhecimentos";

		String user = "sa";

		String password = "1234";

		Connection conexao = null;
		conexao = DriverManager.getConnection(url, user, password);

		return conexao;
	}

}
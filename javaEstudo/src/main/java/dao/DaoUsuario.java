package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectionDataBase;
import entidades.Usuario;

public class DaoUsuario {

	private static Connection connection;

	public DaoUsuario() {
		connection = ConnectionDataBase.getConnection();
	}

	public ArrayList<Usuario> listaDeUsuarios() throws SQLException {

		ArrayList<Usuario> listaDeUsuarios = new ArrayList<>();
		String sql = "select * from usuario";
		PreparedStatement listar = connection.prepareStatement(sql);
		ResultSet resultSet = listar.executeQuery();

		while (resultSet.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));

			listaDeUsuarios.add(usuario);
		}
		return listaDeUsuarios;
	}

}

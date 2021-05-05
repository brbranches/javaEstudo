package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectionDataBase;
import entidades.Eventos;
import entidades.Usuario;

public class EventosDAO {

	private static Connection connection;

	public EventosDAO() {
		connection = ConnectionDataBase.getConnection();
	}

	public ArrayList<Eventos> listaDeEventos() throws SQLException {

		ArrayList<Eventos> listaDeEventos = new ArrayList<>();
		String sql = "select * from eventos";
		PreparedStatement listar = connection.prepareStatement(sql);
		ResultSet resultSet = listar.executeQuery();

		while (resultSet.next()) {
			Eventos evento = new Eventos();
			evento.setId(resultSet.getString("id"));
			evento.setDataEvento(resultSet.getString("dataevento"));
			evento.setDecricao(resultSet.getString("descricao"));


			listaDeEventos.add(evento);
		}
		return listaDeEventos;
	}

}


package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entidades.Usuario;

@WebServlet("/pages/carregarDadosDataTable")
public class carregarDadosDataTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO daoUsuario = new UsuarioDAO();

	public carregarDadosDataTable() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ArrayList<Usuario> usuarios = daoUsuario.listaDeUsuarios();

			String data = "";
			int totalUsuarios = usuarios.size();

			int index = 1;

			for (Usuario usuario : usuarios) {
				data += " [" + "\"" + usuario.getId() + "\"," + "\"" + usuario.getLogin() + "\"," + "\""
						+ usuario.getSenha() + "\"" + "]";

				if (index < totalUsuarios) {
					data += ",";
				}

				index++;

			}

			if (!usuarios.isEmpty()) {

				String json = "{" + "\"draw\": 1," + "\"recordsTotal\": " + usuarios.size() + ","
						+ "\"recordsFiltered\": " + usuarios.size() + "," + "\"data\": [" + data + // INICIO - Processa
																									// a lista de dados
						// FIM - Processa a lista de dados

						"]" + // Fechamento do data
						"}"; // Fechamento do json

				response.setStatus(200);// REsposta OK.
				response.getWriter().write(json); // json com a resposta.

			}

		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(500);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}

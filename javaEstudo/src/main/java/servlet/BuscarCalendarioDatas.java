package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EventosDAO;
import entidades.Eventos;

@WebServlet("/pages/buscarCalendarioDatas")
public class BuscarCalendarioDatas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EventosDAO eventosDao = new EventosDAO();

	public BuscarCalendarioDatas() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			List<Eventos> listaDeEventos = eventosDao.listaDeEventos();

			if (!listaDeEventos.isEmpty()) {

				int totalEventos = listaDeEventos.size();
				int indice = 1;
				String datas = "[";

				for (Eventos eventos : listaDeEventos) {

					datas += "{\"title\" : \"" + eventos.getDecricao() + "\",\"start\" : \"" + eventos.getDataEvento()
							+ "\"}";
					if (indice < totalEventos) {
						datas += ",";
					}
					indice++;
				}

				datas += "]";

				response.setStatus(200);
				response.getWriter().write(datas);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pages/fileUpload")
public class fileUpload extends HttpServlet {

	private static final long serialVersionUID = 1L;

		
	
	
	public fileUpload() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Aqui já podemos usar a variável fileUpload para salvar no banco de dados. Ela
			// já está em base64
			String fileUpload = request.getParameter("fileUpload");

			response.getWriter().write("Upload realizado com sucesso");

		} catch (Exception e) {
			response.getWriter().write("Erro ao realizar o upload");
		}
	}

}

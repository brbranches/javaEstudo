package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entidades.Usuario;
import service.RelatorioService;

@WebServlet("/pages/ServletDownloadFile")

public class ServletDownloadFile extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private RelatorioService relatorioService = new RelatorioService();
	private UsuarioDAO daoUsuario = new UsuarioDAO();

	public ServletDownloadFile() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			ServletContext context = request.getServletContext();
			
			String tipoExportar = request.getParameter("tipoExportar");
			
			List<Usuario> usuarios = daoUsuario.listaDeUsuarios();

			List dados = new ArrayList<>();
			dados.add(usuarios);

			String fileUrl = relatorioService.gerarRelatorio(dados, new HashMap(), "rel_usuario", "rel_usuario",
					context);

			/* Construir o caminho completo e absoluto do arquivo */
			File downloadFile = new File(fileUrl);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			
			//Obter o tipo MIME do arquivo
			String mimeType = context.getMimeType(fileUrl);
			
			if (mimeType == null) {
				//Define como tipo binário se o mapeamento mime nao for encontrado.
				mimeType = "application/octet-stream"; //Consegue fazer o download de qqr coisa
			}

			//Define os atributos para resposta
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			
			//Define os parâmetros de cabeçalho para a resposta
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			
			response.setHeader(headerKey, headerValue);

			//Obter um fluxo de saída da resposta
			OutputStream outputStream = response.getOutputStream();
			
			byte[] buffer = new byte[4096];
			int byteReader = -1;
			
			//Escrever os bytes lidos a partir do fluxo de entrada para o fluxo de saída;
			
			while ((byteReader = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer,0,byteReader);
			}
			
			inputStream.close();
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}

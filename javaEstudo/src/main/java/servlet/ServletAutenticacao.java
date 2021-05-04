package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserLogado;

@WebServlet("/pages/ServletAutenticacao")
public class ServletAutenticacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletAutenticacao() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (Boolean.parseBoolean(request.getParameter("deslogar"))) {
			HttpServletRequest req = (HttpServletRequest) request;//Abro a sessão e passo para a req 
			HttpSession session = req.getSession();// Pego a sessão e guardo na session
			session.invalidate();
			response.sendRedirect("../index.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Pego os valores vindos da tela
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		//Aqui poderia ser feita a validação no banco de dados, aqui estamos fazendo fixo no código
		//apenas para fins didáticos
		
		if (login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("123")) {//Se login foi bem sucedido
			
			//Instancio um objeto pra guardar as informações vindas da tela.
			UserLogado userLogado = new UserLogado();
			
			//Seto os valores vindos da tela nos atributos do objeto usuario
			userLogado.setLogin(login);
			userLogado.setSenha(senha);
			
			HttpServletRequest req = (HttpServletRequest) request;//Abro a sessão e passo para a req 
			HttpSession session = req.getSession();// Pego a sessão e guardo na session
			session.setAttribute("usuario", userLogado); //Guardo as informações de usuário (agora logado) na sessão
			
			
			//Redireciona o usuario para o sistema ja autorizado
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		
		}else {//Se login falhar
			//Redireciona o usuario para a tela de login novamente
			RequestDispatcher dispatcher = request.getRequestDispatcher("/autenticar.jsp");
			dispatcher.forward(request, response);
		}
	}

}

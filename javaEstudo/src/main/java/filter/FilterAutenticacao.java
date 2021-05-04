package filter;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.ConnectionDataBase;
import user.UserLogado;

@WebFilter(urlPatterns = {"/pages/*"})
public class FilterAutenticacao implements Filter{
	
	private static Connection connection; 
	
	//Executa alguma coisa quando a aplica��o � derrubada 
	@Override
	public void destroy() {
	}
	
	//Executa alguma coisa quando a aplica��o � iniciada
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		connection = ConnectionDataBase.getConnection();
		
	}

	//Intercepta todas as requisi��es
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request; 
		HttpSession session = req.getSession();
		
		
		String urlParaAutenticar = req.getServletPath();
		
		
		//Retorna null caso nao esteja logado
		UserLogado userLogado = (UserLogado) session.getAttribute("usuario");
		
		
		if (userLogado == null && !urlParaAutenticar.equalsIgnoreCase("/pages/ServletAutenticacao")) {//Quer dizer que o usu�rio nao est� logado
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/autenticar.jsp?url=" + urlParaAutenticar);
			dispatcher.forward(request, response);// Executa a requi��o.
			return; //o return para o processo para redirecionar;
		}	
		
		chain.doFilter(request, response); //O chain � respons�vel por fazer a execu��o do request e do response.
		
	}

}

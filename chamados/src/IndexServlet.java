import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Atendimentos</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>ATENDIMENTOS - Chamados de Clientes</h1>");
			out.println("<hr>");
			out.println("<a href='http://localhost:8080/chamados/NovoChamado'>Novo Chamado</a>");
			out.println("<br>");
			out.println("<a href='http://localhost:8080/chamados/ListarChamados'>Listar Chamados</a>");
			out.println("<br>");
			out.println("<a href='logoff'>Sair</a>");
			out.println("</body>");
			out.println("</html>");
		} catch (Exception e) {
				
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		
	}
	
	

}

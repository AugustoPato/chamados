

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NovoChamadoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Atendimentos</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Preencha os dados</h1>");
			out.println("<hr>");
			out.println("<form method='POST'>");
			out.println("Código:<br> <input type='text' name='txtcodigo'>");
			out.println("<br>");
			out.println("Titulo:<br> <input type='text' name='txttitulo'>");
			out.println("<br>");
			out.println("Conteudo:<br> <textarea rows='10' cols='40' name='txtconteudo'></textarea>");
			out.println("<br>");
			out.println("<input type='submit' value='Gravar'>");
			out.println("</form>");
			out.println("<br>");
			out.println("<a href='http://localhost:8080/chamados/ListarChamados'>Listar Chamados</a>");
			out.println("<br>");
			out.println("<a href='logoff'>Sair</a>");
			out.println("</body>");
			out.println("</html>");
		} catch (Exception e) {
				
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String codigo = request.getParameter("txtcodigo");
		String titulo = request.getParameter("txttitulo");
		String conteudo = request.getParameter("txtconteudo");
		
		if(titulo.trim().equals("")){
			out.println("Preencha o campo titulo");
		} else if(conteudo.trim().equals("")){
			out.println("Preencha o campo conteudo");
		} else {
			try {
				Class.forName("org.postgresql.Driver");
				Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Chamados","postgres","post");
				
				String SQL = "INSERT INTO atendimentos (ID, TITULO, CONTEUDO) VALUES (?, ?, ?)";
				//INSERT INTO "Atendimentos"(id, titulo, conteudo, data) VALUES (?, ?, ?, ?);
								
				PreparedStatement pstm = conn.prepareStatement(SQL);
				
				pstm.setString(1, codigo);
				pstm.setString(2, titulo);
				pstm.setString(3, conteudo);
				
				pstm.execute();
				out.println("Cadastrado com sucesso");
				pstm.close();
				conn.close();
				} catch (ClassNotFoundException e) {
					out.println("Problemas ao carregar o driver de conexão");
					e.printStackTrace();
				} catch (SQLException e) {
					out.println("Problema ao gravar dados: " + e.getMessage());
				}
		}
	}

}

package pos_java_jdbc.pos_java_jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import junit.framework.TestCase;
import model.UserposJava;

public class TesteBancoJDBC {

	@Test
	public void initBanco() throws SQLException {
		UserPosDAO userPosDAO = new UserPosDAO();
		UserposJava userposJava = new UserposJava();

		userposJava.setId(6L);
		userposJava.setNome("Carlos");
		userposJava.setEmail("carlinhos1304@gmail.com");

		userPosDAO.salvar(userposJava);
	}

	@Test
	public void initListar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<UserposJava> list = dao.listar();
			
			for (UserposJava userposJava : list) {
				System.out.println(userposJava);
				System.out.println("-------------------------");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public void initBuscar() {
		
		UserPosDAO dao = new UserPosDAO();
		
			try {
				UserposJava userposJava = dao.Buscar(5L);
				System.out.println(userposJava);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	
	}
}

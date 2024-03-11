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

		userposJava.setNome("Carlos");
		userposJava.setEmail("carlinhos1304@gmail.com");

		userPosDAO.salvar(userposJava);
	}

//metodo para listar o banco de dados 
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

//metodo para buscar no banco de dados 
	@Test
	public void initBuscar() {

		UserPosDAO dao = new UserPosDAO();

		try {
			UserposJava userposJava = dao.Buscar(6L);
			System.out.println(userposJava);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//Metodo para atualizar a base de dados do banco
	@Test
	public void initAtualizar() {

		try {

			UserPosDAO dao = new UserPosDAO();

			UserposJava objetoBanco = dao.Buscar(5L);
			
			objetoBanco.setNome("Nome Mudado com metodo atualizar");
			
			dao.atualizar(objetoBanco);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//metodo de delete
	@Test
	public void initDeletar() {
		
		try {
			UserPosDAO dao =  new UserPosDAO();
			dao.deletar(6l);
			
		}catch (Exception e) {
		e.printStackTrace();
		}
	}
}

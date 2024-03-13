package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.UserposJava;

//aqui sera feito os insert update e delete

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(UserposJava userposJava) throws SQLException {
		try {
			String sql = "insert into userposjava (nome, email) values (?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userposJava.getNome());
			insert.setString(2, userposJava.getEmail());
			insert.execute();
			connection.commit();// Salvar no banco de dados

		} catch (Exception e) {
			connection.rollback();// reverte operação
			e.printStackTrace();
		}
	}

	public void salvarTelefone(Telefone telefone) {
		try {

			String sql = "INSERT INTO telefoneuser (numero, tipo, usuariopessoa) VALUES (?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuario());
			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<UserposJava> listar() throws Exception {
		List<UserposJava> list = new ArrayList<UserposJava>();

		String sql = "select * from userposjava";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			UserposJava userposJava = new UserposJava();
			userposJava.setId(resultado.getLong("id"));
			userposJava.setNome(resultado.getString("nome"));
			userposJava.setEmail(resultado.getString("email"));

			list.add(userposJava);
		}

		return list;
	}

	public UserposJava Buscar(Long id) throws Exception {
		UserposJava retorno = new UserposJava();

		String sql = "select * from userposjava where id = " + id;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {// retornar apenas um ou nenhum
			UserposJava userposJava = new UserposJava();
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));

		}

		return retorno;
	}

	public List<BeanUserFone> listaUserFone(Long idUser) {

		List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();

		String sql = " select nome, numero, email from telefoneuser as fone ";
		sql += " inner join userposjava  as userp ";
		sql += " on fone.usuariopessoa = userp.id ";
		sql += " where userp.id = " + idUser;

		try {

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				BeanUserFone userFone = new BeanUserFone();
				userFone.setEmail(resultSet.getString("email"));
				userFone.setNumero(resultSet.getString("numero"));
				userFone.setNome(resultSet.getString("nome"));
				beanUserFones.add(userFone);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return beanUserFones;

	}

	public void atualizar(UserposJava userposJava) {

		try {

			String sql = "update userposjava set nome = ? where id = " + userposJava.getId();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userposJava.getNome());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void deletar(Long id) {
		try {

			String sql = "delete from userposjava where id = " + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void deleteFonesPorUser(Long idUser) {
		
		try {
	
		
		String sqlFone = "delete from telefoneuser where usuariopessoa  = " + idUser;
		String sqlUser = "delete  from userposjava where id = " + idUser;
		
		PreparedStatement preparedStatement = connection.prepareStatement(sqlFone);
		preparedStatement.executeUpdate();
		connection.commit();
		
		preparedStatement = connection.prepareStatement(sqlUser);
		preparedStatement.executeUpdate();
		connection.commit();
		
		}catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

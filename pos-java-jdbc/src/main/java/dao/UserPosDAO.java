package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.UserposJava;

	//aqui sera feito os insert update e delete

public class UserPosDAO {
	
	private Connection connection;
	
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar (UserposJava userposJava) throws SQLException {
		try {
		String sql = "insert into userposjava (id , nome, email) values (?, ?, ?)";
		PreparedStatement insert = connection.prepareStatement(sql);
		insert.setLong(1, userposJava.getId());
		insert.setString(2, userposJava.getNome());
		insert.setString(3, userposJava.getEmail());
		insert.execute();
		connection.commit();//Salvar no banco de dados
		
		}catch (Exception e) {
			connection.rollback();//reverte operação
			e.printStackTrace();
		}
	}

	public List<UserposJava> listar() throws Exception{
		List<UserposJava> list = new ArrayList<UserposJava>();
		
		String sql = "select * from userposjava" ;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			UserposJava userposJava = new UserposJava();
			userposJava.setId(resultado.getLong("id"));
			userposJava.setNome(resultado.getString("nome"));
			userposJava.setEmail(resultado.getString("email"));

			list.add(userposJava);
		}

		return list;
	}
	
}

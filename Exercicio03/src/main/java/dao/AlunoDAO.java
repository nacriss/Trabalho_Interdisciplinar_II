package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;


public class AlunoDAO extends DAO {
	
	public AlunoDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean insert(Aluno aluno) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO aluno (matricula, nome, sexo) "
				       + "VALUES ("+aluno.getMatricula()+ ", '" + aluno.getNome() + "', '"  
				       + aluno.getSexo()
				       + "')";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Aluno get(int matricula) {
		Aluno usuario = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM aluno WHERE matricula =" + matricula;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 usuario = new Aluno(rs.getInt("matricula"), rs.getString("nome"), rs.getString("sexo").charAt(0));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuario;
	}
	
	
	public List<Aluno> get() {
		return get("");
	}

	
	public List<Aluno> getOrderByMatricula() {
		return get("matricula");		
	}
	
	
	public List<Aluno> getOrderByNome() {
		return get("nome");		
	}
	
	
	public List<Aluno> getOrderBySexo() {
		return get("sexo");		
	}
	
	
	private List<Aluno> get(String orderBy) {	
	
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM aluno" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Aluno a = new Aluno(rs.getInt("matricula"), rs.getString("nome"), rs.getString("sexo").charAt(0));
	            alunos.add(a);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return alunos;
	}


	public List<Aluno> getSexoMasculino() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario WHERE usuario.sexo LIKE 'M'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Aluno a = new Aluno(rs.getInt("matricula"), rs.getString("nome"), rs.getString("sexo").charAt(0));
	            alunos.add(a);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return alunos;
	}
	
	
	public boolean update(Aluno aluno) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE aluno SET nome = '" + aluno.getNome() +  "', sexo = '" 
			+aluno.getSexo() + "'" + " WHERE matricula = " +aluno.getMatricula();
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int matricula) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM aluno WHERE matricula = " + matricula;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
/*	public boolean autenticar(String login, String senha) {
		boolean resp = false;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario WHERE login LIKE '" + login + "' AND senha LIKE '" + senha  + "'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			resp = rs.next();
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return resp;
	}
	*/	
}
package app;

import java.util.*;
import java.util.List;

import dao.AlunoDAO;
import model.Aluno;

public class Aplicacao {
	public static Scanner sc =new Scanner(System.in);
public static void main(String[] args) throws Exception {
		
		
		int opc;
		do {
			opc=opcao();
			switch(opc) {
			case 1: inserir();
			break;
			case 2: listar();
			break;
			case 3: atualizar();
			break;
			case 4: excluir();
			break;
			case 5: System.out.println("Programa encerrado.");
			break;
			}
		}while(opc!=5);
	}

	public static int opcao(){
		int opcao;
		boolean erro;
		do {
			System.out.println("Digite a opcao:");
			System.out.println("1) Inserir");
			System.out.println("2) Listar");
			System.out.println("3)Atualizar");
			System.out.println("4)Excluir");
			System.out.println("5)sair");
			opcao = sc.nextInt();
			erro=opcao<1&&opcao>5;
			if(erro)System.out.println("Opcao invalida, tente outra opcao!!");
		}while(erro);
		return opcao;
	}
	
	public static void inserir() {
		AlunoDAO alunoDAO = new AlunoDAO();
		System.out.println("\n\n==== Inserir aluno === ");
		System.out.print("Digite matricula: ");
		int matricula = sc.nextInt();
		System.out.print("Digite nome: ");
		String nome = sc.next();
		System.out.print("Digite sexo: ");
		char sexo= sc.next().charAt(0);
		Aluno aluno = new Aluno(matricula, nome, sexo);
		if(alunoDAO.insert(aluno) == true) {
			
				System.out.println("Inserção com sucesso -> " + alunoDAO.toString());
			
		}
	}
		
		public static void listar() {
		AlunoDAO alunoDAO = new AlunoDAO();
		System.out.println("\n\n==== Mostrar Alunos === ");
		List<Aluno> alunos = alunoDAO.getOrderByMatricula();
		for (Aluno c: alunos) {
			System.out.println(c.toString());
		}
	}
		
		public static void excluir() {
		AlunoDAO alunoDAO = new AlunoDAO();
		System.out.println("\n\n==== Excluir Aluno === ");
		listar();
		System.out.print("Digite a matricula do aluno que deseja apagar:");
		int matricula = sc.nextInt();
		alunoDAO.delete(matricula);
		
	}
		public static void atualizar() {
			AlunoDAO alunoDAO = new AlunoDAO();

			System.out.println("\n\n==== Atualizar Aluno === ");
			listar();
			System.out.print("Digite a matricula do aluno: ");
			int matricula=sc.nextInt();
			System.out.print("Digite o nome: ");
			String nome=sc.next();
			System.out.print("Digite o sexo: ");
			char sexo =sc.next().charAt(0);

			Aluno aluno = new Aluno(matricula,nome,sexo);
			alunoDAO.update(aluno);
		}
}

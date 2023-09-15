package service;

import java.util.Scanner;

import java.io.File;

import java.util.List;
import dao.AlunoDAO;
import model.Aluno;
import spark.Request;
import spark.Response;


public class AlunoService {

	private AlunoDAO alunoDAO = new AlunoDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_MATRICULA = 1;
	private final int FORM_ORDERBY_NOME = 2;
	private final int FORM_ORDERBY_SEXO = 3;
	
	
	public AlunoService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Aluno(), FORM_ORDERBY_MATRICULA);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Aluno(), orderBy);
	}

	
	public void makeForm(int tipo, Aluno aluno, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umAluno = "";
		if(tipo != FORM_INSERT) {
			umAluno += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umAluno += "\t\t<tr>";
			umAluno += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/aluno/list/1\">Novo Aluno</a></b></font></td>";
			umAluno += "\t\t</tr>";
			umAluno += "\t</table>";
			umAluno += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/aluno/";
			String name, buttonLabel;
			int matricula;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Aluno";
				//matricula = "0001...";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + aluno.getMatricula();
				name = "Atualizar Aluno (Nome: " + aluno.getNome() + ")";
				matricula = aluno.getMatricula();
				buttonLabel = "Atualizar";
			}
			umAluno += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umAluno += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umAluno += "\t\t<tr>";
			umAluno += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umAluno += "\t\t</tr>";
			umAluno += "\t\t<tr>";
			umAluno += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umAluno += "\t\t</tr>";
			umAluno += "\t\t<tr>";
			umAluno += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\""+ aluno.getNome() +"\"></td>";
			umAluno += "\t\t\t<td>Matricula: <input class=\"input--register\" type=\"text\" name=\"matricula\" value=\"0"+ aluno.getMatricula() +"\"></td>";
			umAluno += "\t\t\t<td>Sexo: <input class=\"input--register\" type=\"text\" name=\"sexo\" value=\"F/M"+ aluno.getSexo() +"\"></td>";
			umAluno += "\t\t</tr>";
			umAluno += "\t\t<tr>";
			umAluno += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umAluno += "\t\t</tr>";
			umAluno += "\t</table>";
			umAluno += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umAluno += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umAluno += "\t\t<tr>";
			umAluno += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Aluno (Matricula " + aluno.getMatricula() + ")</b></font></td>";
			umAluno += "\t\t</tr>";
			umAluno += "\t\t<tr>";
			umAluno += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umAluno += "\t\t</tr>";
			umAluno += "\t\t<tr>";
			umAluno += "\t\t\t<td>&nbsp;Nome: "+ aluno.getNome() +"</td>";
			umAluno += "\t\t\t<td>Matricula: "+ aluno.getMatricula() +"</td>";
			umAluno += "\t\t\t<td>Sexo: "+ aluno.getSexo() +"</td>";
			umAluno += "\t\t</tr>";
			umAluno += "\t\t<tr>";
		//	umAluno += "\t\t\t<td>&nbsp;Data de fabricação: "+ produto.getDataFabricacao().toString() + "</td>";
		//	umAluno += "\t\t\t<td>Data de validade: "+ produto.getDataValidade().toString() + "</td>";
			umAluno += "\t\t\t<td>&nbsp;</td>";
			umAluno += "\t\t</tr>";
			umAluno += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-ALUNO>", umAluno);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Alunos</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/aluno/list/" + FORM_ORDERBY_MATRICULA + "\"><b>Matricula</b></a></td>\n" +
        		"\t<td><a href=\"/aluno/list/" + FORM_ORDERBY_NOME + "\"><b>Nome</b></a></td>\n" +
        		"\t<td><a href=\"/aluno/list/" + FORM_ORDERBY_SEXO + "\"><b>Sexo</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Aluno> alunos;
		if (orderBy == FORM_ORDERBY_MATRICULA) {                 	alunos = alunoDAO.getOrderByMatricula();
		} else if (orderBy == FORM_ORDERBY_NOME) {		alunos = alunoDAO.getOrderByNome();
		} else if (orderBy == FORM_ORDERBY_SEXO) {			alunos = alunoDAO.getOrderBySexo();
		} else {											alunos = alunoDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Aluno p : alunos) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getMatricula() + "</td>\n" +
            		  "\t<td>" + p.getNome() + "</td>\n" +
            		  "\t<td>" + p.getSexo() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/aluno/" + p.getMatricula() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/aluno/update/" + p.getMatricula() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteAluno('" + p.getMatricula() + "', '" + p.getNome() + "', '" + p.getSexo() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-ALUNO>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String nome = request.queryParams("nome");
		char sexo = request.queryParams("sexo").charAt(0);
		int matricula = Integer.parseInt(request.queryParams("matricula"));
		//int quantidade = Integer.parseInt(request.queryParams("quantidade"));
		//LocalDateTime dataFabricacao = LocalDateTime.parse(request.queryParams("dataFabricacao"));
		//LocalDate dataValidade = LocalDate.parse(request.queryParams("dataValidade"));
		
		String resp = "";
		
		Aluno aluno = new Aluno(matricula, nome,sexo); //quantidade, dataFabricacao, dataValidade
		
		if(alunoDAO.insert(aluno) == true) {
            resp = "Aluno (" + nome + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Aluno (" + nome + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int matricula = Integer.parseInt(request.params(":Matricula"));		
		Aluno aluno = (Aluno) alunoDAO.get(matricula);
		
		if (aluno != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, aluno, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Aluno " + matricula + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int matricula = Integer.parseInt(request.params(":matricula"));		
		Aluno aluno = (Aluno) alunoDAO.get(matricula);
		
		if (aluno != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, aluno, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Aluno " + matricula + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int matricula = Integer.parseInt(request.params(":matricula"));
		Aluno aluno = alunoDAO.get(matricula);
        String resp = "";       

        if (aluno != null) {
        	aluno.setNome(request.queryParams("nome"));
        	aluno.setSexo(request.queryParams("sexo").charAt(0));
        	//aluno.setQuantidade(Integer.parseInt(request.queryParams("quantidade")));
        	//aluno.setDataFabricacao(LocalDateTime.parse(request.queryParams("dataFabricacao")));
        	//aluno.setDataValidade(LocalDate.parse(request.queryParams("dataValidade")));
        	alunoDAO.update(aluno);
        	response.status(200); // success
            resp = "Aluno (Matricula " + aluno.getMatricula() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Aluno (Matricula \" + aluno.getMatricula() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int matricula = Integer.parseInt(request.params(":matricula"));
        Aluno aluno = alunoDAO.get(matricula);
        String resp = "";       

        if (aluno != null) {
            alunoDAO.delete(matricula);
            response.status(200); // success
            resp = "Aluno (" + matricula + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Aluno (" + matricula + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}
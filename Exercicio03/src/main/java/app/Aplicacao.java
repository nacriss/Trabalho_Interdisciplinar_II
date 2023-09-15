package app;

import java.util.*;


import static spark.Spark.*;
import service.AlunoService;

public class Aplicacao {
	public static Scanner sc =new Scanner(System.in);
	private static AlunoService alunoService = new AlunoService();

public static void main(String[] args) throws Exception {
		

	port(4568);
    
    staticFiles.location("/public");
    
    post("/aluno/insert", (request, response) -> alunoService.insert(request, response));

    get("/aluno/:matricula", (request, response) -> alunoService.get(request, response));
    
    get("/aluno/list/:orderby", (request, response) -> alunoService.getAll(request, response));

    get("/aluno/update/:matricula", (request, response) -> alunoService.getToUpdate(request, response));
    
    post("/aluno/update/:matricula", (request, response) -> alunoService.update(request, response));
       
    get("/aluno/delete/:matricula", (request, response) -> alunoService.delete(request, response));

}
}   


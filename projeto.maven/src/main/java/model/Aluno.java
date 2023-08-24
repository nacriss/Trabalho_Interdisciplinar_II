package model;

public class Aluno {
    private int matricula;
    private String nome;
    private char sexo;

    public Aluno() {
        this.matricula = -1;
        this.nome = "";
        this.sexo = '*';
    }

    public Aluno(int matricula, String nome, char sexo) {
        this.matricula = matricula;
        this.nome = nome;
        this.sexo = sexo;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Aluno [matricula=" + matricula + ", nome=" + nome + ", sexo=" + sexo + "]";
    }
}
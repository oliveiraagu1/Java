package pessoa;

import curso.Curso;

public class Aluno extends Pessoa {

    private Curso curso;

    public Aluno() {
        this("", "", new Curso());
    }

    public Aluno(String nome, String cpf, Curso curso) {
        super(nome, cpf);
        this.curso = curso;
        setEndereco("");
    }

    public String buscarDados() {
        return super.buscarDados() + "\nCurso: " + curso.getNomeCurso();
    }

    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}

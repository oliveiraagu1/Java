import curso.Curso;
import pessoa.Aluno;
import pessoa.Pessoa;
import pessoa.Professor;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Pessoa pessoa = new Pessoa("Ze", "123");
        Aluno aluno = new Aluno("João", "321", new Curso());
        Professor professor = new Professor();
        Professor professor2 = new Professor("Leo", "123", 1000.0, new ArrayList<>());

        Util.imprimirDadosDePessoa(pessoa);
        Util.imprimirDadosDePessoa(aluno);
        Util.imprimirDadosDePessoa(professor);
        Util.imprimirDadosDePessoa(professor2);
    }

}

package pessoa;

import java.util.ArrayList;
import java.util.List;

import curso.Disciplina;

public class Professor extends Pessoa {

    private Double salario;
    private List<Disciplina> disciplinas;

    public Professor() {
        super("","");
        disciplinas = new ArrayList<>();
    }

    public Professor(String nome, String cpf, Double salario, List<Disciplina> disciplinas) {
        super(nome, cpf);
        this.salario = salario;
        this.disciplinas = disciplinas;
    }

    public String buscarDados() {
		/*return "Nome: " + getNome() +
				"\nCpf: " + getCpf() +
				"\nEndereço: " + getEndereco() +
				"\nSalário: " + salario;*/
        return super.buscarDados() + "\nSalário: " + salario;
    }

    public Double getSalario() {
        return salario;
    }
    public void setSalario(Double salario) {
        this.salario = salario;
    }
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public Double calcularSalarioLiquido() {
        return salario - (salario * 8/100);
    }

    public Double calcularSalarioLiquido(Double bonus) {
        Double salarioBonificado = salario + bonus;
        return salarioBonificado - (salarioBonificado * 8/100);
    }

}

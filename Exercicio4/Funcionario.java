public class Funcionario {

    protected String nome;
    protected double salarioBase;

    public Funcionario(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    public String getNome() {
        return nome;
    }

    public double calcularSalario() {
        return salarioBase;
    }

    public String getTipo() {
        return "Funcionario";
    }

    public String exibirDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo: ").append(getTipo()).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append(String.format("Salario base: R$ %.2f%n", salarioBase));
        sb.append(String.format("Salario final: R$ %.2f", calcularSalario()));
        return sb.toString();
    }
}

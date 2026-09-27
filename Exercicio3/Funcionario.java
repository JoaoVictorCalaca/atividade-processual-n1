public class Funcionario {

    private String nome;
    private String cargo;
    private double salario;

    public Funcionario(String nome, String cargo, double salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public void aumentarSalario(double percentual) {
        salario += salario * (percentual / 100);
    }

    public double calcularSalarioAnual() {
        return salario * 12;
    }

    public String exibirDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Cargo: ").append(cargo).append("\n");
        sb.append(String.format("Salario mensal: R$ %.2f%n", salario));
        sb.append(String.format("Salario anual: R$ %.2f", calcularSalarioAnual()));
        return sb.toString();
    }
}

public class Gerente extends Funcionario {

    private static final double PERCENTUAL_BONUS = 0.20;

    public Gerente(String nome, double salarioBase) {
        super(nome, salarioBase);
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (salarioBase * PERCENTUAL_BONUS);
    }

    @Override
    public String getTipo() {
        return "Gerente";
    }
}

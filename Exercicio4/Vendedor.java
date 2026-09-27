public class Vendedor extends Funcionario {

    private static final double PERCENTUAL_COMISSAO = 0.10;

    private double totalVendas;

    public Vendedor(String nome, double salarioBase, double totalVendas) {
        super(nome, salarioBase);
        this.totalVendas = totalVendas;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (totalVendas * PERCENTUAL_COMISSAO);
    }

    @Override
    public String getTipo() {
        return "Vendedor";
    }
}

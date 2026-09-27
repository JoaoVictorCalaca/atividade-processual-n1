public class ContaCorrente extends Conta {

    private double taxaManutencao;

    public ContaCorrente(int numero, String titular, double saldo, double taxaManutencao) {
        super(numero, titular, saldo);
        this.taxaManutencao = taxaManutencao;
    }

    @Override
    public double calcularSaldo() {
        return saldo - taxaManutencao;
    }

    @Override
    public String getTipo() {
        return "Conta Corrente";
    }
}

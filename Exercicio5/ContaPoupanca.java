public class ContaPoupanca extends Conta {

    private double percentualRendimento;

    public ContaPoupanca(int numero, String titular, double saldo, double percentualRendimento) {
        super(numero, titular, saldo);
        this.percentualRendimento = percentualRendimento;
    }

    @Override
    public double calcularSaldo() {
        return saldo + (saldo * percentualRendimento);
    }

    @Override
    public String getTipo() {
        return "Conta Poupanca";
    }
}

public class Conta {

    protected int numero;
    protected String titular;
    protected double saldo;

    public Conta(int numero, String titular, double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        saldo -= valor;
    }

    public double calcularSaldo() {
        return saldo;
    }

    public String getTipo() {
        return "Conta";
    }

    public String exibirDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo: ").append(getTipo()).append("\n");
        sb.append("Numero: ").append(numero).append("\n");
        sb.append("Titular: ").append(titular).append("\n");
        sb.append(String.format("Saldo calculado: R$ %.2f", calcularSaldo()));
        return sb.toString();
    }
}

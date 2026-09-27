import java.util.Scanner;

public class sistemavendas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Quantidade de vendas: ");
        int quantidadeVendas = scanner.nextInt();

        double[] vendas = new double[quantidadeVendas];

        double totalVendido = 0;
        double maiorVenda = Double.MIN_VALUE;
        double menorVenda = Double.MAX_VALUE;
        int vendasAcimaDe500 = 0;

        for (int i = 0; i < quantidadeVendas; i++) {
            System.out.print("Valor da venda " + (i + 1) + ": ");
            double valorVenda = scanner.nextDouble();
            vendas[i] = valorVenda;

            totalVendido += valorVenda;

            if (valorVenda > maiorVenda) {
                maiorVenda = valorVenda;
            }

            if (valorVenda < menorVenda) {
                menorVenda = valorVenda;
            }

            if (valorVenda > 500.00) {
                vendasAcimaDe500++;
            }
        }

        double mediaVendas = totalVendido / quantidadeVendas;
        double comissao = calcularComissao(totalVendido);

        System.out.println("\n===== Resumo das Vendas =====");
        System.out.printf("Valor total vendido: R$ %.2f%n", totalVendido);
        System.out.printf("Maior venda: R$ %.2f%n", maiorVenda);
        System.out.printf("Menor venda: R$ %.2f%n", menorVenda);
        System.out.printf("Média das vendas: R$ %.2f%n", mediaVendas);
        System.out.println("Vendas acima de R$ 500,00: " + vendasAcimaDe500);
        System.out.printf("Comissão do vendedor: R$ %.2f%n", comissao);

        scanner.close();
    }
    
    public static double calcularComissao(double totalVendido) {
        double percentual;

        if (totalVendido <= 1000.00) {
            percentual = 0.03;
        } else if (totalVendido <= 5000.00) {
            percentual = 0.05;
        } else {
            percentual = 0.08;
        }

        return totalVendido * percentual;
    }
}

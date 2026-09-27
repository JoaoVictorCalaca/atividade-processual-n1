import java.util.Scanner;

public class caixaeletronica {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Valor do saque: ");
        int valorSaque = scanner.nextInt();

        int[] notasDisponiveis = {200, 100, 50, 20, 10, 5, 2};

        int valorRestante = valorSaque;

        System.out.println("\nNotas:");

        for (int i = 0; i < notasDisponiveis.length; i++) {
            int notaAtual = notasDisponiveis[i];
            int quantidadeDeNotas = 0;

            while (valorRestante >= notaAtual) {
                valorRestante = valorRestante - notaAtual;
                quantidadeDeNotas++;
            }

            if (quantidadeDeNotas > 0) {
                System.out.println(quantidadeDeNotas + " x R$ " + notaAtual);
            }
        }

        scanner.close();
    }
} 

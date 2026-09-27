import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Conta> contas = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static int proximoNumero = 1001;

    public static void main(String[] args) {

        cadastrarContasIniciais();
        limparTela();

        int opcao;
        do {
            opcao = exibirMenu();
            String mensagem;

            switch (opcao) {
                case 1:
                    mensagem = cadastrarContaCorrente();
                    break;
                case 2:
                    mensagem = cadastrarContaPoupanca();
                    break;
                case 3:
                    mensagem = depositar();
                    break;
                case 4:
                    mensagem = sacar();
                    break;
                case 5:
                    mensagem = exibirConta();
                    break;
                case 6:
                    mensagem = listarTodas();
                    break;
                case 0:
                    mensagem = "Encerrando o Sistema de Contas Bancarias.";
                    break;
                default:
                    mensagem = "Opcao invalida. Tente novamente.";
            }

            limparTela();
            System.out.println(mensagem);
            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }

    private static int exibirMenu() {
        System.out.println("=== SISTEMA DE CONTAS BANCARIAS ===");
        System.out.println("1 - Criar conta corrente");
        System.out.println("2 - Criar conta poupanca");
        System.out.println("3 - Depositar");
        System.out.println("4 - Sacar");
        System.out.println("5 - Exibir dados de uma conta");
        System.out.println("6 - Listar todas as contas");
        System.out.println("0 - Sair");
        System.out.print("Digite a opcao desejada: ");

        String entrada = scanner.nextLine();

        try {
            return Integer.parseInt(entrada.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void cadastrarContasIniciais() {
        contas.add(new ContaCorrente(proximoNumero++, "Ana Souza", 1000.00, 20.00));
        contas.add(new ContaCorrente(proximoNumero++, "Bruno Lima", 2500.00, 15.00));
        contas.add(new ContaPoupanca(proximoNumero++, "Carla Dias", 1000.00, 0.05));
        contas.add(new ContaPoupanca(proximoNumero++, "Diego Alves", 3000.00, 0.03));
    }

    private static String cadastrarContaCorrente() {
        System.out.println("--- Nova Conta Corrente ---");

        String titular = lerTexto("Titular: ");

        System.out.print("Saldo inicial: ");
        double saldo = lerDouble();

        System.out.print("Taxa de manutencao: ");
        double taxa = lerDouble();

        ContaCorrente nova = new ContaCorrente(proximoNumero++, titular, saldo, taxa);
        contas.add(nova);

        return "Conta corrente criada com sucesso!\n\n" + nova.exibirDados();
    }

    private static String cadastrarContaPoupanca() {
        System.out.println("--- Nova Conta Poupanca ---");

        String titular = lerTexto("Titular: ");

        System.out.print("Saldo inicial: ");
        double saldo = lerDouble();

        System.out.print("Percentual de rendimento (ex: 0.05 para 5%): ");
        double percentual = lerDouble();

        ContaPoupanca nova = new ContaPoupanca(proximoNumero++, titular, saldo, percentual);
        contas.add(nova);

        return "Conta poupanca criada com sucesso!\n\n" + nova.exibirDados();
    }

    private static String depositar() {
        Conta conta = selecionarConta();
        if (conta == null) {
            return "Operacao cancelada.";
        }

        System.out.print("Valor do deposito: ");
        double valor = lerDouble();

        conta.depositar(valor);

        return "Deposito realizado com sucesso!\n\n" + conta.exibirDados();
    }

    private static String sacar() {
        Conta conta = selecionarConta();
        if (conta == null) {
            return "Operacao cancelada.";
        }

        System.out.print("Valor do saque: ");
        double valor = lerDouble();

        conta.sacar(valor);

        return "Saque realizado com sucesso!\n\n" + conta.exibirDados();
    }

    private static String exibirConta() {
        Conta conta = selecionarConta();
        if (conta == null) {
            return "Operacao cancelada.";
        }

        return conta.exibirDados();
    }

    private static String listarTodas() {
        if (contas.isEmpty()) {
            return "Nenhuma conta cadastrada.";
        }

        StringBuilder sb = new StringBuilder("=== Todas as Contas ===\n\n");
        for (Conta c : contas) {
            sb.append(c.exibirDados()).append("\n-------------------------------\n");
        }

        return sb.toString();
    }

    private static Conta selecionarConta() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return null;
        }

        System.out.println();
        for (Conta c : contas) {
            System.out.println(c.getNumero() + " - " + c.getTitular() + " (" + c.getTipo() + ")");
        }
        System.out.print("Digite o numero da conta: ");

        int numero = lerInt();

        for (Conta c : contas) {
            if (c.getNumero() == numero) {
                return c;
            }
        }

        return null;
    }

    private static String lerTexto(String rotulo) {
        System.out.print(rotulo);
        String entrada = scanner.nextLine().trim();

        while (entrada.isEmpty()) {
            System.out.print("Este campo nao pode ficar em branco. " + rotulo);
            entrada = scanner.nextLine().trim();
        }

        return entrada;
    }

    private static double lerDouble() {
        while (true) {
            String entrada = scanner.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.print("Valor invalido. Digite um numero (ex: 1500.00): ");
            }
        }
    }

    private static int lerInt() {
        while (true) {
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.print("Valor invalido. Digite um numero inteiro: ");
            }
        }
    }

    private static void limparTela() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}

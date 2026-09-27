import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Funcionario> funcionarios = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        cadastrarFuncionariosIniciais();
        limparTela();

        int opcao;
        do {
            opcao = exibirMenu();
            String mensagem;

            switch (opcao) {
                case 1:
                    mensagem = cadastrarGerente();
                    break;
                case 2:
                    mensagem = cadastrarVendedor();
                    break;
                case 3:
                    mensagem = exibirFuncionario();
                    break;
                case 4:
                    mensagem = listarTodos();
                    break;
                case 0:
                    mensagem = "Encerrando o Sistema de Funcionarios.";
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
        System.out.println("=== FUNCIONARIOS COM HERANCA ===");
        System.out.println("1 - Cadastrar gerente");
        System.out.println("2 - Cadastrar vendedor");
        System.out.println("3 - Exibir dados de um funcionario");
        System.out.println("4 - Listar todos os funcionarios");
        System.out.println("0 - Sair");
        System.out.print("Digite a opcao desejada: ");

        String entrada = scanner.nextLine();

        try {
            return Integer.parseInt(entrada.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void cadastrarFuncionariosIniciais() {
        funcionarios.add(new Gerente("Ana Souza", 5000.00));
        funcionarios.add(new Vendedor("Bruno Lima", 2000.00, 8000.00));
        funcionarios.add(new Vendedor("Carla Dias", 1800.00, 3000.00));
    }

    private static String cadastrarGerente() {
        System.out.println("--- Cadastrar Gerente ---");

        String nome = lerTexto("Nome: ");

        System.out.print("Salario base: ");
        double salarioBase = lerDouble();

        Gerente novo = new Gerente(nome, salarioBase);
        funcionarios.add(novo);

        return "Gerente cadastrado com sucesso!\n\n" + novo.exibirDados();
    }

    private static String cadastrarVendedor() {
        System.out.println("--- Cadastrar Vendedor ---");

        String nome = lerTexto("Nome: ");

        System.out.print("Salario base: ");
        double salarioBase = lerDouble();

        System.out.print("Total de vendas: ");
        double totalVendas = lerDouble();

        Vendedor novo = new Vendedor(nome, salarioBase, totalVendas);
        funcionarios.add(novo);

        return "Vendedor cadastrado com sucesso!\n\n" + novo.exibirDados();
    }

    private static String exibirFuncionario() {
        if (funcionarios.isEmpty()) {
            return "Nenhum funcionario cadastrado.";
        }

        Funcionario funcionario = selecionarFuncionario();
        if (funcionario == null) {
            return "Operacao cancelada.";
        }

        return funcionario.exibirDados();
    }

    private static String listarTodos() {
        if (funcionarios.isEmpty()) {
            return "Nenhum funcionario cadastrado.";
        }

        StringBuilder sb = new StringBuilder("=== Todos os Funcionarios ===\n\n");
        for (Funcionario f : funcionarios) {
            sb.append(f.exibirDados()).append("\n-------------------------------\n");
        }

        return sb.toString();
    }

    private static Funcionario selecionarFuncionario() {
        System.out.println();
        for (int i = 0; i < funcionarios.size(); i++) {
            Funcionario f = funcionarios.get(i);
            System.out.println((i + 1) + " - " + f.getNome() + " (" + f.getTipo() + ")");
        }
        System.out.print("Selecione o numero do funcionario: ");

        int indice = lerInt() - 1;

        if (indice < 0 || indice >= funcionarios.size()) {
            return null;
        }

        return funcionarios.get(indice);
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

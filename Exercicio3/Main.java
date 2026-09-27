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
                    mensagem = cadastrarFuncionario();
                    break;
                case 2:
                    mensagem = aumentarSalario();
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
        System.out.println("=== SISTEMA DE FUNCIONARIOS ===");
        System.out.println("1 - Cadastrar funcionario");
        System.out.println("2 - Aumentar salario");
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
        funcionarios.add(new Funcionario("Ana Souza", "Analista", 3000.00));
        funcionarios.add(new Funcionario("Bruno Lima", "Desenvolvedor", 4500.00));
        funcionarios.add(new Funcionario("Carla Dias", "Gerente", 7000.00));
        funcionarios.add(new Funcionario("Diego Alves", "Estagiario", 1500.00));
        funcionarios.add(new Funcionario("Elisa Melo", "Coordenadora", 6000.00));
    }

    private static String cadastrarFuncionario() {
        System.out.println("--- Cadastrar Funcionario ---");

        String nome = lerTexto("Nome: ");
        String cargo = lerTexto("Cargo: ");

        System.out.print("Salario: ");
        double salario = lerDouble();

        Funcionario novo = new Funcionario(nome, cargo, salario);
        funcionarios.add(novo);

        return "Funcionario cadastrado com sucesso!\n\n" + novo.exibirDados();
    }

    private static String aumentarSalario() {
        if (funcionarios.isEmpty()) {
            return "Nenhum funcionario cadastrado.";
        }

        Funcionario funcionario = selecionarFuncionario();
        if (funcionario == null) {
            return "Operacao cancelada.";
        }

        System.out.print("Percentual de aumento (%): ");
        double percentual = lerDouble();

        funcionario.aumentarSalario(percentual);

        return "Aumento aplicado com sucesso!\n\n" + funcionario.exibirDados();
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
            System.out.println((i + 1) + " - " + funcionarios.get(i).getNome());
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

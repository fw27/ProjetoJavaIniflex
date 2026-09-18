package br.com.iniflex;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

// classe principal com menu interativo no terminal
public class Principal {

    private static final List<Funcionario> funcionarios = new ArrayList<>();
    private static Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");
    private static final DecimalFormat DF_MOEDA = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.forLanguageTag("pt-BR")));
    private static final DecimalFormat DF_QTD = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.forLanguageTag("pt-BR")));

    public static void main(String[] args) {
        // ajustando a saida pra utf-8 pra exibir acentos corretamente no console do windows
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        } catch (Exception ignored) {
        }

        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        System.out.println("==========================================================");
        System.out.println("      bem-vindo ao sistema de funcionarios da iniflex     ");
        System.out.println("==========================================================");

        while (executando) {
            exibirMenu();
            System.out.print("escolha uma opcao: ");

            if (!scanner.hasNextLine()) {
                break;
            }

            String opcao = scanner.nextLine().trim();
            if (opcao.isEmpty()) {
                continue;
            }

            switch (opcao) {
                case "1" -> acao31InserirFuncionarios();
                case "2" -> acao32RemoverJoao();
                case "3" -> acao33ImprimirFuncionarios();
                case "4" -> acao34AplicarAumento();
                case "5" -> acao35AgruparPorFuncao();
                case "6" -> acao36ImprimirAgrupadosPorFuncao();
                case "7" -> acao38ImprimirAniversariantes();
                case "8" -> acao39ImprimirMaiorIdade();
                case "9" -> acao310ImprimirOrdemAlfabetica();
                case "10" -> acao311ImprimirTotalSalarios();
                case "11" -> acao312ImprimirSalariosMinimos();
                case "12" -> executarFluxoCompleto();
                case "0" -> {
                    System.out.println("\nfechando o sistema... muito obrigado!");
                    executando = false;
                }
                default -> System.out.println("\nopcao invalida! digite um numero valido do menu.");
            }

            if (executando) {
                System.out.println("\npressione enter pra voltar ao menu...");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        }

        scanner.close();
    }

    // exibe as opcoes do menu no terminal
    private static void exibirMenu() {
        System.out.println("\n-------------------- MENU PRINCIPAL --------------------");
        System.out.println("1  - [3.1] inserir funcionarios da tabela");
        System.out.println("2  - [3.2] remover o funcionario 'joao'");
        System.out.println("3  - [3.3] imprimir todos os funcionarios (formatado)");
        System.out.println("4  - [3.4] aplicar 10% de aumento salarial");
        System.out.println("5  - [3.5] agrupar funcionarios por funcao em um map");
        System.out.println("6  - [3.6] imprimir funcionarios agrupados por funcao");
        System.out.println("7  - [3.8] imprimir aniversariantes dos meses 10 e 12");
        System.out.println("8  - [3.9] imprimir funcionario com maior idade");
        System.out.println("9  - [3.10] imprimir funcionarios em ordem alfabetica");
        System.out.println("10 - [3.11] imprimir total dos salarios");
        System.out.println("11 - [3.12] imprimir quantidade de salarios minimos");
        System.out.println("12 - [completo] executar todos os requisitos de uma vez");
        System.out.println("0  - sair do programa");
        System.out.println("--------------------------------------------------------");
    }

    // 3.1 - insere os 10 funcionarios na ordem da tabela
    private static void acao31InserirFuncionarios() {
        funcionarios.clear();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        System.out.println("\n[sucesso] 10 funcionarios cadastrados na lista com sucesso!");
    }

    // 3.2 - remove o joao da lista
    private static void acao32RemoverJoao() {
        if (funcionarios.isEmpty()) {
            System.out.println("\n[aviso] a lista esta vazia! cadastre os funcionarios na opcao 1 primeiro.");
            return;
        }
        boolean removido = funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));
        if (removido) {
            System.out.println("\n[sucesso] funcionario 'joao' removido da lista!");
        } else {
            System.out.println("\n[aviso] o funcionario 'joao' nao foi encontrado ou ja foi removido.");
        }
    }

    // 3.3 - imprime todos os funcionarios formatados
    private static void acao33ImprimirFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("\n[aviso] a lista esta vazia! cadastre os funcionarios na opcao 1 primeiro.");
            return;
        }
        System.out.println("\n=== lista de funcionarios cadastrados ===");
        for (Funcionario f : funcionarios) {
            System.out.println(f);
        }
    }

    // 3.4 - aplica aumento de 10%
    private static void acao34AplicarAumento() {
        if (funcionarios.isEmpty()) {
            System.out.println("\n[aviso] a lista esta vazia! cadastre os funcionarios na opcao 1 primeiro.");
            return;
        }
        for (Funcionario f : funcionarios) {
            BigDecimal novoSalario = f.getSalario().multiply(new BigDecimal("1.10")).setScale(2, RoundingMode.HALF_UP);
            f.setSalario(novoSalario);
        }
        System.out.println("\n[sucesso] aumento de 10% aplicado com sucesso no salario de todos!");
    }

    // 3.5 - agrupa os funcionarios por funcao em um map
    private static void acao35AgruparPorFuncao() {
        if (funcionarios.isEmpty()) {
            System.out.println("\n[aviso] a lista esta vazia! cadastre os funcionarios na opcao 1 primeiro.");
            return;
        }
        funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        System.out.println("\n[sucesso] funcionarios agrupados por funcao em um Map<String, List<Funcionario>>!");
    }

    // 3.6 - imprime os funcionarios agrupados por funcao
    private static void acao36ImprimirAgrupadosPorFuncao() {
        if (funcionariosPorFuncao == null || funcionariosPorFuncao.isEmpty()) {
            System.out.println("\n[aviso] nenhum agrupamento encontrado! execute a opcao 5 [3.5] primeiro para agrupar.");
            return;
        }
        System.out.println("\n=== funcionarios agrupados por funcao ===");
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("\nfuncao: " + entry.getKey());
            for (Funcionario f : entry.getValue()) {
                System.out.println("  - " + f.getNome() + " | salario: R$ " + f.getSalarioFormatado());
            }
        }
    }

    // 3.8 - aniversariantes dos meses 10 e 12
    private static void acao38ImprimirAniversariantes() {
        if (funcionarios.isEmpty()) {
            System.out.println("\n[aviso] a lista esta vazia! cadastre os funcionarios na opcao 1 primeiro.");
            return;
        }
        System.out.println("\n=== aniversariantes de outubro (10) e dezembro (12) ===");
        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .toList();

        if (aniversariantes.isEmpty()) {
            System.out.println("nenhum funcionario faz aniversario nesses meses.");
        } else {
            for (Funcionario f : aniversariantes) {
                System.out.println(f.getNome() + " faz aniversario em " + f.getDataNascimentoFormatada() + " (" + f.getFuncao() + ")");
            }
        }
    }

    // 3.9 - funcionario mais velho
    private static void acao39ImprimirMaiorIdade() {
        if (funcionarios.isEmpty()) {
            System.out.println("\n[aviso] a lista esta vazia! cadastre os funcionarios na opcao 1 primeiro.");
            return;
        }
        Funcionario maisVelho = Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        System.out.println("\n=== funcionario com maior idade ===");
        System.out.println("nome: " + maisVelho.getNome() + " | idade: " + maisVelho.getIdade() + " anos");
    }

    // 3.10 - ordem alfabetica
    private static void acao310ImprimirOrdemAlfabetica() {
        if (funcionarios.isEmpty()) {
            System.out.println("\n[aviso] a lista esta vazia! cadastre os funcionarios na opcao 1 primeiro.");
            return;
        }
        List<Funcionario> ordenados = new ArrayList<>(funcionarios);
        ordenados.sort(Comparator.comparing(Funcionario::getNome));

        System.out.println("\n=== funcionarios em ordem alfabetica ===");
        for (Funcionario f : ordenados) {
            System.out.println(f);
        }
    }

    // 3.11 - total dos salarios
    private static void acao311ImprimirTotalSalarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("\n[aviso] a lista esta vazia! cadastre os funcionarios na opcao 1 primeiro.");
            return;
        }
        BigDecimal total = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\n=== total dos salarios ===");
        System.out.println("total: R$ " + DF_MOEDA.format(total));
    }

    // 3.12 - quantidade de salarios minimos
    private static void acao312ImprimirSalariosMinimos() {
        if (funcionarios.isEmpty()) {
            System.out.println("\n[aviso] a lista esta vazia! cadastre os funcionarios na opcao 1 primeiro.");
            return;
        }
        System.out.println("\n=== quantidade de salarios minimos (base R$ 1.212,00) ===");
        for (Funcionario f : funcionarios) {
            BigDecimal qtd = f.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + " ganha " + DF_QTD.format(qtd) + " salarios minimos");
        }
    }

    // executa o fluxo completo do teste direto
    private static void executarFluxoCompleto() {
        System.out.println("\n>>> EXECUTANDO TODOS OS REQUISITOS DE UMA VEZ <<<");
        acao31InserirFuncionarios();
        acao32RemoverJoao();
        acao33ImprimirFuncionarios();
        acao34AplicarAumento();
        acao35AgruparPorFuncao();
        acao36ImprimirAgrupadosPorFuncao();
        acao38ImprimirAniversariantes();
        acao39ImprimirMaiorIdade();
        acao310ImprimirOrdemAlfabetica();
        acao311ImprimirTotalSalarios();
        acao312ImprimirSalariosMinimos();
        System.out.println("\n>>> FIM DA EXECUCAO COMPLETA <<<");
    }
}

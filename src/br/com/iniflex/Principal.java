package br.com.iniflex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// classe principal pra rodar o fluxo do teste
public class Principal {

    public static void main(String[] args) {
        // 3.1 - inserindo os funcionarios na lista seguindo a ordem da tabela
        List<Funcionario> funcionarios = new ArrayList<>();
        inserirFuncionarios(funcionarios);

        // 3.2 - removendo o joao da lista conforme pedido
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        // 3.3 - imprimindo todos os funcionarios com formatacao pt-br (dd/mm/aaaa e separadores de milhar/decimal)
        System.out.println("=== 3.3 - lista de funcionarios cadastrados (sem o joao) ===");
        imprimirFuncionarios(funcionarios);

        // 3.4 - aplicando o aumento de 10% pra todo mundo e atualizando a lista
        for (Funcionario f : funcionarios) {
            BigDecimal novoSalario = f.getSalario().multiply(new BigDecimal("1.10")).setScale(2, java.math.RoundingMode.HALF_UP);
            f.setSalario(novoSalario);
        }

        System.out.println("\n=== 3.4 - lista apos aumento salarial de 10% ===");
        imprimirFuncionarios(funcionarios);

        // 3.5 - agrupando os funcionarios por funcao usando map e collectors
        java.util.Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(java.util.stream.Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - imprimindo os funcionarios agrupados por cada funcao
        System.out.println("\n=== 3.6 - funcionarios agrupados por funcao ===");
        for (java.util.Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("\nfuncao: " + entry.getKey());
            for (Funcionario f : entry.getValue()) {
                System.out.println("  - " + f.getNome() + " | Salario: R$ " + f.getSalarioFormatado());
            }
        }

        // 3.8 - imprimindo aniversariantes dos meses 10 e 12 (corrigido: usando || em vez de &&)
        System.out.println("\n=== 3.8 - aniversariantes de outubro (10) e dezembro (12) ===");
        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .toList();

        if (aniversariantes.isEmpty()) {
            System.out.println("nenhum funcionario encontrado");
        } else {
            for (Funcionario f : aniversariantes) {
                System.out.println(f.getNome() + " faz aniversario em " + f.getDataNascimentoFormatada());
            }
        }

        // 3.9 - pegando o funcionario com maior idade (quem nasceu antes tem a menor data)
        System.out.println("\n=== 3.9 - funcionario com maior idade ===");
        Funcionario maisVelho = java.util.Collections.min(funcionarios, java.util.Comparator.comparing(Funcionario::getDataNascimento));
        System.out.println("nome: " + maisVelho.getNome() + " | idade: " + maisVelho.getIdade() + " anos");

        // 3.10 - imprimindo os funcionarios por ordem alfabetica
        System.out.println("\n=== 3.10 - funcionarios em ordem alfabetica ===");
        List<Funcionario> ordenadosPorNome = new ArrayList<>(funcionarios);
        ordenadosPorNome.sort(java.util.Comparator.comparing(Funcionario::getNome));
        imprimirFuncionarios(ordenadosPorNome);

        // 3.11 - calculando o total de salarios de todos os funcionarios
        System.out.println("\n=== 3.11 - total dos salarios ===");
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        java.text.DecimalFormat dfMoeda = new java.text.DecimalFormat("#,##0.00", new java.text.DecimalFormatSymbols(java.util.Locale.forLanguageTag("pt-BR")));
        System.out.println("total dos salarios: R$ " + dfMoeda.format(totalSalarios));

        // 3.12 - calculando quantos salarios minimos cada um ganha (salario minimo = R$ 1212,00)
        System.out.println("\n=== 3.12 - quantidade de salarios minimos por funcionario ===");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        java.text.DecimalFormat dfQtd = new java.text.DecimalFormat("#,##0.00", new java.text.DecimalFormatSymbols(java.util.Locale.forLanguageTag("pt-BR")));
        for (Funcionario f : funcionarios) {
            // resolvido: passando escala de 2 casas decimais e arredondamento half_up
            BigDecimal qtd = f.getSalario().divide(salarioMinimo, 2, java.math.RoundingMode.HALF_UP);
            System.out.println(f.getNome() + " ganha " + dfQtd.format(qtd) + " salarios minimos");
        }
    }

    // metodo pra imprimir a lista de funcionarios formatadinha
    private static void imprimirFuncionarios(List<Funcionario> lista) {
        for (Funcionario f : lista) {
            System.out.println(f);
        }
    }

    // metodo pra popular a lista com os dados passados no enunciado
    private static void inserirFuncionarios(List<Funcionario> lista) {
        lista.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        lista.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        lista.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        lista.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        lista.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        lista.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        lista.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        lista.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        lista.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        lista.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
    }
}

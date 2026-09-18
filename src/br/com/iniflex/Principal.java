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

        System.out.println("=== 3.1 e 3.2 - funcionarios inseridos e joao removido com sucesso ===");
        System.out.println("quantidade atual de funcionarios na lista: " + funcionarios.size());
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

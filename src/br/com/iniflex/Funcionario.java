package br.com.iniflex;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// classe funcionario herdando tudo de pessoa e adicionando salario e funcao
public class Funcionario extends Pessoa {

    private BigDecimal salario;
    private String funcao;

    // formatadores pra reaproveitar na exibicao formatada
    private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormat FORMATADOR_MOEDA = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.forLanguageTag("pt-BR")));

    // construtor chamando o super pra inicializar nome e data na classe pai
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    // devolve a data de nascimento bonitinha no formato dd/mm/aaaa
    public String getDataNascimentoFormatada() {
        if (getDataNascimento() == null) {
            return "";
        }
        return getDataNascimento().format(FORMATADOR_DATA);
    }

    // devolve o salario com separador de milhar como ponto e decimal como virgula
    public String getSalarioFormatado() {
        if (salario == null) {
            return "0,00";
        }
        return FORMATADOR_MOEDA.format(salario);
    }

    @Override
    public String toString() {
        return String.format("Nome: %-10s | Data Nasc: %s | Salario: R$ %10s | Funcao: %s",
                getNome(), getDataNascimentoFormatada(), getSalarioFormatado(), funcao);
    }
}

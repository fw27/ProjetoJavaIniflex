package br.com.iniflex;

import java.time.LocalDate;
import java.time.Period;

// classe base pra representar uma pessoa no sistema
public class Pessoa {

    private String nome;
    private LocalDate dataNascimento;

    // construtor padrao recebendo nome e data de nascimento
    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    // calcula a idade certinha em anos completos com base na data de hoje
    public int getIdade() {
        if (this.dataNascimento == null) {
            return 0;
        }
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}

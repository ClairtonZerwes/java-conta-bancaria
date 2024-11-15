import java.util.Random;
import java.util.Scanner;

public class ContaBancaria {
    private String nome;
    private int numeroConta;
    private double saldo;

    // Construtor da Classe
    public ContaBancaria(String nome, int numeroConta, double saldo) {
        this.nome = nome;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    /*public void setSaldo(double saldo) {
        this.saldo = saldo;
    }*/
    // O método setSaldo não é uma boa opção setar o saldo diretamente este deve ser privado,
    // somente podendo ser altarado internamentes na classe pelo métodos sacar, depositar e transferir.

    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito de R$" + valor + " realizado com sucesso!");
        System.out.println("Saldo atual: R$" + saldo);
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado com sucesso!");
            System.out.println("Saldo atual: R$" + saldo);
        } else {
            System.out.println("Saldo insuficiente para saque!");
        }
    }

    public void transferir(ContaBancaria contaDestino, double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            contaDestino.saldo += valor;
            System.out.println("Transferência de R$" + valor + " para a conta " + contaDestino.numeroConta + " realizada com sucesso!");
            System.out.println("Saldo atual: R$" + saldo);
        } else {
            System.out.println("Saldo insuficiente para transferência!");
        }
    }

    // Sobrescreve o método toString padrão do java para imprimir os dados da conta bancaria
    @Override
    public String toString() {
        return "---  Imprmindo Dados da Conta Bancária  ---\n" +
                "Número da Conta: " + numeroConta + "\n" +
                "Nome: " + nome + "\n" +
                "Saldo: R$" + saldo + "\n";
    }
}


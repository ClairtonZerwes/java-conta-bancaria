import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<ContaBancaria> listaContasBancarias = new ArrayList<>();

    private static void criarNovaContaBancaria(Scanner scanner) {
        System.out.println("Informe o nome do novo cliente: ");
        String nomeCliente = scanner.next();
        ContaBancaria contaCliente = null;
        int numeroConta = 0;
        boolean numeroDeContaValido = true;
        do {
            System.out.println("Informe o número da conta do novo cliente: ");
            numeroConta = scanner.nextInt();
            contaCliente = consultarContaPeloNumero(numeroConta);
            if (contaCliente != null) {
                System.out.println("O Número da Conta %d já existe informe um novo número para conta!".formatted(numeroConta));
                numeroDeContaValido = false;
            }
            else {
                numeroDeContaValido = true;
            }
            if ((numeroConta <= 0) || (Integer.toString(numeroConta).length() >= 7)) {
                System.out.println("O Número da conta deve ser maior que zero e ter 6 digitos.");
                numeroDeContaValido = false;
            }
        } while(!numeroDeContaValido);

        System.out.println("Informe o saldo incial da conta do novo cliente: ");
        double saldoCliente = scanner.nextDouble();

        ContaBancaria conta = new ContaBancaria(nomeCliente, numeroConta, saldoCliente);
        System.out.println(conta);
        listaContasBancarias.add(conta);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcaoMenu = 0;

        ContaBancaria conta1 = new ContaBancaria("João da Silva", 123456, 1000.0);
        listaContasBancarias.add(conta1);
        ContaBancaria conta2 = new ContaBancaria("Maria da Silva", 654321, 2000.0);
        listaContasBancarias.add(conta2);
        ContaBancaria conta3 = new ContaBancaria("Clairton Zerwes", 987654, 3000.0);
        listaContasBancarias.add(conta3);

        do {
            exibirMenu();
            opcaoMenu = scanner.nextInt();
            ContaBancaria contaCliente = null;

            switch (opcaoMenu) {
                case 0:
                    criarNovaContaBancaria(scanner);
                    break;
                case 1:
                    contaCliente = mensagemPadraoInformarNumeroConta(scanner, "consultar saldo");
                    if (contaCliente != null) {
                        System.out.println(contaCliente);
                    } else {
                        System.out.println("Conta informada não existe!");
                    }
                    break;
                case 2:
                    contaCliente = mensagemPadraoInformarNumeroConta(scanner, "depositar");
                    if (contaCliente != null) {
                        System.out.println("Digite o valor a depositar: ");
                        double valorDeposito = scanner.nextDouble();
                        if (valorDeposito < 0) {
                            System.out.println("Você deve informar um valor maior que zero para depositar");
                            exibirMenu();
                        }
                        contaCliente.depositar(valorDeposito);
                    } else {
                        System.out.println("Número de conta para depositar não existe!");
                    }
                    break;
                case 3:
                    contaCliente = mensagemPadraoInformarNumeroConta(scanner, "sacar");
                    if (contaCliente != null) {
                        System.out.println("Digite o valor a sacar: ");
                        double valorSaque = scanner.nextDouble();
                        if (valorSaque < 0) {
                            System.out.println("Você deve informar um valor maior que zero para sacar");
                            exibirMenu();
                        }
                        contaCliente.sacar(valorSaque);;
                    } else {
                        System.out.println("Conta para sacar não existe!");
                    }
                    break;
                case 4:
                    boolean existeDuasContas = true;
                    // Conta Origem
                    contaCliente = mensagemPadraoInformarNumeroConta(scanner, "origem da transferência.");
                    if (contaCliente != null) {
                        existeDuasContas = true;
                    } else {
                        System.out.println("A conta origem da transferência não existe!");
                        existeDuasContas = false;
                    }
                    // Conta Destino
                    ContaBancaria contaDestino = null;
                    contaDestino = mensagemPadraoInformarNumeroConta(scanner, "destino para transferir.");
                    if (contaDestino != null) {
                        existeDuasContas = true;
                    } else {
                        System.out.println("A conta destino para transferir não existe!");
                        existeDuasContas = false;
                    }
                    if (existeDuasContas) {
                        System.out.println("Digite o valor a transferir: ");
                        double valorTransferencia = scanner.nextDouble();
                        contaCliente.transferir(contaDestino, valorTransferencia);

                    } else {
                        System.out.println("As Contas de origem é ou destino não existem. Informe as duas contas válidas!!!");
                    }
                    break;
                case 5:
                    contaCliente = mensagemPadraoInformarNumeroConta(scanner, "consultar");
                    if (contaCliente != null) {
                        System.out.println(contaCliente);
                    }
                    break;
                case 6:
                    listarTodasAsContasDoBanco();
                    break;
                case 7:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    break;
                default:
                    System.out.println("Opção inválida! Informe um número entre 0 e 7.");
            }
        } while (opcaoMenu != 7);
    }

    private static ContaBancaria mensagemPadraoInformarNumeroConta(Scanner scanner, String descricaoMensagem) {
        System.out.printf("Digite o número da conta para %s: %n", descricaoMensagem);
        int numeroConta = scanner.nextInt();
        ContaBancaria contaCliente = consultarContaPeloNumero(numeroConta);
        return contaCliente;
    }

    public static ContaBancaria consultarContaPeloNumero(int contaNumero) {
        ContaBancaria conta = null;
        if (!listaContasBancarias.isEmpty()) {
            for (ContaBancaria c : listaContasBancarias) {
                if (c.getNumeroConta() == contaNumero) {
                    conta = c;
                    return conta;
                } else {
                    conta = null;
                }
            }
        }
        return conta;
    }

    private static void listarTodasAsContasDoBanco() {
        if (!listaContasBancarias.isEmpty()) {
            for (ContaBancaria conta : listaContasBancarias) {
                System.out.println(conta);
            }
        }
        else {
            System.out.println("Lista de Contas Bancárias Vazia!!!");
        }
    }
    private static void exibirMenu() {
        System.out.println("---  Menu Conta Bancária  ---");
        System.out.println("0 - Criar nova Conta Bancária");
        System.out.println("1 - Consultar Saldo");
        System.out.println("2 - Depositar");
        System.out.println("3 - Sacar");
        System.out.println("4 - Transferir");
        System.out.println("5 - Consultar uma Conta Bancária");
        System.out.println("6 - Mostrar todas as Contas Bancárias");
        System.out.println("7 - Sair");
        System.out.println("Digite a opção desejada: ");
    }
}
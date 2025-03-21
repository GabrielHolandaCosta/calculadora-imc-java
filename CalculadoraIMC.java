import java.util.Scanner;

public class CalculadoraIMC {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criação de um objeto Pessoa
        Pessoa pessoa = obterDadosPessoa(scanner);

        // Cálculo do IMC
        double imc = calcularIMC(pessoa);

        // Exibição do IMC e da categoria
        exibirResultado(imc);
        exibirCategoria(imc);
    }

    // Método para coletar os dados do usuário
    public static Pessoa obterDadosPessoa(Scanner scanner) {
        System.out.println("Calculadora de IMC - Índice de Massa Corporal");

        // Coleta de dados de forma robusta
        double peso = obterEntradaPeso(scanner);
        double altura = obterEntradaAltura(scanner);

        // Criação e retorno do objeto Pessoa
        return new Pessoa(peso, altura);
    }

    // Método para coletar o peso de forma válida
    public static double obterEntradaPeso(Scanner scanner) {
        double peso = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print("Digite o seu peso (em kg): ");
            if (scanner.hasNextDouble()) {
                peso = scanner.nextDouble();
                if (peso > 0) {
                    valido = true;
                } else {
                    System.out.println("Peso inválido! O peso deve ser maior que zero.");
                }
            } else {
                System.out.println("Entrada inválida! Por favor, insira um número válido para o peso.");
                scanner.next(); // Limpar o buffer do scanner
            }
        }
        return peso;
    }

    // Método para coletar a altura de forma válida (agora em centímetros)
    public static double obterEntradaAltura(Scanner scanner) {
        double altura = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print("Digite a sua altura (em centímetros): ");
            if (scanner.hasNextDouble()) {
                altura = scanner.nextDouble();
                if (altura > 0 && altura <= 300) { // Considerando altura máxima de 300 cm
                    valido = true;
                } else {
                    System.out.println("Altura inválida! A altura deve ser maior que zero e menor ou igual a 300 centímetros.");
                }
            } else {
                System.out.println("Entrada inválida! Por favor, insira um número válido para a altura.");
                scanner.next(); // Limpar o buffer do scanner
            }
        }
        return altura;
    }

    // Método para calcular o IMC
    public static double calcularIMC(Pessoa pessoa) {
        // Convertendo altura de centímetros para metros
        double alturaEmMetros = pessoa.getAltura() / 100;
        return pessoa.getPeso() / (alturaEmMetros * alturaEmMetros);
    }

    // Método para exibir o IMC formatado
    public static void exibirResultado(double imc) {
        System.out.println("\nSeu IMC é: " + String.format("%.2f", imc));
    }

    // Método para exibir a categoria do IMC
    public static void exibirCategoria(double imc) {
        if (imc < 18.5) {
            System.out.println("Categoria: Abaixo do peso");
        } else if (imc >= 18.5 && imc < 24.9) {
            System.out.println("Categoria: Peso normal");
        } else if (imc >= 25 && imc < 29.9) {
            System.out.println("Categoria: Sobrepeso");
        } else if (imc >= 30 && imc < 34.9) {
            System.out.println("Categoria: Obesidade Grau 1");
        } else if (imc >= 35 && imc < 39.9) {
            System.out.println("Categoria: Obesidade Grau 2");
        } else {
            System.out.println("Categoria: Obesidade Grau 3 (Obesidade Mórbida)");
        }
    }

    // Classe Pessoa para armazenar as informações do usuário
    public static class Pessoa {
        private double peso;
        private double altura;

        public Pessoa(double peso, double altura) {
            this.peso = peso;
            this.altura = altura;
        }

        public double getPeso() {
            return peso;
        }

        public double getAltura() {
            return altura;
        }
    }
}

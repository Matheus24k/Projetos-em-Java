import java.util.Scanner;
        public class Exzcc {
    public static void main(String[] args) {
        // Criando um scanner para entrada de dados
        Scanner scanner = new Scanner(System.in);

        // Solicitando e lendo o ano atual
        System.out.print("Digite o ano atual: ");
        int anoAtual = scanner.nextInt();

        // Solicitando e lendo o ano de nascimento
        System.out.print("Digite o ano de nascimento: ");
        int anoNascimento = scanner.nextInt();

        // Calculando a idade
        int idade = anoAtual - anoNascimento;

        // Exibindo a idade
        System.out.println("A idade atual é: " + idade + " anos.");

        // Fechando o scanner
        scanner.close();
    }
}
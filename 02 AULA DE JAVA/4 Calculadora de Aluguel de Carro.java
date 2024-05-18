import java.util.Scanner;

public class CalculoAluguelCarro {
    public static void main(String[] args) {
        // Taxas por dia e por quilômetro
        double taxaPorDia = 86.75;
        double taxaPorKm = 1.23;
        
        // Entrada do usuário: número de dias e quilômetros percorridos
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número de dias que você ficou com o carro:");
        int numDias = scanner.nextInt();
        System.out.println("Digite a quantidade de quilômetros percorridos:");
        double numKm = scanner.nextDouble();
        scanner.close();
        
        // Cálculo do valor do aluguel
        double valorAluguel = (taxaPorDia * numDias) + (taxaPorKm * numKm);
        
        // Exibição das taxas e do valor total a pagar
        System.out.println("\nTaxas:");
        System.out.println("Taxa por dia: R$" + taxaPorDia);
        System.out.println("Taxa por quilômetro: R$" + taxaPorKm);
        System.out.println("\nValor total a pagar: R$" + valorAluguel);
    }
}

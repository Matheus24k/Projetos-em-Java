//Bibliopteca de formatação de . e ,
import java.util.Locale;

//Bibliopteca de Scaner
import java.util.Scanner;
public class Exzcc {

    public static void main(String[]args) {

        //o locale é para localizar a formatação dos Estados Unidos sobre ponto e virgula
        Locale.setDefault(Locale.US);

        //Chamando o Scaner
        Scanner sc = new Scanner(System.in);

        //Declarando variaveis
        final double PI = 3.1415;
        double raio, area;

        //Entrada de dados para o usuario interagir
        System.out.print("Qual é o raio do circulo: ");
        raio = sc.nextDouble();

        area = PI * (raio * raio);

        // print com f para formatar o as casas decimais depois do .
        System.out.printf("A area do circulo com o raio %.2f é igual a %.2f", raio, area);
        
        
        //Fechar o scaner
        sc.close();

        }
    }
package exercicio;

public class Exercico {

    public static void main(String[] args) {
        String gameRPG, gameIndie;
        int age, code;
        char gender;
        double priceRPG, priceIndie, media;

        gameRPG = "Hogwarts Legacy";
        priceRPG = 107.80;

        gameIndie = "Hollow Knight";
        priceIndie = 46.99;

        age = 35;
        code = 2367;
        gender = 'M';



        media = (priceRPG + priceIndie) / 2;

        System.out.println("produtos :");
        System.out.println(gameRPG + ", está custando R$" + priceRPG);
        System.out.println(gameIndie + ", está custando R$" + priceIndie);

        System.out.printf("O comprador tinha %d anos, código %d e genero: %s%n", age, code, gender);



        System.out.printf("A média de preço dos produtos é igual a %.2f", media);

    }
}

import javax.swing.JOptionPane;

public class Exzcc {
    public static void main(String[] args) {
        // Solicita ao usuário a temperatura em graus Celsius
        String celsiusString = JOptionPane.showInputDialog("Digite a temperatura em graus Celsius:");

        // Converte a entrada para um valor double
        double celsius = Double.parseDouble(celsiusString);

        // conversões
        double kelvin = celsius + 273.15;
        double reaumur = celsius * 0.8;
        double rankine = (celsius + 273.15) * 9/5;
        double fahrenheit = celsius * 9/5 + 32;

        // Constrói a mensagem com os resultados
        String message = "Temperatura em Kelvin: " + kelvin + " K\n" +
                "Temperatura em Réaumur: " + reaumur + " Re\n" +
                "Temperatura em Rankine: " + rankine + " Ra\n" +
                "Temperatura em Fahrenheit: " + fahrenheit + " °F";

        // Exibe a mensagem para que tiver vendo
        JOptionPane.showMessageDialog(null, message);
    }
}

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static int validationIntMenu(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        boolean valid = false;

        while (!valid) {
            try {
                option = sc.nextInt();
                if (option >= min && option <= max) {
                    valid = true;
                } else {
                    System.out.print("Opção inválida. Por favor, insira um número entre " + min + " e " + max + ".");
                }
            }
            catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Por favor, insira um número inteiro entre "+min+"e"+max+": ");
                sc.next();
            }
        }

        return option;
    }

    public static int validationInt() {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        boolean valid = false;

        while (!valid) {
            try {
                option = sc.nextInt();
                valid = true;
            }
            catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Por favor, insira um número inteiro: ");
                sc.next();
            }
        }

        return option;
    }

    public static String validationString(Scanner sc) {
        String input = "";

        while (input.isEmpty() || !input.matches("[\\p{L}\\s@._-]+")) {
            input = sc.nextLine();
            if (!input.matches("[\\p{L}\\s@*._-]+")) {
                System.out.print("Entrada inválida. Por favor, insira apenas letras, espaços: ");
            }
        }

        return input;
    }


    public static double validationDouble(Scanner sc) {
        double value = 0.0;
        boolean valid = false;

        while (!valid) {
            try {
                String input = sc.nextLine();
                value = Double.parseDouble(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor, insira um valor numérico: ");
            }
        }

        return value;
    }

    public static String validateStringInt(Scanner sc) {
        String input = "";

        while (input.isEmpty() || !input.matches("\\d*(\\.\\d+)?")) {
            input = sc.nextLine().trim();
            if (!input.matches("\\d*(\\.\\d+)?")) {
                System.out.print("Entrada inválida. Por favor, insira um número válido: ");
            }
        }
        return input;
    }
    public static String validateDate(Scanner sc) {
        String input = "";
        Pattern pattern = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            input = sc.nextLine().trim();
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                inputFormat.setLenient(false);
                try {
                    java.util.Date date = inputFormat.parse(input);
                    return outputFormat.format(date);
                } catch (ParseException e) {
                    System.out.print("Entrada inválida. Por favor, insira uma data válida (dd/MM/yyyy): ");
                }
            } else {
                System.out.print("Entrada inválida. Por favor, insira uma data no formato dd/MM/yyyy: ");
            }
        }
    }
}

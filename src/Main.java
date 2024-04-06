
import java.util.Scanner;
public class Main {

    public static final String[] RIM = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    public static final String[] ARAB = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите пример");
        String primer = scanner.nextLine();
        System.out.println(calc(primer));

    }

    public static String calc(String input) throws Exception {
        String[] razbivka = input.split("[+*/-]");
        String result = null;

        if (razbivka.length > 2) {

            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        if (razbivka.length < 2){
            throw new Exception("строка не является математической операцией");
        }


        boolean isRim = false; // флажок первое число Римское или нет

        for (String rim : RIM) {
            if (razbivka[0].contains(rim)) {
                isRim = true;
                break;
            }
        }


        boolean isArab = false;// флажок второе число Арабское или нет

        for (String arab : ARAB) {
            if (razbivka[1].contains(arab)) {
                isArab = true;
                break;
            }
        }
        if (isRim & !isArab) {
            result = rimcalculc(input, razbivka);
        } else if (!isRim & isArab) {
            result = arabcalculc(input, razbivka);
        } else {
            throw new Exception("используются одновременно разные системы счисления");
        }
        return result;
    }


    public static String arabcalculc(String p, String[] razbivka) throws Exception {

        int dostau0;
        int dostau1;
        String result = null;

        try {
            dostau0 = Integer.parseInt(razbivka[0]);
            dostau1 = Integer.parseInt(razbivka[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("Cтрока не является математической операцией");

        }

        if (dostau0 > 10 || dostau1 > 10 || dostau0 <= 0 || dostau1 <= 0) {
            throw new Exception("Калькулятор умеет работать с числами от 1 до 10");
        }
        if (p.contains("+")) {
            result = String.valueOf(dostau0 + dostau1);
        } else if (p.contains("-")) {
            result = String.valueOf(dostau0 - dostau1);
        } else if (p.contains("*")) {
            result = String.valueOf(dostau0 * dostau1);
        } else if (p.contains("/")) {
            result = String.valueOf(dostau0 / dostau1);
        }
        return result;
    }

    public static String rimcalculc(String primer, String[] razbivka) throws Exception {

        String rimznach1 = razbivka[0];
        String rimznach2 = razbivka[1];

        int rimzifra1 = 0;
        int rimzifra2 = 0;

        int rezrus = 0;


        for (int i = 0; i < RIM.length; i++) {
            if (RIM[i].equals(rimznach1)) {
                rimzifra1 = i;
            }
        }

        for (int i = 0; i < RIM.length; i++) {
            if (RIM[i].equals(rimznach2)) {
                rimzifra2 = i;
            }
        }
        if (primer.contains("+")) {
            rezrus = rimzifra1 + rimzifra2;
        } else if (primer.contains("-")) {
            rezrus = rimzifra1 - rimzifra2;
            if (rezrus < 0) {
                throw new Exception("в римской системе нет отрицательных чисел");
            }
        } else if (primer.contains("*")) {
            rezrus = rimzifra1 * rimzifra2;
        } else if (primer.contains("/")) {
            rezrus = rimzifra1 / rimzifra2;
        }
        return RIM[rezrus];
    }
}


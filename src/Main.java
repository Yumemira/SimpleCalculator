import java.io.IOException;
import java.util.Scanner;

// Main class
public class Main {
    static Scanner sc; //scanner for catch input
    static int[] var = new int[2]; // vars container
    static char operator; // operator used in calculation
    static Calculate calculate = new Calculate(); // instance of calculator
    static Converter converter = new Converter();

    // start point
    public static void main(String[] args)
    {
        String getInput; //input container
        String getOutput; // output container

        // scan user input
        sc = new Scanner(System.in);
        getInput = sc.nextLine();
        sc.close();

        try {
            // count part
            getOutput = calc(getInput);

            //output part
            System.out.println(getOutput);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    // calculations happen here
    public static String calc(String input) throws IOException
    {
        boolean arabic = true; // boolean that controls system encoding


        sc = new Scanner(input);
        // get all operands and operator from string
        if(sc.hasNextFloat())
        {
            throw new IOException("Используются только целые числа");
        }
        else {
            if (sc.hasNextInt()) {
                arabicNumbers();
            } else {
                romeNumbers();
                arabic = false;
            }
            sc.close();
        }


        int result = calculate.calculation(var, operator);

        if(arabic)
            return String.valueOf(result);
        else
            return converter.arabicToRome(result);
    }

    //arabic version
    public static void arabicNumbers() throws IOException
    {
        var[0] = sc.nextInt(); // first numb
        if(sc.hasNext())
        {
            operator = sc.next().charAt(0); // operator
        }
        else
        {
            throw new IOException("Для вычисления необходим оператор");

        }

        if (sc.hasNextInt())
        {
            var[1] = sc.nextInt(); // second numb
        }
        else
        {
            throw new IOException("Отсутствует второе число(вы использовали римское число вместо арабского?)");
        }

        //checking for 2nd operator
        if (sc.hasNext())
        {
            throw new IOException("Неверное количество операторов(2 числа 1 оператор)");
        }
    }

    //rome version
    public static void romeNumbers() throws IOException
    {
        String[] romeNumbs; // rome numbers' container
        try {
            romeNumbs = new String[2];


            romeNumbs[0] = sc.next(); // first numb

            if (sc.hasNext()) {
                operator = sc.next().charAt(0); // operator
            } else {
                throw new IOException("Для вычисления необходим оператор");
            }

            if (sc.hasNext()) {
                romeNumbs[1] = sc.next(); // second numb
            } else {
                throw new IOException("Отсутствует второе число(вы использовали римское число вместо арабского?)");
            }

            //checking for 2nd operator
            if (sc.hasNext()) {
                throw new IOException("Неверное количество аргументов(2 числа 1 оператор)");
            }

            var[0] = converter.romeToArabic(romeNumbs[0]);
            var[1] = converter.romeToArabic(romeNumbs[1]);
        } catch (IllegalArgumentException e) {
            System.out.println("Калькулятор может работать только с числами из одной системы счисления(римские либо арабские)");
        }

    }
}
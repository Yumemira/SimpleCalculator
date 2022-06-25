import java.io.IOException;

public class Calculate
{
    //function to do calculaions
    public int calculation(int[] var,char operator)
    {
        int answer; // answer container

        //check all numbers are above or equals to 10
        if(var[0] > 10 || var[1] > 10)
        {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Число больше 10ти недопустимо");
            }
        }

        switch (operator)
        {
            case '+':
                answer = sumFunc(var);
                break;
            case '-':
                answer = substrFunc(var);
                break;
            case '*':
                answer = multiplFunc(var);
                break;
            case '/':
                answer = divideFunc(var);
                System.out.println("Внимание, при делении выполнено округление до целого числа!");
                break;
            default:
            try{
                throw new IOException();
            }catch (IOException e)
            {
                System.out.println("Неизвестный оператор(Допустимые +,-,*,/)");
            }
            answer = 0;
        }
        return answer;
    }
// find sum of variables
    private int sumFunc(int[] var)
    {
        return var[0] + var[1];
    }
// find substraction variable one and two
    private int substrFunc(int[] var)
    {
        return var[0] - var[1];
    }
// find multiplication of variables
    private int multiplFunc(int[] var)
    {
        return var[0] * var[1];
    }
// find dividing first variable on the second
    private int divideFunc(int[] var)
    {
        return var[0]/var[1];
    }
}

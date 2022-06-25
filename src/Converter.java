import java.io.IOException;
import java.util.concurrent.locks.Condition;

import static java.lang.String.valueOf;

public class Converter
{
    RomeNumbs romeNumbs; // row of existed numbers
    public String arabicToRome(int convertNumber) throws IOException
    {
        String romeNumb = " "; // answer container

        if(convertNumber<=0)
        {
            throw new IOException("Римское число не может быть отрицательным либо равняться нулю");
        }
        //conversation
        while(convertNumber>0)
        {

            int i = 0;
            while(i<6&&convertNumber>=romeNumbs.values()[i].numValue)
                i++;

            i--;
            if(i<6)
            {
                if (romeNumbs.values()[i+1].numValue - convertNumber <= romeNumbs.values()[i+1].numValue / 5&&romeNumbs.values()[i+1].numValue!=2 * romeNumbs.values()[i].numValue) // IV matches
                {

                    convertNumber += romeNumbs.values()[i].numValue;
                    convertNumber -= romeNumbs.values()[i+1].numValue;

                    romeNumb += romeNumbs.values()[i].name();
                    romeNumb += romeNumbs.values()[i+1].name();

                    continue;
                }
                if(romeNumbs.values()[i+1].numValue - convertNumber <= romeNumbs.values()[i+1].numValue / 10) // IX matches
                {
                    convertNumber += romeNumbs.values()[i-1].numValue;
                    convertNumber -= romeNumbs.values()[i+1].numValue;

                    romeNumb += romeNumbs.values()[i-1].name();
                    romeNumb += romeNumbs.values()[i+1].name();

                    continue;
                }
            }

            convertNumber -= romeNumbs.values()[i].numValue;
            romeNumb += romeNumbs.values()[i].name();
        }

        return romeNumb;
    }

    public int romeToArabic(String convertNumber)
    {
        int arabicNumb = 0; // answer container

        String firstNumb;
        String secondNumb;
        int firstID;
        int secondID;

        int i = 0;
        while(i+1<convertNumber.length())
        {
            firstNumb = Character.toString(convertNumber.charAt(i));
            secondNumb = Character.toString(convertNumber.charAt(i+1));
            firstID = RomeNumbs.valueOf(firstNumb).ordinal();
            secondID = RomeNumbs.valueOf(secondNumb).ordinal();

            if(secondID > firstID)
            {
                arabicNumb -= RomeNumbs.valueOf(firstNumb).numValue;
                arabicNumb += RomeNumbs.valueOf(secondNumb).numValue;
                i+=2;
            }
            else
            {
                arabicNumb += RomeNumbs.valueOf(firstNumb).numValue;
                i++;
            }
        }
        if(i<convertNumber.length())
        {
            firstNumb = Character.toString(convertNumber.charAt(i));
            arabicNumb += RomeNumbs.valueOf(firstNumb).numValue;
        }

        return arabicNumb;
    }
}

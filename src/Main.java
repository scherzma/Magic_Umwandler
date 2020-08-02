import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class Main {

    public static void main(String[] args) {//new BigDecimal(1).divide
        convertToBase(new BigDecimal("9192631770"),60);
        //System.out.println();
        //System.out.println(Integer.toString(123456000,16));
    }

    public static void convertToBase(double number, int newBase){
        int digitsGZ = (int)Math.floor(Math.log(number) / Math.log(newBase));

        double fraction = number % 1;
        ArrayList<String> ans = new ArrayList<>();

        for(int i = digitsGZ; i >= 0; i--){
            double pow = Math.pow(newBase, i);
            ans.add("["+(int)((number-number%pow) / pow)+"]");
            number %= pow;
        }
        ans.add(",");
        number = fraction;
        while(number > 0.00000001){
            number *= newBase;
            ans.add("["+(int)(number-number%1)+"]");
            number %= 1;
        }

        ans.stream().forEach(x -> System.out.print(x));
    }

    public static void convertToBase(String numberS, int newBase){
        convertToBase(new BigDecimal(numberS), newBase);
    }

    public static void convertToBase(BigDecimal number, int newBase){
        int digitsGZ = (int)Math.floor(Math.log(number.doubleValue()) / Math.log(newBase));

        BigDecimal fraction = new BigDecimal(number.remainder(BigDecimal.ONE).toString());
        ArrayList<String> ans = new ArrayList<>();

        for(int i = digitsGZ; i >= 0; i--){
            BigDecimal pow = new BigDecimal(Math.pow(newBase,i));
            ans.add("["+((number.subtract(number.remainder(pow))).divide(pow).toString())+"]");
            number = number.remainder(pow);
        }
        ans.add(",");
        number = fraction;
        BigDecimal newBaseBD = new BigDecimal(newBase);


        BigDecimal acc = new BigDecimal(10).movePointLeft(200);
        while(number.compareTo(acc) > 0){
            number = number.multiply(newBaseBD);
            ans.add("["+(number.subtract(number.remainder(BigDecimal.ONE))).intValue()+"]");
            number = number.remainder(BigDecimal.ONE);
        }

        ans.stream().forEach(x -> System.out.print(x));
    }
}
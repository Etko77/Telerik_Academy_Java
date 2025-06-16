package exercises;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Exercise {
    public static void main(String[] args) {
        Function<Integer,Double> centrigradeToFarenheit = c -> new Double((c * 9 / 5)+ 32);

        System.out.println(centrigradeToFarenheit.apply(30));

        Function<String,Integer> stringToInteger = stringToConvert -> Integer.valueOf(stringToConvert);
        System.out.println(stringToInteger.apply("44") + 12);

        Function<Integer,Integer> doubleValue = x -> x*2;
        System.out.println(doubleValue.apply(2));

        UnaryOperator<Integer> doubleValueBinary = x -> x + x;
        System.out.println(doubleValueBinary.apply(6));
    }
}

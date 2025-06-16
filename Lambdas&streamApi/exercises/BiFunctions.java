package exercises;

import java.util.function.BiFunction;

public class BiFunctions {
    public static void main(String[] args) {
        BiFunction<Integer,String, Double> combine = (x, s) -> x+Double.parseDouble(s);
        System.out.println(combine.apply(4,"44"));
    }
}

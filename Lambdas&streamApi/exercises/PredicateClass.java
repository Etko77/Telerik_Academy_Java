package exercises;

import java.util.function.Predicate;

public class PredicateClass {
    public static void main(String[] args) {
        Predicate<String> isStringValid = s -> {
            return s != null && !s.isEmpty() && !s.isBlank();
        };
        System.out.println(isStringValid.test(null));
        System.out.println(isStringValid.test(""));
        System.out.println(isStringValid.test("null)"));
    }
}

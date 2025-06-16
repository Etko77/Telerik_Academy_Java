package exercises;

import exercises.Person;

import java.util.List;
import java.util.stream.Collectors;

public class StreamUsage {
    public static void main(String[] args) {

    }
    public static boolean validateListContains(List<String> string, String target){
        return string.stream().anyMatch(s->s.contains(target));
    }
    public static double distinctCount(List<String> strings){
        return strings.stream().distinct().count();
    }
    public static List<String> extractElementsWithLeadingDigit(List<String> strings){
        return strings
                .stream()
                .filter(s -> Character.isDigit(s.charAt(0)))
                .collect(Collectors.toList());
    }
    public static List<String> transformStringsToUppercase(List<String> strings){
        return strings
                .stream()
                .map(s->s.toUpperCase())
                .collect(Collectors.toList());
    }
    public static String extractPeopleIntroductions(List<Person> people){
        return people
                .stream()
                .map(person -> person.toString())
                .collect(Collectors.joining("\n"))
    }
    public static String concatenateList(List<String> strings, String delimiter){
        return strings
                .stream()
                .reduce("",(acc,next)->acc+next+delimiter);
    }
}

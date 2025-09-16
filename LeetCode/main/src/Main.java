import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringToWorkOn = scanner.nextLine();
        HashMap<Character,Integer> pairs = new HashMap<>();
        for (Character ch:
                stringToWorkOn.toCharArray()) {
            if(pairs.containsKey(ch)){
                pairs.put(ch,pairs.get(ch)+1);
            }else{
                pairs.put(ch,1);
            }
        }
        Character maxSymbol = null;
        int maxSymbolCount = -1;

        Character maxLower = null;
        int maxLowerCount = -1;

        Character maxUpper = null;
        int maxUpperCount = -1;

        for (Map.Entry<Character, Integer> entry : pairs.entrySet()) {
            char ch = entry.getKey();
            int count = entry.getValue();

            if (Character.isLowerCase(ch)) {
                if (count > maxLowerCount || (count == maxLowerCount && ch < maxLower)) {
                    maxLower = ch;
                    maxLowerCount = count;
                }
            } else if (Character.isUpperCase(ch)) {
                if (count > maxUpperCount || (count == maxUpperCount && ch < maxUpper)) {
                    maxUpper = ch;
                    maxUpperCount = count;
                }
            } else {
                if (count > maxSymbolCount || (count == maxSymbolCount && ch < maxSymbol)) {
                    maxSymbol = ch;
                    maxSymbolCount = count;
                }
            }
        }

        if (maxSymbol != null) {
            System.out.println(maxSymbol + " " + maxSymbolCount);
        } else {
            System.out.println("-");
        }
        
        if (maxLower != null) {
            System.out.println(maxLower + " " + maxLowerCount);
        } else {
            System.out.println("-");
        }

        if (maxUpper != null) {
            System.out.println(maxUpper + " " + maxUpperCount);
        } else {
            System.out.println("-");
        }
    }

}

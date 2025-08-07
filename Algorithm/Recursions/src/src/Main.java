import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        System.out.println(fibonacci(n));
    }
    public static int countOccurrences2(int n){
        if (n < 0){
            return 0;
        }
        if(n/10 == 0){
            if(n == 8){
                return 1;
            }else{
                return 0;
            }
        }
        if(n%10 == 8 && (n/10)%10 == 8){
            return 2 + countOccurrences2(n/10);
        }else if(n%10 == 8){
            return 1 + countOccurrences2(n/10);
        }
        else{
            return countOccurrences2(n/10);
        }
    }
    public static int countOccurrences1(int n){
        if (n < 0){
            return 0;
        }
        if(n/10 == 0){
            if(n == 7){
                return 1;
            }else{
                return 0;
            }
        }
        if(n%10 == 7){
            return 1 + countOccurrences1(n/10);
        }else{
            return countOccurrences1(n/10);
        }
    }
    public static int triangleBlocks(int n){
        if (n <= 1){
            return 1;
        }
        return n + triangleBlocks(n-1);
    }
    public static int bunnyEars2(int n){
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return 2;
        }
        if(n%2 == 0){
            return 3 + bunnyEars2(n-1);
        }else{
            return 2 + bunnyEars2(n-1);
        }
    }
    public static int bunnyEars1(int n){
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return 2;
        }
        return 2 + bunnyEars1(n-1);
    }
    public static int fibonacci(int n){
        if (n <= 1){
            return 1;
        }
        return fibonacci(n-2) + fibonacci(n-1);
    }
    public static int sumDigits(int n){
        if(n%10 ==0 ){
            return n;
        }
        return n%10 + sumDigits(n/10);
    }
    public static int fact(int n){
        if (n <= 1){
            return 1;
        }
        return n * fact(n-1);
    }
    public static String reverse(String str){
        if(str.isEmpty()){
            return str;
        }
        return reverse(str.substring(1))+str.charAt(0);
    }


}
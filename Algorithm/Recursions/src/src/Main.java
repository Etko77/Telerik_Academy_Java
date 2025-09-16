import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int m = Integer.parseInt(scanner.nextLine());
        int [][]arrMatrix = new int[n][m];
        for(int i = 0; i < n;i++){
            for(int j = 0; j < m;j++){
                arrMatrix[i][j] = Integer.parseInt(scanner.nextLine());
            }
        }
        for(int i = 0; i < n;i++){
            for(int j = 0; j < m;j++){
                if(arrMatrix[i][j] == 0){
                    System.out.println(scroogeMcDuck(arrMatrix,i,j));
                    break;
                }
            }
        }
    }
    public static int scroogeMcDuck(int[][] arr, int n, int m) {
        // If there are coins in current cell, take one
        int coinsCollected = 0;
        if (arr[n][m] > 0) {
            arr[n][m]--;
            coinsCollected++;
        }

        // Directions: left, right, up, down
        int[][] directions = {
                {0, -1}, // left
                {0,  1}, // right
                {-1, 0}, // up
                {1,  0}  // down
        };

        int bestDir = -1;
        int maxCoins = 0;

        // Find the best move
        for (int i = 0; i < directions.length; i++) {
            int newN = n + directions[i][0];
            int newM = m + directions[i][1];

            // Check bounds
            if (newN >= 0 && newN < arr.length && newM >= 0 && newM < arr[0].length) {
                int coins = arr[newN][newM];
                if (coins > maxCoins) {
                    maxCoins = coins;
                    bestDir = i;
                }
            }
        }

        // If no coins around, stop recursion
        if (maxCoins == 0) {
            return coinsCollected;
        }

        // Move to best neighbor and continue recursively
        int newN = n + directions[bestDir][0];
        int newM = m + directions[bestDir][1];
        coinsCollected += scroogeMcDuck(arr, newN, newM);

        return coinsCollected;
    }

    public static int countHi (String str){
        if (str.length()<2) return 0;
        if(str.substring(0,2).equals("hi")){
            return 1 + countHi(str.substring(2));
        }else{
            return 0+countHi(str.substring(1));
        }
    }
    public static int countX (String str){
        if (str.isEmpty()) return 0;
        if(str.charAt(0) == 'x'){
            return 1 + countX(str.substring(1));
        }else{
            return 0 + countX(str.substring(1));
        }
    }
    public static int powerN(int num, int n){
        if(n == 0){
            return 1;
        }
        if(n==1){
            return num;
        }
        return num * powerN(num, n-1);
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
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.println(palindrome("abccba"));
    }
    public static boolean palindrome(String str){
        if(str.length()<=1){
            return true;
        }
        if(str.charAt(0) != str.charAt(str.length()-1)){
            return false;
        }
        return palindrome(str.substring(1,str.length()-1));
    }
}
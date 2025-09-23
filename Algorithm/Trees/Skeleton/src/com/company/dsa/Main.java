package com.company.dsa;

public class Main {
    
    public static void main(String[] args) {
        
    }
    public int findMax(int[] arr){
        int max = arr[0];
        for(int i = 1;i < arr.length; i++){
            if(arr[i]>max){
                max = arr[i];
            }
        }
        return max;
    }
    
}

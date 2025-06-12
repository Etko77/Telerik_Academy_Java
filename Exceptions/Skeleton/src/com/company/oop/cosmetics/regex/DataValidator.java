package com.company.oop.cosmetics.regex;

import com.company.oop.cosmetics.exceptions.InvalidDataProvided;

public class DataValidator {
    public static void validateName(String name) throws InvalidDataProvided {
        String regex = "^.{3,10}$";
        try{
            if(!name.matches(regex)){
                throw new InvalidDataProvided("Invalid name provided");
            }
        }finally{

        }

    }
    public static void validateBrand(String name) throws InvalidDataProvided {
        String regex = "^.{2,10}$";
        try {
            if (!name.matches(regex)) {
                throw new InvalidDataProvided("Invalid brand provided");
            }
//        }catch(InvalidDataProvided e){
//            System.out.println(e.getMessage());
//        }
        }finally{

        }

    }



}

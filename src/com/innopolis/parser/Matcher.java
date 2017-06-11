package com.innopolis.parser;

/**
 * Created by davlet on 6/11/17.
 */
public class Matcher {
    public static boolean match(String s1, String s2){
        if (s1.equals(s2)){
            return true;
        }
        return false;
    }

    public static boolean fuzzymatch(String s1, String s2){
        return false;
    }
}

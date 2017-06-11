package com.innopolis;

import com.innopolis.parser.ParserEngine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<File> files = new ArrayList<>();
        files.add(new File("file1.txt"));

        try (BufferedReader br = new BufferedReader(new FileReader(new File("file1.txt")))) {
            String current;
            while ((current = br.readLine()) != null){
                System.out.println(current);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        ParserEngine parserEngine = new ParserEngine();


    }
}

package com.innopolis.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

/**
 * Created by davlet on 6/10/17.
 */
public class ParserEngine implements Runnable, Parser {
    File file;


    public ParserEngine(File file){
        this.file = file;
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file));
    }

    @Override
    public void parse(File file) {

    }
}

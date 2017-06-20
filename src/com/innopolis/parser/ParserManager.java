package com.innopolis.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.exit;

/**
 * Created by davlet on 6/11/17.
 */
public class ParserManager {
    private List<Parser> parsers;
    private List<File> files;
    public Map<String, Integer> wordToNumberOfOccurences;
    private String splitBy;
    private File resultFile;

    public ParserManager(List<File> files, String splitBy, File resultFile){
        parsers = new ArrayList<>();
        if (checkFiles(files))
            this.files = files;
        else {
            System.out.println("One of specified files not found! Please, check paths!");
            exit(0);
        }
        this.wordToNumberOfOccurences = new HashMap<>();
        this.splitBy = splitBy;
        this.resultFile = resultFile;
    }

    public boolean checkFiles(List<File> files){
        for (File file : files){
            if (file.isDirectory() || !file.exists()){
                return false;
            }
        }
        return true;
    }

    public void start() {
        List<String> threadNames = new ArrayList<>();
        for (int i = 0; i < files.size(); i++){
            threadNames.add("Thread " + i + " ");
            parsers.add(new Parser(files.get(i), this.wordToNumberOfOccurences, splitBy, threadNames.get(i), resultFile));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(files.size());
        for (Parser parser : parsers){
            executorService.execute(parser);
        }
        executorService.shutdown();
    }
}

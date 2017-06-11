package com.innopolis.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by davlet on 6/11/17.
 */
public class ParserManager {
    private List<Parser> parsers;
    private List<File> files;
    private Map<String, Integer> wordToNumberOfOccurences;
    private String splitBy;
    private File resultFile;

    public ParserManager(List<File> files, String splitBy, File resultFile){
        parsers = new ArrayList<>();
        this.files = files;
        this.wordToNumberOfOccurences = new HashMap<>();
        this.splitBy = splitBy;
        this.resultFile = resultFile;
    }

    public void start() {
        String[] strings = new String[] {"1 Thread ", "2 Thread ", "3 Thread "};
        for (int i = 0; i < files.size(); i++){
            parsers.add(new Parser(files.get(i), this.wordToNumberOfOccurences, splitBy, strings[i], resultFile));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(files.size());
        for (Parser parser : parsers){
            executorService.execute(parser);
        }
        executorService.shutdown();
    }
}

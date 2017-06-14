package com.innopolis.parser;

import java.io.*;
import java.util.Map;

/**
 * Created by davlet on 6/10/17.
 */
public class Parser implements Runnable, Parsable {
    private File file;
    private Map<String, Integer> wordToNumberOfOccurences;
    private String splitBy;
    private String id;
    private File resultFile;
    private PrintWriter printWriter;

    Parser(File file, Map<String, Integer> wordToNumberOfOccurences, String splitBy, String id, File resultFile) {
        this.file = file;
        this.wordToNumberOfOccurences = wordToNumberOfOccurences;
        this.splitBy = splitBy;
        this.id = id;
        this.resultFile = resultFile;
        try {
            printWriter = new PrintWriter(resultFile, "UTF-16");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        parse();
    }

    @Override
    public void parse() {
        String currentLine = null;
        String[] lineSplitted = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file));
            while ((currentLine = bufferedReader.readLine()) != null) {
                lineSplitted = currentLine.split(splitBy);

                    for (String word : lineSplitted) {
                        if (containsLatinLetter(word)) {
                            System.out.println("Stopped! " + this.id + "Contains english letters!");
                            return;
                        }
                        if (word != null) {
                            synchronized (wordToNumberOfOccurences) {
                                putInHashMap(word);
                            }
                            System.out.println(this.id + "Word: " + word + " #: " + wordToNumberOfOccurences.get(word));
                            writeToFile(this.id + "Word: " + word + " #: " + wordToNumberOfOccurences.get(word));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean containsLatinLetter(String word) {
        char[] chars = word.toCharArray();
        for (char c : chars){
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
                return true;
        }
        return false;
    }

    private void putInHashMap(String word){
        if (!wordToNumberOfOccurences.containsKey(word)) {
            wordToNumberOfOccurences.put(word, 1);
            return;
        }
        Integer value = wordToNumberOfOccurences.get(word);
        wordToNumberOfOccurences.put(word, value + 1);
    }

    private void writeToFile(String string){
        if (resultFile.exists()){
            printWriter.println(string);
        }
    }
}
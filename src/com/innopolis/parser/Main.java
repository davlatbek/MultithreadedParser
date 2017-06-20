package com.innopolis.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * Вариант 1
     * Необходимо разработать программу, которая получает на вход список ресурсов, содержащих текст,
     * и считает общее количество вхождений (для всех ресурсов) каждого слова. Каждый ресурс должен
     * быть обработан в отдельном потоке, текст не должен содержать инностранных символов, только кириллица,
     * знаки препинания и цифры. Отчет должен строиться в режиме реального времени, знаки препинания и цифры
     * в отчет не входят. Все ошибки должны быть корректно обработаны, все API покрыто модульными тестами
     * @param args
     */
    public static void main(String[] args) {
        List<File> files = new ArrayList<>();
        files.add(new File("./src/com/innopolis/parser/file1.txt"));
        files.add(new File("./src/com/innopolis/parser/file2.txt"));
        files.add(new File("./src/com/innopolis/parser/file3.txt"));
        File resultFile = new File("./src/com/innopolis/parser/result.txt");
        ParserManager manager = new ParserManager(files, " ", resultFile);
        manager.start();
    }
}

package org.semyonan.parser;

import org.springframework.data.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileParser {
    public static List<Pair<String, Integer>> parse(String fileName) throws FileNotFoundException {
        var result = new ArrayList<Pair<String, Integer>>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        while(scanner.hasNext()){
            var line = scanner.nextLine();
            var parts = line.split("_");
            result.add(Pair.of(parts[0], Integer.parseInt(parts[1])));
        }
        return result;
    }
}

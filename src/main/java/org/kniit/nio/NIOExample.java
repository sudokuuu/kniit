package org.kniit.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class NIOExample {
    public static void main(String[] args) {
        Path inputFile = Paths.get("src/main/resources/input_nio.txt");
        Path outputFile = Paths.get("src/main/resources/output_nio.txt");

        try {
            List<String> lines = Files.readAllLines(inputFile);
            Files.write(outputFile, lines, StandardOpenOption.APPEND);
            System.out.println("Файл успешно скопирован.");
        } catch (IOException e) {
            System.out.println("Ошибка при копировании файла: " + e.getMessage());
        }
    }
}

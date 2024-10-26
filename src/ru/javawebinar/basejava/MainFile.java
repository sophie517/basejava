package ru.javawebinar.basejava;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainFile {
    public static void main(String[] args) {
        // рекурсивный обход и вывод имени файлов в каталогах и подкаталогах
        File path = new File("./src/ru/javawebinar/basejava");
        printAllFiles(path.toPath());
    }

    public static void printAllFiles(Path path) {
        if (Files.isRegularFile(path)) {
            System.out.println(path.getFileName());
        } else if (Files.isDirectory(path)){
            File[] files = path.toFile().listFiles();
            if (files != null) {
                for (File file : files) {
                    printAllFiles(file.toPath());
                }
            }
        }
    }
}

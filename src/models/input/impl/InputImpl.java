package models.input.impl;

import models.input.Input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputImpl implements Input {
    @Override
    public List<String> readFile(String fileName) {
        List<String> textFile = new ArrayList<>();
        try (Scanner input = new Scanner(new FileInputStream(fileName))) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                textFile.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        }
        return textFile;
    }
}

package org.example;

import java.io.*;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Main {
    public static void main(String... fileName) {
        if(fileName.length == 0) {
            System.out.println("Usage: java -jar <option flag 1> ... <option flag n> ccwc.jar <file>");
            return;
        }

        File file = new File(fileName[fileName.length - 1]);

        boolean linesFlag = false;
        boolean wordsFlag = false;
        boolean charsFlag = false;

        for(String flag : fileName) {
            if(flag.equals("-l") || flag.equals("-L"))
                linesFlag = true;
            else if(flag.equals("-w") || flag.equals("-W"))
                wordsFlag = true;
            else if(flag.equals("-c") || flag.equals("-C"))
                charsFlag = true;
        }

        try {
            String content = Files.readString(file.toPath(), StandardCharsets.UTF_8);

            if(linesFlag) {
                System.out.print(content.split("\\n").length + "\t");
            }

            if(wordsFlag) {
                System.out.print(content.split("\\s+").length + "\t");
            }

            if(charsFlag) {
                System.out.print(content.length() + "\t");
            }

            System.out.println(file.getName());
        }
        catch (IOException ioe) {
            System.out.println("Error reading file: " + ioe.getMessage());
        }
    }
}
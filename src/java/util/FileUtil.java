/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author thien
 */
public class FileUtil {

    public static void writeToFile(String src, String filePath) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {

            fileWriter.write(src);
            fileWriter.flush();
            System.out.println("Success");
        }
    }

    public static String readFile(String filePath) throws FileNotFoundException, IOException {
        StringBuilder builder = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath); BufferedReader br = new BufferedReader(fileReader)) {

            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
        }
        return builder.toString();
    }
}

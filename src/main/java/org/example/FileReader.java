package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private String path;
    private String data;
    private File file;

    public FileReader(String path){
        this.path = path;
        file = new File(path);
    }

    public void read() throws Exception {
            StringBuilder content = new StringBuilder();

            try(BufferedReader reader = new BufferedReader(new java.io.FileReader(file));) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append(" ");
                }
            } catch (IOException e) {
                System.out.println("Blad wczytywania pliku");
            }
            data = content.toString();

    }

    public String getData() {
        return data;
    }
}

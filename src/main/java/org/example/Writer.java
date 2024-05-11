package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    private String path;
    public Writer(String path){
        this.path = path;
    }
    public void write(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

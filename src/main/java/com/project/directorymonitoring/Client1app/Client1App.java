package com.project.directorymonitoring.Client1app;

import com.project.directorymonitoring.FileProcessor;

public class Client1App {
    static FileProcessor processor = FileProcessor.getInstance();
    public static void main(String[] args) {
        String directoryPath = "Filestore";
        processor.processFiles(directoryPath);
    }
}

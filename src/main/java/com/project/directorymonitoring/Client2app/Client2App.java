package com.project.directorymonitoring.Client2app;

import com.project.directorymonitoring.FileProcessor;

public class Client2App {
    static FileProcessor processor = FileProcessor.getInstance();
    public static void main(String[] args) {
        String directoryPath = "Filestore";
        processor.processFiles(directoryPath);
    }
}

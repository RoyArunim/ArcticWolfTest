package com.project.directorymonitoring.Client1app;

import com.project.directorymonitoring.FileProcessor;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Client1App {
    static FileProcessor processor = FileProcessor.getInstance();
    public static void main(String[] args) {
        String directoryPath = "Filestore1";
        Client1App app= new Client1App();
        app.sendFilesToServer(directoryPath);
    }

    public void sendFilesToServer(String folderPath) {
        try (
                Socket socket = new Socket("localhost", 9999);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())
        ) {
            Map<String, byte[]> fileMap = processor.processFiles(folderPath, "^[a-z]+\\.[a-z]+$");
            out.writeObject(fileMap);
            System.out.println("Sent file map to server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

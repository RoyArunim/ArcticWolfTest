package com.project.directorymonitoring.Client2app;

import com.project.directorymonitoring.Client1app.Client1App;
import com.project.directorymonitoring.FileProcessor;
import com.project.directorymonitoring.FileReceiver;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Client2App {
    static FileProcessor processor = FileProcessor.getInstance();
    public static void main(String[] args) {
        String directoryPath = "Filestore2";
        Client2App app= new Client2App();
        app.sendFilesToServer(directoryPath);
    }

    private void sendFilesToServer(String directoryPath) {
        try (
                Socket socket = new Socket("localhost", 9999);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())
        ) {
            Map<String, byte[]> fileMap = processor.processFiles(directoryPath, "^[a-z]+\\.[a-z]+$");
            out.writeObject(fileMap);
            System.out.println("Sent file map to server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.project.directorymonitoring.Client2app;

import com.project.directorymonitoring.FileProcessor;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
            Map<String, byte[]> fileMap = processor.processFiles(directoryPath, "^[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+$");
            out.writeObject(fileMap);
            System.out.println("Sent file map to server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

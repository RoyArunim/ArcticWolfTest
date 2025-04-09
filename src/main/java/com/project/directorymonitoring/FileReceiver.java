package com.project.directorymonitoring;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class FileReceiver implements Runnable{

    private final Socket socket;
    private static Path OUTPUT_DIR;

    public FileReceiver(Socket socket, String outputdir) {
        this.socket = socket;
        OUTPUT_DIR = Paths.get(outputdir);
    }

    @Override
    public void run() {

        try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())){

            Map<String, byte[]> filemap = (Map<String, byte[]>) inputStream.readObject();
            System.out.println("Received file from client");
            //Ensure output directory exists
            if (!Files.exists(OUTPUT_DIR)) {
                Files.createDirectories(OUTPUT_DIR);
            }
            //Reconstruct
            for(Map.Entry<String, byte[]> entry: filemap.entrySet()){
                String filename = entry.getKey();
                byte[] fileBytes = entry.getValue();


                Path filePath = OUTPUT_DIR.resolve(filename);

                try (OutputStream outputStream = new FileOutputStream(filePath.toFile())) {
                    outputStream.write(fileBytes);
                }
                System.out.println("Saved file:" +filename);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

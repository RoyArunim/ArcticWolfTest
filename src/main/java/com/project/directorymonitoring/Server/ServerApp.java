package com.project.directorymonitoring.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.project.directorymonitoring.FileReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerApp {

    private static final Logger logger = LoggerFactory.getLogger(ServerApp.class);
    private final int serverport =9999;
    public static void main(String[] args) throws IOException {
        String directoryPath2Write = "FinalFileStore";
        ServerApp app = new ServerApp();
        app.start(directoryPath2Write);

    }

    public void start(String directoryPath2write) throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(9999)){
            System.out.println("Server is running");
            while(true){
                Socket clientSocket = serverSocket.accept();
                new Thread(new FileReceiver(clientSocket,directoryPath2write)).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}

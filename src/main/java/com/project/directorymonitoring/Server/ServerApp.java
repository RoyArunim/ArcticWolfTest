package com.project.directorymonitoring.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerApp {

    private static final Logger logger = LoggerFactory.getLogger(ServerApp.class);
    public static void main(String[] args) throws IOException {
        String directoryPath2Write = "FinalFileStore";
        ServerSocket serverSocket = new ServerSocket(1234);
        //Since multiple clients can access Server, we have to use multithreaded approach
        while(true){
            Socket clientSocket = serverSocket.accept();
            logger.info("Client connected with server: "+ clientSocket.getInetAddress());



        }

    }
}

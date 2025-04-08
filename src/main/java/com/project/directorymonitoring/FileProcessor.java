package com.project.directorymonitoring;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileProcessor {

    private FileProcessor(){}
    private static class SingletonHelper{
        private static final FileProcessor INSTANCE = new FileProcessor();
    }

    public static FileProcessor getInstance(){
        return SingletonHelper.INSTANCE;
    }


    public void processFiles(String filepath) {

        Map<String, String> filePropertiesMap = new HashMap<>();
        try {
            DirectoryStream<Path> stream;
            stream = Files.newDirectoryStream(Paths.get(filepath));

            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) {
                    String filename = entry.getFileName().toString();
                    if (validateFilename(filename)) {
                        filePropertiesMap.put(filename, Files.readString(entry));
                    }else
                        continue;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(Map.Entry<String, String> entrySet: filePropertiesMap.entrySet()){
            System.out.println(entrySet.getKey()+":::"+entrySet.getValue());
        }
    }

    public static boolean validateFilename(String filename){
        return true;
    }
}

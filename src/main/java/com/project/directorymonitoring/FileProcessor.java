package com.project.directorymonitoring;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class FileProcessor {

    private FileProcessor(){}
    private static class SingletonHelper{
        private static final FileProcessor INSTANCE = new FileProcessor();
    }

    public static FileProcessor getInstance(){
        return SingletonHelper.INSTANCE;
    }


    public Map<String, byte[]> processFiles(String filepath, String regex) throws IOException {
        File dir = new File(filepath);
        Map<String, byte[]> fileMap = new HashMap<>();
        Properties newProperties = new Properties();
        for(File file : dir.listFiles((f)->f.getName().endsWith("properties"))) {
            Properties props = new Properties();
            System.out.println("The files inside are:"+file.getName());
            try (FileInputStream fis = new FileInputStream(file)) {
                props.load(fis);
            }



            Pattern pattern = Pattern.compile(".*"); // Accept all keys for now
            props.stringPropertyNames().forEach(key -> {
                if (pattern.matcher(key).matches()) {
                    newProperties.setProperty(key, props.getProperty(key));
                }
            });



            if (!newProperties.isEmpty()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                newProperties.store(baos, "Filtered properties");
                byte[] fileBytes = baos.toByteArray();

                fileMap.put(file.getName(), fileBytes);

                //Delete files
                if (file.delete()) {
                    System.out.println("Deleted file after processing: " + file.getName());
                } else {
                    System.err.println("Failed to delete file: " + file.getName());
                }
            } else {
                System.out.println("No matching keys found in " + file.getName());
            }



        }
        return fileMap;


    }

}

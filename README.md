# ArcticWolfTest
Installation steps:<br/>
Make sure jdk8 is installed.  
JAVA_HOME has to be set in enviroment variables.

## Installation Steps
This application uses sock
ets for connection between Client and Server. Server is capable of accepting multiple client requests. Client closes its socket right after its task is done

##Clone the project
```bash
git clone https://github.com/RoyArunim/ArcticWolfTest/tree/master
```
Inside the project you will find two directories that have property files stored within them.  
FileStore1  
FileStore2  
Make sure these folder paths pre exist otherwise they might throw FileNotfound errors.  

The Client code processes the properties files such that only dot separated keys are picked up and the rest are ignored.  
This is the format of the regular expression that is filtering the properties. If the content in the files does not match the regex format specified, the Client will show that message ``` No Matching keys found in <<Filename>> ``` and move to the next .properties file. After the Client creates the Map of filtered properties, the original .properties files get deleted from the original directories.  
 After processing is done and Filtered propeeties map is sent to Server, Client closes socket.  

The reconstructed .properties files gets created by Server in a directory called FinalFileStore. Original filenames are used when reconstructing the .properties files.

## How to Run the Code  
Run the Server and Clients main() functions separately. For demonstration's sake I have specified two clients to show that the server can accept requests from multiple clients.  


 


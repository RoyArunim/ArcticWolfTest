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

Run the Server and Clients separately.
The original property files will get deleted once sent to Server.  
The final result will appear in a directory FinalFileStore that gets created by server on consuming the bytestreams from Client.  


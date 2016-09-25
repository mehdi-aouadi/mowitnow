# MowItNow  
## Documentation  
The documentation is available in the [project site].  
Many reports are generated using the maven-site-plugin.
## Running the application
The executable jar can be generated using Maven :  
Move to the project directory ```com.xebia.mowitnow``` and run
```maven clean install```.  
A jar file ```project-mowitnow-0.0.1-SNAPSHOT-exec.jar``` will be generated in ```/target``` subdirectory.  
Move to ```target``` and run :  
```java -cp project-mowitnow-0.0.1-SNAPSHOT-exec.jar com.xebia.mowitnow.Mowitnow.main "YourInputFilePath"```  
Put the input file path instead of YourInputFilePath.  
Input file path example :  
```
5 5
1 2 N
GAGAGAGAA
3 3 E
AADAADADDA  
```  

Result :  
```
1 3 N
5 1 E
```

 [project site]: <https://mehdi-aouadi.github.io/mowitnow/>  
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
Input file example :  
```
5 5
1 2 N
GAGAGAGAA
3 3 E
AADAADADDA  
```  

Result :  
```
Hello Humain, I am going to mow your lawn !
22:57:09.564 [main] DEBUG com.xebia.mowitnow.base.Lawn - A lawn has been initialized : North limit is 6 and East limit is 6 
22:57:09.565 [main] DEBUG com.xebia.mowitnow.mower.Commander - Mowers set. Here is the inital state of the lawn : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 |   | N |   |   |   |   |
-----------------------------
 1 |   |   |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.565 [main] DEBUG com.xebia.mowitnow.mower.Mower - The mower 0 is coming, brace yourself 
22:57:09.566 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 1 G .
22:57:09.566 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 |   | W |   |   |   |   |
-----------------------------
 1 |   |   |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.566 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 2 A .
22:57:09.566 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 | W | X |   |   |   |   |
-----------------------------
 1 |   |   |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.567 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 3 G .
22:57:09.567 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 | S | X |   |   |   |   |
-----------------------------
 1 |   |   |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.567 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 4 A .
22:57:09.567 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 | X | X |   |   |   |   |
-----------------------------
 1 | S |   |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.568 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 5 G .
22:57:09.568 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 | X | X |   |   |   |   |
-----------------------------
 1 | E |   |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.568 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 6 A .
22:57:09.569 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 | X | X |   |   |   |   |
-----------------------------
 1 | X | E |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.569 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 7 G .
22:57:09.569 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 | X | X |   |   |   |   |
-----------------------------
 1 | X | N |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.569 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 8 A .
22:57:09.570 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 | X | N |   |   |   |   |
-----------------------------
 1 | X | X |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.570 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 9 A .
22:57:09.570 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | E |   |   |
-----------------------------
 2 | X | X |   |   |   |   |
-----------------------------
 1 | X | X |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.571 [main] DEBUG com.xebia.mowitnow.mower.Mower - Stopping the mower Mower Id : 0 at Poisition X:1 Y:3
22:57:09.571 [main] DEBUG com.xebia.mowitnow.mower.Mower - The mower 1 is coming, brace yourself 
22:57:09.571 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 1 A .
22:57:09.571 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | E |   |
-----------------------------
 2 | X | X |   |   |   |   |
-----------------------------
 1 | X | X |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.572 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 2 A .
22:57:09.572 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | E |
-----------------------------
 2 | X | X |   |   |   |   |
-----------------------------
 1 | X | X |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.572 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 3 D .
22:57:09.572 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | S |
-----------------------------
 2 | X | X |   |   |   |   |
-----------------------------
 1 | X | X |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.573 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 4 A .
22:57:09.573 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | X |
-----------------------------
 2 | X | X |   |   |   | S |
-----------------------------
 1 | X | X |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.573 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 5 A .
22:57:09.574 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | X |
-----------------------------
 2 | X | X |   |   |   | X |
-----------------------------
 1 | X | X |   |   |   | S |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.574 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 6 D .
22:57:09.574 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | X |
-----------------------------
 2 | X | X |   |   |   | X |
-----------------------------
 1 | X | X |   |   |   | W |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.574 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 7 A .
22:57:09.575 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | X |
-----------------------------
 2 | X | X |   |   |   | X |
-----------------------------
 1 | X | X |   |   | W | X |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.575 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 8 D .
22:57:09.575 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | X |
-----------------------------
 2 | X | X |   |   |   | X |
-----------------------------
 1 | X | X |   |   | N | X |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.576 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 9 D .
22:57:09.576 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | X |
-----------------------------
 2 | X | X |   |   |   | X |
-----------------------------
 1 | X | X |   |   | E | X |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.576 [main] DEBUG com.xebia.mowitnow.mower.Mower - Performing the insctruction N° 10 A .
22:57:09.576 [main] DEBUG com.xebia.mowitnow.mower.Commander - A move has been detected. Here is the lawn status : 
 
 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | X |
-----------------------------
 2 | X | X |   |   |   | X |
-----------------------------
 1 | X | X |   |   | X | E |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

22:57:09.578 [main] DEBUG com.xebia.mowitnow.mower.Mower - Stopping the mower Mower Id : 1 at Poisition X:5 Y:1
Mower(s) final position(s) :
1 3 N
5 1 E
```

 [project site]: <https://mehdi-aouadi.github.io/mowitnow/>  
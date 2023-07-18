# City Navigator Application
A simple command line application written in Java that utilizes a route finding algorithm to determine the shortest and fastest routes around Los Angeles.

## Description
The application allows the user to enter a starting address and destination for a given route. The user can choose between public transportation or a car.
The application pulls information from a database and uses that information to build a graph in order to determine the shortest and fastest routes. The locations are using real world coordinates which are pulled using a third party API.

The algorithm used to find said routes is an implementation of the Floyd-Warshall algorithm. For transportation, the weights for the graph are based off of distance and time. By utilziing the algorithm, the user can receive step-by-step instructions on how to reach the destination. For public transportation, this includes wait time for the arrival of the next bus.

While the graph is built on the actual coordinates of locations across Los Angeles, the roads are not. In doing so, users can add real life locations to the graph and the roads in which they are connected. This application can be used to add a user's favorite locations in any part of the world.

The program was written entirely in Java, utilizing MySQL for the database. We also utilized the myBatis library to efficiently retrieve and manage data from our database.

### Installing
Clone or download this repository and run the program using a Java SDK (20 or greater) and an IDE.

### Executing program
Start the program by running the NavigatorCLI.java file in src/main/java/com/solvd. 

Follow the on screen instructions to utilize the program's features. The user can enter the number which corresponds to the desired option.
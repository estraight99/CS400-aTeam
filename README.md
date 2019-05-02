# Road Builder

## Author
* Dung Viet Bui, LEC 001, XTeam 22. Email: dvbui@wisc.edu (Algorithm and GUI designer)
* Eli Straight, LEC 001, XTeam 22. Email: straight2@wisc.edu (The maker of the Right Part of the GUI)
* Yuanbo Zhang, LEC 001, XTeam 22. Email: yzhang2325@wisc.edu (The maker of the Left Part of the GUI)
* Quming Wang, LEC 001, ATeam 101. Email: qwang357@wisc.edu (The co-maker of the Left Part of the GUI)

## Description
(Even though TTD Online is mentioned in the description, we actually intend to build an independent program which is not related to the design of OpenTTD or TTD Online, and this program cannot be used in the game because the data format of the program is not compatible with those in OpenTTD or TTD Online)

TTD Online is a game in which players compete as owners of transport tycoons. The map of the game can be considered as a 1000\*1000 table, each cell on the table is an empty cell or a location such as a bus station, a bank, a factory, etc. Two cells on the table can be connected by a road, and all road built in the game has the same price ($1) and are bidirectional. 

One problem which usually arises during the game is to build a path, which consists of multiple roads, to connect two specific locations. Since the map of the game is quite big, most players cannot build a "good" path between two far away locations in the game manually. 

We have written a program that helps players in this game **build the shortest path between two locations, and if there are multiple shortest paths, the program need to choose the cheapest one to build.**

## How to use this program
### Run the program
* On Windows: Double click on ```executable.jar```.
* On Linux: Open the terminal in the folder containing ```executable.jar``` and type

```java -jar executable.jar```

Dung Viet Bui cannot discover why ```executable.jar``` can run directly on Linux

## Developer Diary
### The First Meeting (April 21st, 2019)
We create a simple GUI for the program to finish Milestone #1. Dung Viet Bui implements the top part of the GUI (the title and the help button) and the ImageView for each tile on the map, Eli Straight implements the right part (the text fields), and Yuanbo Zhang implements the right part of the GUI (including the map).

The first problem we encountered is to display the map in the GUI. When we simply put the ImageView to the GridPane, the images are stacked on top of each other instead of creating the map that we expected. Yuanbo Zhang came up with a solution: adding some gap between the rows and the columns of the GridPane. This solution works on Linux and Mac; however, there is a gap between two consecutive rows of the map on Windows. We decide that it is a minor problem and move on to the next task.

### Between the First Meeting and the Second Meeting (from April 22nd, 2019 to April 27th, 2019)
Yuanbo Zhang and Eli Straight performed some minor changes to the code so that the program can be submitted for Milestone 1.

Meanwhile, Dung Viet Bui started to implement some crucial elements for the program such as the GameMap, the MapGenerator, the User, and a temporary PathFinding algorithm. Viet's initial algorithm was based on Dijkstra's algorithm, which has the complexity of O(E + Vlog(V)) with E is the number of edges and V is the number of vertices on the graph. Since V ~ the number of tiles on the map and E ~ 4V (because the degree of most vertices is 4), theoretically, the algorithm could handle a graph that has as many tiles as 10^6 in 1 second.

At the end of April 27th, 2019, both the front-end and the back-end of the program are basically done; however, the team still needed a meeting to connect these parts together.

### The Second Meeting (April 28th, 2019)
The GUI and the algorithm are put together during the meeting. We encountered some problems during this combining process:
* The Map took too long to load. The problem was the Images loaded into the program were not recycled, so each time the GameMap is moved, ~10 additional images are loaded. If the user tried to move the map too fast, then the number of new Images could grow very quickly. Dung Viet Bui decided to improve his createImageView() method by creating a "cache" array in the MapView class so Images can be reused again.
* There was little communication between the program and the user. Eli Straight created some pop-up windows to solve this problem.
* Yuanbo Zhang modified the ComboBox so that the user can switch between three maps during the game. He used the same idea from the createImageView() method to "preload" all three maps that are used in the program. While this effort increased the start-up time of the program to ~6 seconds, it now takes only miliseconds to switch between maps during the game.
* At the end of the day, Yuanbo Zhang designed some graphics for different types of locations.

### Between the Second Meeting and the Due Date (April 29th, 2019 to May 2nd, 2019)
* Dung Viet Bui improved his algorithm by using a LinkedList instead of a PriorityQueue in the Dijkstra algorithm. The path finding speed in the worst case in the biggest map decreased from ~2 seconds to less than 1 second.
* Eli Straight and Yuanbo Zhang performed some testing process and documented the code.

## The Algorithm
(Written by Dung Viet Bui)
The inputs of the algorithm are the map of the game, and the starting point and the ending point of the path that needed to be built.

In the first step, I view the map of the game as a Grid Graph with each location is a vertex on the graph, and each "adjacency" relation is an edge of the graph.

In the second step, I assign the direction for the edges on this Grid Graph. Define the distance of vertices a and b be the length of the shortest path between a and b. With every vertices a and b, a points to b if and only if (the distance from the starting point to b) + (the distance from the ending point to b) = (the distance from the starting point to the ending point). Traveling on this graph from the starting point, you will definitely reach the ending point.

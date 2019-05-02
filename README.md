# Road Builder
![Best photo of the program](https://2.pik.vn/20193967b8f8-b028-4f37-aeec-f25e9e7bd682.png)
## Author
* Dung Viet Bui, LEC 001, XTeam 22. Email: dvbui@wisc.edu (Algorithm and GUI designer)
* Eli Straight, LEC 001, XTeam 22. Email: straight2@wisc.edu (The coder of the Right Part of the GUI + some pop-up windows)
* Yuanbo Zhang, LEC 001, XTeam 22. Email: yzhang2325@wisc.edu (The coder of the Left Part of the GUI)
* Quming Wang, LEC 001, ATeam 101. Email: qwang357@wisc.edu (Graphics Designer, joined the team on April 28th, 2019 and coded nothing)

## Description
(Even though TTD Online is mentioned in the description, we actually intend to build an independent program which is not related to the design of OpenTTD or TTD Online, and this program cannot be used in the game because the data format of the program is not compatible with those in OpenTTD or TTD Online)

TTD Online is a game in which players compete as owners of transport tycoons. The map of the game can be considered as a 1000\*1000 table, each cell on the table is an empty cell or a location such as a bus station, a bank, a factory, etc. Two cells on the table can be connected by a road, and all road built in the game has the same price ($1) and are bidirectional. 

One problem which usually arises during the game is to build a path, which consists of multiple roads, to connect two specific locations. Since the map of the game is quite big, most players cannot build a "good" path between two far away locations in the game manually. 

We have written a program that helps players in this game **build the shortest path between two locations, and if there are multiple shortest paths, the program need to choose the cheapest one to build.**

## How to use this program
### Run the program
Double click on ```executable.jar```
### Reset the program
Compile and run ```MapGenerator.java```. This map generator will reset the map of the game. To reset the information of the user, use the Special Features in the program.

## Developer Diary
### The First Meeting (April 21st, 2019)
We create a simple GUI for the program to finish Milestone #1. Dung Viet Bui implements the top part of the GUI (the title and the help button) and the ImageView for each tile on the map, Eli Straight implements the right part (the text fields), and Yuanbo Zhang implements the right part of the GUI (including the map).

The first problem we encountered is to display the map in the GUI. When we simply put the ImageView to the GridPane, the images are stacked on top of each other instead of creating the map that we expected. Yuanbo Zhang came up with a solution: adding some gap between the rows and the columns of the GridPane. This solution works on Linux and Mac; however, there is a gap between two consecutive rows of the map on Windows. We decide that it is a minor problem and move on to the next task.

### Between the First Meeting and the Second Meeting (from April 22nd, 2019 to April 27th, 2019)
Yuanbo Zhang and Eli Straight performed some minor changes to the code so that the program can be submitted for Milestone 1.

Meanwhile, Dung Viet Bui started to implement some crucial elements for the program such as the GameMap, the MapGenerator, the User, and a PathFinding algorithm. Viet's algorithm is described below. Because he was bored, he also implemented the JSON Input/Output part for these crucial classes.

At the end of April 27th, 2019, both the front-end and the back-end of the program are basically done; however, the team still needed a meeting to connect these parts together.

### The Second Meeting (April 28th, 2019)
The GUI and the algorithm are put together during the meeting. We encountered some problems during this combining process:
* The Map took too long to load. The problem was the Images loaded into the program were not recycled, so each time the GameMap is moved, ~10 additional images are loaded. If the user tried to move the map too fast, then the number of new Images could grow very quickly. Dung Viet Bui decided to improve his createImageView() method by creating a "cache" array in the MapView class so Images can be reused again.
* There was little communication between the program and the user. Eli Straight created some pop-up windows to solve this problem.
* Yuanbo Zhang modified the ComboBox so that the user can switch between three maps during the game. He used the same idea from the createImageView() method to "preload" all three maps that are used in the program. While this effort increased the start-up time of the program to ~6 seconds, it now takes only milliseconds to switch between maps during the game.

The project is basically done at this point.

* At the end of the day, Quming Wang joined the team and designed the graphics for different types of location in the game.

### Between the Second Meeting and the Due Date (April 29th, 2019 to May 2nd, 2019)
* Dung Viet Bui improved his algorithm by using a LinkedList instead of a PriorityQueue in Dijkstra's algorithm. The pathfinding speed in the worst case in the biggest map decreased from ~2 seconds to less than 1 second.
* Eli Straight and Yuanbo Zhang performed some testing process and documented the code.

## The Algorithm
(Written by Dung Viet Bui)

The inputs of the algorithm are the map of the game, and the starting point and the ending point of the path that needed to be built.

In the first step, I view the map of the game as a Grid Graph with each location is a vertex on the graph, and each "adjacency" relation is an edge of the graph.

In the second step, I assign the direction for the edges on this Grid Graph. Define the distance of a and b be the length of the shortest path between a and b. With every vertices a and b, a points to b if and only if (the distance from the starting point to b) + (the distance from the ending point to b) = (the distance from the starting point to the ending point). Traveling on this graph from the starting point, you will definitely reach the ending point on the shortest path.

In the third step, I assign the cost to the edges of the above digraph. If there is a road between a and b, the cost of the edge (a,b) is 0 because I do not need to build any road. If there is not a road between a and b, the cost of the edge (a,b) is 1.

(All the above steps are done implicitly in my mind, I do not actually make a new graph for each step).

In the fourth step, I run Dijkstra's algorithm on the graph to find the shortest path from the starting point to the ending point. This shortest path is actually the cheapest-shortest path to build between the starting point and the ending point.

The complexity of this algorithm is O(E + Vlog(V)) with V is the number of vertices on the graph, and E is the number of edges on the graph. Since V = n^2 and E = 4V (n is the length / the width of the GameMap), the complexity of this algorithm can also be expressed as O(4n^2 + n^2log(n^2) = O(n^2log(n)).

In this specific problem, the PriorityQueue in Dijkstra's algorithm can be replaced with a LinkedList, for when a vertex is reached through an edge with cost 0, that vertex can always be put at the "top" of the PriorityQueue, and when a vertex is reached through an edge with cost 1, that vertex can always be put at the "bottom" part of the PriorityQueue. When the PriorityQueue is replaced with the LinkedList, the complexity of Dijkstra's algorithm decreases to O(n^2). The new algorithm produced by this trick is called BFS 0-1 on some website.

## External Assistance
### Dung Viet Bui
* To parse the JSON files, he used the tutorial ![from GeeksForGeeks](https://www.geeksforgeeks.org/parse-json-java/).
* To on-click listeners to the ImageView, he used some help ![from StackOverFlow](https://stackoverflow.com/questions/25550518/add-eventhandler-to-imageview-contained-in-tilepane-contained-in-vbox)
* To create custom pop-ups for the project, he used some help from ![this tutorial](https://o7planning.org/en/11533/opening-a-new-window-in-javafx).
* To detect the closing action, he used some help ![from StackOverFlow](https://stackoverflow.com/questions/26619566/javafx-stage-close-handler)

### Yuanbo Zhang
* To display the map properly, he used some help from ![this tutorial](https://docs.oracle.com/javafx/2/ui_controls/accordion-titledpane.htm#CACGBAHI).

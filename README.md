# Road Builder

## Author
* Dung Viet Bui, LEC 001, XTeam 22. Email: dvbui@wisc.edu
* Eli Straight, LEC 001, XTeam 22. Email: straight2@wisc.edu
* Yuanbo Zhang, LEC 001, XTeam 22. Email:

## Description
(Even though TTD Online is mentioned in the description, we actually intend to build an independent program which is not related to the design of OpenTTD or TTD Online, and this program cannot be used in the game because the data format of the program is not compatible with those in OpenTTD or TTD Online)

TTD Online is a game in which players compete as owners of transport tycoons. The map of the game can be considered as a 1000\*1000 table, each cell on the table is an empty cell or a location such as a bus station, a bank, a factory, etc. Two cells on the table can be connected by a road, and all road built in the game has the same price ($1) and are bidirectional. 

One problem which usually arises during the game is to build a path, which consists of multiple roads, to connect two specific locations. Since the map of the game is quite big, most players cannot build a "good" path between two far away locations in the game manually. 

We have written a program that helps players in this game **build the shortest path between two locations, and if there are multiple shortest paths, the program need to choose the cheapest one to build.**

## Developer Diary
### First Meeting (April 21st, 2019)
We create a simple GUI for the program to finish Milestone #1. 

The first problem we encountered is to display the map in the GUI. When we simply put the ImageView to the GridPane, the images are stacked on top of each other instead of creating the map that we expected. Yuanbo Zhang came up with a solution: adding some gap between the rows and the columns of the GridPane. This solution works on Linux and Mac; however, there is a gap between two consecutive rows of the map on Windows. We decide that it is a minor problem and move on to the next task.

### Between the First Meeting and the Second Meeting (from April 22nd, 2019 to April 27th, 2019)
Yuanbo Zhang and Eli Straight performed some minor changes to the code so that the program can be submitted for Milestone 1.

Meanwhile, Dung Viet Bui started to implement some crucial elements for the program such as the GameMap, the MapGenerator, the User, and a temporary PathFinding algorithm. Viet's initial algorithm was based on Dijkstra's algorithm, which has the complexity of O(E + Vlog(V)) with E is the number of edges and V is the number of vertices on the graph. Since V ~ the number of tiles on the map and E ~ 4V (because the degree of most vertices is 4), theoretically, the algorithm could handle a graph that has as many tiles as 10^6 in 1 second.

At the end of April 27th, 2019, both the front-end and the back-end of the program are basically done; however, the team still needed a meeting to connect these parts together.




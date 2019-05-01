# Road Builder

## Author
* Dung Viet Bui, LEC 001, XTeam 22. Email: dvbui@wisc.edu
* Eli Straight, LEC 001, XTeam 22. Email: 
* Yuanbo Zhang, LEC 001, XTeam 22. Email:

## Description
(Even though TTD Online is mentioned in the description, we actually intend to build an independent program which is not related to the design of OpenTTD or TTD Online, and this program cannot be used in the game because the data format of the program is not compatible with those in OpenTTD or TTD Online)

TTD Online is a game in which players compete as owners of transport tycoons. The map of the game can be considered as a 1000\*1000 table, each cell on the table is an empty cell or a location such as a bus station, a bank, a factory, etc. Two cells on the table can be connected by a road, and all road built in the game has the same price ($1) and are bidirectional. 

One problem which usually arises during the game is to build a path, which consists of multiple roads, to connect two specific locations. Since the map of the game is quite big, most players cannot build a "good" path between two far away locations in the game manually. 

We have written a program that helps players in this game **build the shortest path between two locations, and if there are multiple shortest paths, the program need to choose the cheapest one to build.**

## Developer Diary
### First Meeting (April 21st, 2019)
We create a simple GUI for the program to finish Milestone #1


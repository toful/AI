# AI
Artificial Intelligence exercises carried out during the AI subject, URV

## Informed Search
Studying the different ways to find a path for moving as fast as we can in a different highs map.

There are some diferent algorithms implemented:
* Breadth Uninformed Search
* Depth Uninformed Search
* Best First Algorithm
* A* Algorithm

Some heuristic functions have also been implemented.

The map is initialized from a text file. The different positions in each line are parsed by a comma, and each position has a single value ( 0-9 indicates the hight in that positon and -1 indicates a wrong positon (a cliff) ). Here there is an example:
```
1, 0, -1, 1, 3, 2, 3, 4, 3, 1
2, 1, -1, 2, 4, 2, 2, 4, 2, 2
5, 3, -1, 2, 3, 2, -1, 3, 3, 3
3, 3, 1, 3, 4, 3, -1, 1, 2, 2
2, 2, 2, 3, 6, 4, -1, 1, 2, 1
-1, -1, -1, -1, 3, 3, -1, 0, 2, -1
-1, -1, -1, -1, 2, 4, -1, 2, 2, -1
2, 3, 4, 3, 1, 3, -1, 3, 2, -1
3, 5, 6, 5, 2, 3, -1, 5, 3, -1
5, 6, 7, 6, 4, 4, -1, 6, 4, 5
```
We can move in an horizontal or vertical way. The moving time for each step will be:
* 1 + ( the heights difference  between the destination and the origin ), if the difference is positive or 0.
* 1/2 unit, if the difference is negative.
* We can not move to a position with cliff (-1).


## Author

* **Cristòfol Daudén Esmel** - [toful](https://github.com/toful)

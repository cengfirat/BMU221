import java.util.ArrayDeque;

import java.util.Queue;

 

// queue node used in BFS

class Node

{

                // (x, y) represents matrix cell coordinates

                // dist represent its minimum distance from the source

                int x, y, dist;

 

                Node(int x, int y, int dist) {

                               this.x = x;

                               this.y = y;

                               this.dist = dist;

                }

};

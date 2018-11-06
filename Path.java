package pathByQueue;

public class Path {
    private static class Pos {
        public int x, y;
        public Pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    private Maze maze;
    private ListQueue<Pos> queue;
    private final static int dx[] = new int[]{-1, 1,  0, 0};
    private final static int dy[] = new int[]{ 0, 0, -1, 1};
    
    public Path(Maze map) {
        this.maze = map;
        queue = new ListQueue<Pos>();
    }
    
    public void findPath(int fromX, int fromY, int toX, int toY) {
        maze.initDistance();
        if(maze.map[toY][toX]) {
            maze.distance[toY][toX] = 0;
            queue.enqueue(new Pos(toX, toY));
        }
        while(!queue.isEmpty()) {
            //TODO: remove a marked position from the queue
            //TODO: break if (fromX, fromY) is reached
        	Pos p = queue.dequeue();
            if(p.x == fromX && p.y == fromY) break; // check if reached 벽에 닿으
            for(int i = 0; i < dx.length; i++) {
                int x = p.x + dx[i];    //neighbor's x
                int y = p.y + dy[i];    //neighbor's y
                if(x >= 0 && x < maze.col && y >= 0 && y < maze.row && maze.map[y][x]
                		&& maze.distance[y][x] > maze.distance[p.y][p.x]+1) { 
                	maze.distance[y][x] = maze.distance[p.y][p.x]+1;
                	queue.enqueue(new Pos(x,y)); //adding to queue
                }
                
                //TODO: check if the neighboring cell can be marked
                //      - 0 <= x < maze.col, 0 <= y < maze.row,
                //      - maze.map[y][x],
                //      - maze.distance[y][x] < maze.distance[p.y][p.x]+1
                //TODO: if a neighboring cell can be marked,
                //      mark it and add it to the queue
            }
        }
    }
}

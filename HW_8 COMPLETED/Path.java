 

public class Path {
    private int[][] dist;
    private World.CellState[][] state;
    private int ny, nx;
    private int queueType;
    
    public Path(int[][] dist, World.CellState[][] state) {
        this.dist = dist;
        this.state = state;
        ny = state.length;
        nx = state[0].length;
    }
    
    //Prefer dots than empty cells
    protected int cellDist(int x, int y) {
        return state[y][x] == World.CellState.Dot ? 1 : 2;
    }
    
    public void findDistance(Pos from) {
        //For testing, try three different types of queues
        Queue<Pos> queue = null;
        switch(queueType) {
        case 0: queue = new ListQueue<Pos>();   break;
        case 1: queue = new HeapQueue<Pos>();   break;
        case 2: queue = new RBTreeQueue<Pos>(); break;
        }
        queueType = (queueType + 1) % 3;
       
        //initialize dist to max int
        for(int y = 0; y < ny; y++)
            for(int x = 0; x < nx; x++)
                dist[y][x] = 998;//Integer.MAX_VALUE;

        queue.enqueue(from);
        dist[from.y][from.x] = 0;

        while(!queue.isEmpty()){
            Pos p = queue.dequeue();
            for(int i = 0; i < World.DX.length; i++) {
                int x = p.x + World.DX[i];
                int y = p.y + World.DY[i];
                if(x >= 0 && x < nx && y >= 0 && y < ny && state[y][x] != World.CellState.Wall
                        && dist[y][x] > dist[p.y][p.x] + cellDist(x,y)) { 
                    dist[y][x] = dist[p.y][p.x]+cellDist(x,y);
                    queue.enqueue(new Pos(x,y,ny*y+x)); //adding to queue

                }
            }
                
        }
        //TODO: - Starting from the 'from' position,
        //        update dist[y][x] for all y and x such that
        //        dist[y][x] = dist[p.y][p.x] + d, where
        //        p is the position dequeued from queue and
        //        d is cellDist(x, y) value
        //      - Updating dist[y][x] should continue until
        //        queue becomes empty
        //
    }

    
    //Hopefully, this can help your debugging
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < ny; y++) {
            for(int x = 0; x < nx; x++) {
                if(state[y][x] == World.CellState.Wall)
                    sb.append("###");
                else
                    sb.append(String.format("%3d", dist[y][x]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

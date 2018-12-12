 

import java.util.Random;

public class World {
    public static int width;
    public static int height;
    public static final int NUM_GHOST = 4;
    public static final int[] DX = new int[] {0, 1, 0, -1};  //NESW
    public static final int[] DY = new int[] {1, 0, -1, 0};  //NESW
    private static Random rand = new Random();
    
    
    public enum CellState {
        Empty, Wall, Dot, PowerPellet
    }
    public static class Block {
        private CellState state;
        private Pos pos;
        public CellState getState() { return state; }
        public Pos getPos() { return pos; }
    }
    
    private static class Map {
        private CellState state[][];
        private int distance[][];
        private int dotCount;
        
        public Map(char[][] wall) {
            init(wall);
        }
        public void init(char[][] wall) {
            dotCount = 0;
            height = wall.length;
            width = wall[0].length;
            state = new CellState[height][width];
            distance = new int[height][width];
            for(int y = 0; y < height; y++)
                for(int x = 0; x < width; x++) {
                    if(wall[y][x] == '#')
                        state[y][x] = CellState.Wall;
                    else if(wall[y][x] == '*')
                        state[y][x] = CellState.PowerPellet;
                    else {
                        state[y][x] = CellState.Dot;
                        dotCount++;
                    }
                    distance[y][x] = Integer.MAX_VALUE;
                }
        }
        public CellState getState(int x, int y) { return state[y][x]; }
        public CellState getState(Pos pos)      { return state[pos.y][pos.x]; }
        public int getDistance(Pos pos)         { return distance[pos.y][pos.x]; }
        public void updateDistance(Pos from)    { new Path(distance, state).findDistance(from); }
        public int getDotCount()                { return dotCount; }
        public Pos farthest() {
            int max = 0, maxx = 0, maxy = 0;
            for(int y = 0; y < height; y++)
                for(int x = 0; x < width; x++)
                    if(state[y][x] != CellState.Wall && distance[y][x] > max) {
                        max = distance[y][x];
                        maxx = x; maxy = y;
                    }
            return new Pos(maxx, maxy);
        }
        public CellState enterCell(Pos pos) {
            CellState ret = state[pos.y][pos.x];
            if(state[pos.y][pos.x] == CellState.Dot)
                dotCount--;
            state[pos.y][pos.x] = CellState.Empty;
            return ret;
        }
    }
    
    public static abstract class Movable {
        protected int dir;
        protected Pos pos;
        
        public int getDir()     { return dir; }
        public Pos getPos()     { return pos; }
        public Pos getNextPos() { return getNextPos(dir); }
        public Pos getNextPos(int dir) {
            return new Pos((pos.x + DX[dir] + width)  % width,
                           (pos.y + DY[dir] + height) % height);
        }
    }
    
    private static class PacMan extends Movable {
        private Map map;
        
        public PacMan(Map map) {
            init(map);
        }
        public void init(Map map) {
            this.map = map;
            pos = new Pos(0, height/2);
            dir = 1; //E
            map.enterCell(pos);
        }
        public void turnNorth() { dir = 0; }
        public void turnEast()  { dir = 1; }
        public void turnSouth() { dir = 2; }
        public void turnWest()  { dir = 3; }
        public Pos getNextPos() {
            Pos p = getNextPos(dir);
            if(map.getState(p) != CellState.Wall)
                return p;
            return pos;
        }
        public void move(boolean blueMode) {
            Pos p = getNextPos();
            pos = p;
            map.updateDistance(pos);
            if(blueMode)
                map.updateDistance(map.farthest());
        }
    }
    
    private static class Ghost extends Movable {
        private Map map;
        
        public Ghost(Map map) {
            init(map);
        }
        public void init(Map map) {
            this.map = map;
            pos = new Pos(width/2, height/2);
        }
        public Pos getNextPos() {
            int curDist = map.getDistance(pos) + (rand.nextInt(3) - 1); 
            for(int i = 0; i < 4; i++) {
                Pos p = getNextPos(i);
                if( map.getState(p) != CellState.Wall &&
                    map.getDistance(p) < curDist) {
                    dir = i;
                    return p;
                }
            }
            return pos;
        }
        public void move() {
            pos = getNextPos();
        }
    }
    
    private PacMan pacMan;
    private Ghost ghost[];
    private char[][] chMap;
    private Map map;

    private int stageCount;
    private int score;
    private int blueMode;
    private boolean gameOver;
    
    public World(char[][] map) {
        init(map);
    }
    public void init(char[][] chMap) {
        this.chMap = chMap;
        this.map= new Map(chMap);
        pacMan = new PacMan(map);
        ghost = new Ghost[NUM_GHOST];
        for(int i = 0; i < NUM_GHOST; i++)
            ghost[i] = new Ghost(map);
        stageCount = 0;
        score = 0;
        blueMode = 0;
        gameOver = false;
    }
    public int getScore()                    { return score; }
    public int getStageCount()               { return stageCount; }
    public boolean getBlueMode()             { return blueMode > 0; }
    public Movable getPacMan()               { return pacMan; }
    public Movable getGhost(int i)           { return ghost[i]; }
    public CellState getState(int x, int y)  { return map.getState(x, y); }
    public CellState getState(Pos pos)       { return map.getState(pos); }
    public void turnNorth()                  { pacMan.turnNorth(); }
    public void turnEast()                   { pacMan.turnEast(); }
    public void turnSouth()                  { pacMan.turnSouth(); }
    public void turnWest()                   { pacMan.turnWest(); }
    public boolean step() {
        if(gameOver)
            return true;
        
        //update position
        pacMan.move(blueMode > 0);
        int gi = rand.nextInt(ghost.length);
        ghost[gi].move();
        
        //check if pacMan or any ghost dies
        Pos pos = pacMan.getPos();
        for(int i = 0; i < ghost.length; i++) {
            if(pos.equals(ghost[i].getPos())) {
                if(blueMode > 0) {
                    ghost[i].init(map);
                    score += 10;
                }
                else
                    return (gameOver = true);
            }
        }
        
        //eat dot/power pellet
        if(blueMode > 0)
            blueMode--;
        World.CellState cs = map.enterCell(pos);
        if(cs == World.CellState.Dot) {
            score++;
            if(map.getDotCount() == 0) {
                init(chMap);
                stageCount++;
            }
        }
        else if(cs == World.CellState.PowerPellet) {
            score += 10;
            blueMode += 50;
        }
        return false;
    }
}

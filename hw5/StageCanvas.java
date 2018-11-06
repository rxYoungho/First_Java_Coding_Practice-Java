 

import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

public class StageCanvas extends Canvas {
    private final int CELL_W = 10;
    private final int CELL_H = 10;
    
    private SnakeWorld world;
    
    public StageCanvas(SnakeWorld stage) {
        this.world = stage;
        this.setHeight(CELL_H * SnakeWorld.HEIGHT);
        this.setWidth(CELL_W * SnakeWorld.WIDTH);
    }
    public void repaint() {
        GraphicsContext gc = getGraphicsContext2D();
        int width  = (int) getWidth();
        int height = (int) getHeight();
        gc.clearRect(0, 0, width,  height);
        
        for(SnakeWorld.Pos pos: world.getWallPos())
            drawCell(gc, pos, Color.DARKBLUE);
        
        drawCell(gc, world.getApplePos(), Color.RED);
        
        for(SnakeWorld.Pos pos: world.getSnakePos())
            drawCell(gc, pos, Color.LIME);
    }
    
    private void drawCell(GraphicsContext gc, SnakeWorld.Pos pos, Color color) {
        gc.setFill(color);
        gc.fillRect(pos.x * CELL_W,  pos.y * CELL_H,  CELL_W, CELL_H);
    }
}

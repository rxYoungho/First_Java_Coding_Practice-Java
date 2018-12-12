 

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;

public class StageCanvas extends Canvas {
    private final int CELL_W = 30;  //multiple of 3
    private final int CELL_H = 30;
    
    private static final String IMG_NAME[][] = new String[][] {
        new String[] { "BlinkyS.png", "PinkyS.png", "InkyS.png", "ClydeS.png", "PacManS.png" },
        new String[] { "BlinkyE.png", "PinkyE.png", "InkyE.png", "ClydeE.png", "PacManE.png" },
        new String[] { "BlinkyN.png", "PinkyN.png", "InkyN.png", "ClydeN.png", "PacManN.png" },
        new String[] { "BlinkyW.png", "PinkyW.png", "InkyW.png", "ClydeW.png", "PacManW.png" },
        new String[] { "BlinkyN.png", "PinkyN.png", "InkyN.png", "ClydeN.png", "PacMan.png" },
        new String[] { "BlinkyW.png", "PinkyW.png", "InkyW.png", "ClydeW.png", "PacMan.png" },
        new String[] { "BlinkyS.png", "PinkyS.png", "InkyS.png", "ClydeS.png", "PacMan.png" },
        new String[] { "BlinkyE.png", "PinkyE.png", "InkyE.png", "ClydeE.png", "PacMan.png" },
        //blue mode
        new String[] { "BlueS.png", "BlueS.png", "BlueS.png", "BlueS.png", "PacManS.png" },
        new String[] { "BlueE.png", "BlueE.png", "BlueE.png", "BlueE.png", "PacManE.png" },
        new String[] { "BlueN.png", "BlueN.png", "BlueN.png", "BlueN.png", "PacManN.png" },
        new String[] { "BlueW.png", "BlueW.png", "BlueW.png", "BlueW.png", "PacManW.png" },
        new String[] { "BlueN.png", "BlueN.png", "BlueN.png", "BlueN.png", "PacMan.png" },
        new String[] { "BlueW.png", "BlueW.png", "BlueW.png", "BlueW.png", "PacMan.png" },
        new String[] { "BlueS.png", "BlueS.png", "BlueS.png", "BlueS.png", "PacMan.png" },
        new String[] { "BlueE.png", "BlueE.png", "BlueE.png", "BlueE.png", "PacMan.png" },
    };
    
    private World world;
    private Image imageSet[][][];
    private Image image[][];
    private Timeline timeline;
    
    public StageCanvas(World world) {
        this.world = world;
        this.setHeight(CELL_H * World.height);
        this.setWidth(CELL_W * World.width);
        
        imageSet = new Image[4][4][IMG_NAME[0].length];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < IMG_NAME[0].length; j++) {
                imageSet[0][i][j] = new Image(StageCanvas.class.getResource(IMG_NAME[i   ][j]).toString());
                imageSet[1][i][j] = new Image(StageCanvas.class.getResource(IMG_NAME[i+4 ][j]).toString());
                imageSet[2][i][j] = new Image(StageCanvas.class.getResource(IMG_NAME[i+8 ][j]).toString());
                imageSet[3][i][j] = new Image(StageCanvas.class.getResource(IMG_NAME[i+12][j]).toString());
            }
        }
        image = imageSet[0];
       
        timeline = new Timeline(
                new KeyFrame( Duration.millis(100), e -> {
                    image = world.getBlueMode() ? imageSet[2] : imageSet[0];
                    repaint();
                }), 
                new KeyFrame( Duration.millis(700), e -> {
                    image = world.getBlueMode() ? imageSet[3] : imageSet[1];
                    repaint();
                }) );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public void repaint() {
        GraphicsContext gc = getGraphicsContext2D();
        int width  = (int) getWidth();
        int height = (int) getHeight();
        
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width,  height);
        
        for(int y = 0; y < World.height; y++) {
            for(int x = 0; x < World.width; x++) {
                Pos pos = new Pos(x, y);
                drawDot(gc, pos);
                drawWall(gc, pos);                
            }
        }
        
        Pos posPacMan = world.getPacMan().getPos(); 
        drawImage(gc, posPacMan, world.getPacMan().getDir(), World.NUM_GHOST);
        
        for(int i = 0; i < World.NUM_GHOST; i++) {
            Pos posGhost = world.getGhost(i).getPos();
            BlendMode bm = posPacMan.equals(posGhost) ? BlendMode.ADD : BlendMode.SRC_OVER;
            drawImage(gc, world.getGhost(i).getPos(), world.getGhost(i).getDir(), i, bm);
        }
    }
    
    private void drawDot(GraphicsContext gc, Pos pos) {
        if(world.getState(pos.x, pos.y) == World.CellState.Dot) {
            gc.setFill(Color.WHITE);
            gc.fillOval(pos.x * CELL_W + CELL_W/2 - 2,  pos.y * CELL_H + CELL_H/2 - 2,  4, 4);
        }
        else if(world.getState(pos.x, pos.y) == World.CellState.PowerPellet) {
            gc.setFill(Color.ORANGE);
            gc.fillOval(pos.x * CELL_W + CELL_W/2 - 4,  pos.y * CELL_H + CELL_H/2 - 4,  8, 8);
        }
    }
    
    private void drawImage(GraphicsContext gc, Pos pos, int dir, int imgIndex) {
        drawImage(gc, pos, dir, imgIndex, BlendMode.SRC_OVER);
    }
    private void drawImage(GraphicsContext gc, Pos pos, int dir, int imgIndex, BlendMode bm) {
        gc.setGlobalBlendMode(bm);
        gc.drawImage(image[dir][imgIndex],  pos.x * CELL_W,  pos.y * CELL_H, CELL_W, CELL_H);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.setGlobalAlpha(1.0);
    }
    
    private boolean validX(int x) { return 0 <= x && x < World.width; }
    private boolean validY(int y) { return 0 <= y && y < World.height; }
    private void drawWall(GraphicsContext gc, Pos pos) {
        if(world.getState(pos.x, pos.y) != World.CellState.Wall)
           return;
        
        int[] dx = new int[] { 1, -1, -1,  1};
        int[] dy = new int[] {-1, -1,  1,  1};
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        for(int i = 0; i < 4; i++) {
            int nx = pos.x + dx[i];
            int ny = pos.y + dy[i];
            
            if(validY(ny) && world.getState(pos.x, ny) == World.CellState.Wall &&     //outside arc
               validX(nx) && world.getState(nx, pos.y) == World.CellState.Wall ) {
                if(world.getState(nx, ny) != World.CellState.Wall) {
                    int cx = pos.x * CELL_W + CELL_W/3 + dx[i] * CELL_W/2;
                    int cy = pos.y * CELL_H + CELL_H/3 + dy[i] * CELL_H/2;
                    gc.strokeArc(cx, cy, CELL_W/3, CELL_H/3, i * 90 + 180, 90, ArcType.OPEN);
                }
            }
            else if((!validY(ny) || world.getState(pos.x, ny) != World.CellState.Wall) && //inside arc
                    (!validX(nx) || world.getState(nx, pos.y) != World.CellState.Wall) ) {
                int cx = pos.x * CELL_W + CELL_W/3 + dx[i] * CELL_W/6;
                int cy = pos.y * CELL_H + CELL_H/3 + dy[i] * CELL_H/6;
                gc.strokeArc(cx, cy, CELL_W/3, CELL_H/3, i * 90, 90, ArcType.OPEN);
            }
            else if((!validY(ny) || world.getState(pos.x, ny) == World.CellState.Wall) && //v line
                    (!validX(nx) || world.getState(nx, pos.y) != World.CellState.Wall) ) {
                int x  = pos.x * CELL_W + CELL_W/2 + dx[i] * CELL_W/3;
                int y1 = pos.y * CELL_H + CELL_H/2 + dy[i] * CELL_H/2;
                int y2 = pos.y * CELL_H + CELL_H/2 + dy[i] * CELL_H/6;
                gc.strokeLine(x, y1, x, y2);
            }
            else if((!validY(ny) || world.getState(pos.x, ny) != World.CellState.Wall) && //h line
                    (!validX(nx) || world.getState(nx, pos.y) == World.CellState.Wall) ) {
                int y  = pos.y * CELL_H + CELL_H/2 + dy[i] * CELL_H/3;
                int x1 = pos.x * CELL_W + CELL_W/2 + dx[i] * CELL_W/2;
                int x2 = pos.x * CELL_W + CELL_W/2 + dx[i] * CELL_W/6;
                gc.strokeLine(x1, y, x2, y);
            }
            if(!validX(nx) || world.getState(nx, pos.y) != World.CellState.Wall) {
                int x  = pos.x * CELL_W + CELL_W/2 + dx[i] * CELL_W/3;
                int y1 = pos.y * CELL_H + CELL_H/2 - CELL_H/6;
                int y2 = pos.y * CELL_H + CELL_H/2 + CELL_H/6;
                gc.strokeLine(x, y1, x, y2);
            }
            if(!validY(ny) || world.getState(pos.x, ny) != World.CellState.Wall) {
                int y  = pos.y * CELL_H + CELL_H/2 + dy[i] * CELL_H/3;
                int x1 = pos.x * CELL_W + CELL_W/2 - CELL_W/6;
                int x2 = pos.x * CELL_W + CELL_W/2 + CELL_W/6;
                gc.strokeLine(x1, y, x2, y);
            }
        }
    }
}

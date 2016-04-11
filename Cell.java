package cgol;
import java.awt.Color;
import java.awt.Graphics;
/**
 *
 * @author matth
 */
public class Cell {
    //declaring the int variables for x/y axis
    private int x;
    private int y;
    //generating the boolean to tell if the creature is alive or dead
    private boolean alive;
    
    private boolean nextround;
    //declaring the size of each cell/pixel as 10
    static  int size = 5;
    static boolean grid = true;
  
    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        
    }
    public boolean isAlive(){
        return alive;
    }
    
    public void setAlive(boolean alive){
        this.alive = alive;
    }
       
    public void setNextRound(boolean nextround){
        this.nextround = nextround;
    }
    
    public void nextRound(){
        alive = nextround;
    }
    
    public void draw(Graphics g){
        
        //if key pressed i.e. 'g' then remove grid
        if(grid){
           g.setColor(Color.BLUE);
           g.drawRect(x * size, y * size, size, size);
        
            //if creature is alive then its colour stays the same (blue)
            if(alive) g.setColor(Color.BLUE);
            //if the creature is dead then its colour changes to white
            else g.setColor(Color.WHITE);
            g.fillRect(x * size+1, y * size+1, size - 1, size - 1); 
        }else{
            if(alive) g.setColor(Color.BLUE);
            //if the creature is dead then its colour changes to white
            else g.setColor(Color.WHITE);
            g.fillRect(x * size, y * size, size, size); 
        }
        
    }
}

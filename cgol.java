package cgol;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



/**
 *
 * @author matth
 */
public class Simulation implements KeyListener, MouseListener, MouseMotionListener{
    private Cell[][] cells;
    private Random random;
    //setting the width and height of the simulation
    private int width = Cgol.width/Cell.size;
    private int height = Cgol.height/Cell.size;
    private int generation;
    public int button;
    //private int button;
    private boolean go;
    
    public Simulation(){
        random = new Random();
        cells = new Cell[width][height];
        for(int x = 0; x < width; x++){
            for(int i = 0; i < height; i++){
                cells[x][i] = new Cell(x, i);
                cells[x][i].setAlive(random.nextBoolean());
            }
        }
    }
    //updating each pixel
    public void update(){
        //if go is true then run through code. else dont
        //this is used for pausing the game
        if(go){
            generation++;
            for(int x = 0; x<width; x++){
                for(int y = 0; y<height; y++){
                int mx = x - 1;
                if(mx < 0) mx = width - 1;
                int my = y - 1;
                if(my < 0) my = height - 1;
                int gx = (x + 1) % width;
                int gy = (y + 1)% height;
               
                //generating an alive counter
                int alivecounter = 0;
               
                //checking if relative cells have a creature which is alive inside it
                if(cells[mx][my].isAlive()) alivecounter++;
                if(cells[mx][y].isAlive()) alivecounter++;
                if(cells[mx][gy].isAlive()) alivecounter++;
                if(cells[x][my].isAlive()) alivecounter++;
                if(cells[x][gy].isAlive()) alivecounter++;
                if(cells[gx][my].isAlive()) alivecounter++;
                if(cells[gx][y].isAlive()) alivecounter++;
                if(cells[gx][gy].isAlive()) alivecounter++;
                if(alivecounter < 2||alivecounter > 3) cells[x][y].setNextRound(false);
                else if(alivecounter == 3) cells[x][y].setNextRound(true);
                }
            }
            for(int x = 0; x<width; x++){
                for(int y = 0; y<height; y++){
                    cells[x][y].nextRound();
                }
            }
        }
    }
    
    public void draw(Graphics g){
        for(int x = 0; x<width; x++){
            for(int y = 0; y<height; y++){
                cells[x][y].draw(g);
            }
        }
        g.setColor(Color.RED);
        //setting font
        g.setFont(new Font("SansSerif", Font.BOLD, 25));
        g.drawString("" + generation, 10, 10+g.getFont().getSize());
    }
    @Override
    public void keyPressed(KeyEvent e){
        
    }
    @Override
    public void keyReleased(KeyEvent e){
        //if key G is released. VK_G is the key that is released. in this case G
        //VK - VIRTUAL KEYBOARD
        if(e.getKeyCode() == KeyEvent.VK_G){
            if(Cell.grid) Cell.grid = false;
            else Cell.grid = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_R){
            for(int x = 0; x<width; x++){
                for(int y = 0; y<height; y++){
                    cells[x][y].setAlive(random.nextBoolean());
                }
            }
        }
        //if key E is pressed then kill everything (make everything alive equal to false)
        if(e.getKeyCode()==KeyEvent.VK_E){
            for(int x = 0; x<width; x++){
                for(int y =0; y<height; y++){
                    cells[x][y].setAlive(false);
                }
            }
        }
                //if spacebar is pressed and if go, then go = false. else go = true

        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            if(go) go = false;
            else go = true;
        }
    }
    //overriding class exceptions and choosing what happens if a specific key is typed or mouse button is pressed
    @Override
    public void keyTyped(KeyEvent e){
        
    }
    @Override
    public void mouseDragged(MouseEvent e){
        
        //System.out.println(true);
        if(!go){
          int mx = e.getX()/Cell.size;
          int my = e.getY()/Cell.size;
          if(button== 1)cells[mx][my].setAlive(true);
          else cells[mx][my].setAlive(false);
        }
    }
    @Override
    public void mouseMoved(MouseEvent e){
        // System.out.println(button);
        //removes color on cell which has been coloured by mouse being dragged over it
        if(!go){
          int mx = e.getX()/Cell.size;
          int my = e.getY()/Cell.size;
          if(button== 1)cells[mx][my].setAlive(true);
          else if (button != -1)cells[mx][my].setAlive(false);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e){
        
    }
    @Override
    public void mouseEntered(MouseEvent e){
        
    }
    @Override
    public void mouseExited(MouseEvent e){
        
    }
    @Override
    public void mousePressed(MouseEvent e){
        button = e.getButton();
        if(!go){
          int mx = e.getX()/Cell.size;
          int my = e.getY()/Cell.size;
          if(button== 1)cells[mx][my].setAlive(true);
          else cells[mx][my].setAlive(false);
        }
    }
    @Override
    public void mouseReleased(MouseEvent e){
        //deletes color on cell
        button = -1;
    }
    
}

package cgol;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
/**
 *
 * @author matth
 */
public class Frame extends JFrame {
    
    private Screen s;
    private Simulation sim;
    private float tslu;
    private float PAUSETIME = 0.05f;
    
    
    public Frame(){
        //setting the JFrame setUndecorated to false so the JFrame has exit buttons, minimie buttons etc...
        setUndecorated(false);
        //option to allow user to choose the cell size
        String input = JOptionPane.showInputDialog(this, "Cell size?");
        try{
           Cell.size = Integer.parseInt(input);
        }catch(Exception e){
            System.exit(0);
        }
        input = JOptionPane.showInputDialog(this, "Pausetime?");
        try{
            PAUSETIME = Float.parseFloat(input);
        }catch(Exception e){
            System.exit(0);
        }
        setExtendedState(MAXIMIZED_BOTH);
        
        
    }
    //Creating a new screen
    public void createScreen(){
        //importing and declaring properties from the simulation class
        sim = new Simulation();
        addKeyListener(sim);
        addMouseListener(sim);
        addMouseListener(sim);
        addMouseMotionListener(sim);

        //assigning the variable s to new Screen
        s = new Screen();
        //setting the bounds of the screen (s)
        s.setBounds(0, 0, Cgol.width, Cgol.height);
        //adding the screen s
        add(s);
    }
    public void update(float tslf){
        tslu += tslf;
        if(tslu > PAUSETIME){
            sim.update();
            tslu = 0;
        }
        sim.update();
    }
    public void repaint(){
        s.repaint();
    }
    
    private class Screen extends JLabel{
        @Override
        //creating a paint component
        public void paintComponent(Graphics g){
            //calling methods from parent. in this case the paintComponent method and g variable
            super.paintComponent(g);
            
            //setting the colour of g as RED
            //g.setColor(Color.RED);
            //assigning a rectangle to g and filling it
            //g.fillRect(0, 0, 50, 50);
            
            sim.draw(g);
        }
    }
}

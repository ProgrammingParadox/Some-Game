package GUI.UI;

import javax.swing.JFrame;
import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener {
  public boolean pressed = false;
  public boolean in = false;
  public boolean click = false;

  public int x = 0;
  public int y = 0;

  public Mouse(JFrame frame) {
    frame.addMouseListener(this);
    frame.addMouseMotionListener(this);
  }

  public void mouseClicked(MouseEvent e) {
    click = true;
  }
  public void mouseEntered(MouseEvent e) {
    in = true;
  }
  public void mouseExited(MouseEvent e) {
    in = false;
  }
  public void mousePressed(MouseEvent e) {
    pressed = true;
  }
  public void mouseReleased(MouseEvent e) {
    pressed = false;
  }

  public void mouseMoved(MouseEvent e){
    x = e.getX() - 5;
    y = e.getY() - 27;
  }
  public void mouseDragged(MouseEvent e){
    x = e.getX() - 5;
    y = e.getY() - 27;
  }

  public void postUse(){ click = false; }

  public static void main(String[] args){}
}

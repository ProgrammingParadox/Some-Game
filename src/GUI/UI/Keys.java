package GUI.UI;

import javax.swing.JFrame;
import java.awt.event.*;

public class Keys implements KeyListener {
  public boolean[] keys = new boolean[256];

  public Keys(JFrame frame) {
    frame.addKeyListener(this);
  }

  public void keyTyped(KeyEvent e){

  }
  public void keyPressed(KeyEvent e){
    keys[e.getKeyCode()] = true;
  }
  public void keyReleased(KeyEvent e){
    keys[e.getKeyCode()] = false;
  }

  public void postUse(){ }

  public static void main(String[] args){}
}

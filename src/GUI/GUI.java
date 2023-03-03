package GUI;

import javax.swing.*;       // Import everything from the swing package
import java.awt.*;		    // Import everything from the awt package
import java.util.ArrayList; // What can I say? ArrayList is useful

import GUI.UI.*;

public class GUI extends JPanel implements Runnable {
    private JFrame frame;

    Thread t;

    public int frameWidth = 1000;
    public int frameHeight = 800;

    public Mouse mouse;
    public Keys keys;

    public ArrayList<GUIShape> shapes = new ArrayList<GUIShape>();

    public int[] fill = new int[]{0, 0, 0, 255};
    public int[] stroke = new int[]{0, 0, 0, 255};
    public int strokeWeight = 1;

    public ArrayList<Pair<String, double[]>> transformations = new ArrayList<Pair<String, double[]>>(0);

    public String fontName = "Trebuchet MS";
    public int fontSize = 12;

    public GUI(String name, int w, int h){
        frame = new JFrame(name);

        frameWidth = w;
        frameHeight = h;

        frame.add(this);
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        t = new Thread(this);
        t.start();

        mouse = new Mouse(frame);
        keys  = new Keys (frame);
    }
    public GUI(String name){
        frame = new JFrame(name);

        frame.add(this);
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        t = new Thread(this);
        t.start();

        mouse = new Mouse(frame);
        keys  = new Keys (frame);
    }
    public GUI(){
        frame = new JFrame("GUI");

        frame.add(this);
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        t = new Thread(this);
        t.start();

        mouse = new Mouse(frame);
        keys  = new Keys (frame);
    }

    public Component add(Component obj){
        return frame.add(obj);
    }

    // maybe, for text metrics, have a local
    // graphics object I change along with 
    // textSize so when needed the text width
    // and stuff can be supplied?

    public ArrayList<Pair<String, double[]>> getTransformations(){
        return (ArrayList<Pair<String, double[]>>) transformations.clone();
    }
    public void setTransformations(ArrayList<Pair<String, double[]>> t){
        this.transformations = (ArrayList<Pair<String, double[]>>) t.clone();
    }

    public void translate(double x, double y){
        transformations.add(
            new Pair<String, double[]>(
                "translate", 
                new double[]{
                    (double) x, 
                    (double) y
                }
            )
        );
    }
    public void rotate(double theta){
        transformations.add(
            new Pair<String, double[]>(
                "rotate", 
                new double[]{
                    theta
                }
            )
        );
    }
    public void scale(double sx, double sy){
        transformations.add(
            new Pair<String, double[]>(
                "scale", 
                new double[]{
                    sx, sy
                }
            )
        );
    }
    public void shear(double shx, double shy){
        transformations.add(
            new Pair<String, double[]>(
                "shear", 
                new double[]{
                    shx, shy
                }
            )
        );
    }

    public void fill(int r, int g, int b, int a){
        this.fill = new int[]{r, g, b, a};
    }
    public void fill(int r, int g, int b){
        this.fill = new int[]{r, g, b, 255};
    }
    public void fill(int gray, int a){
        this.fill = new int[]{gray, gray, gray, a};
    }
    public void fill(int gray){
        this.fill = new int[]{gray, gray, gray, 255};
    }

    public void stroke(int r, int g, int b, int a){
        this.stroke = new int[]{r, g, b, a};
    }
    public void stroke(int r, int g, int b){
        this.stroke = new int[]{r, g, b, 255};
    }
    public void stroke(int gray, int a){
        this.stroke = new int[]{gray, gray, gray, a};
    }
    public void stroke(int gray){
        this.stroke = new int[]{gray, gray, gray, 255};
    }

    public void strokeWeight(int strokeWeight){
        this.strokeWeight = strokeWeight;
    }
    public void noStroke(){
        this.strokeWeight = 0;
    }

    public void font(String fontName, int size){
        this.fontName = fontName;
        this.fontSize = size;
    }
    public void font(String fontName){
        this.fontName = fontName;
    }
    public void fontSize(int size){
        this.fontSize = size;
    }

    public void rect(int x, int y, int w, int h) {
        this.shapes.add(new GUIShape(
            "rect",

            this.fill,
            this.stroke,

            this.strokeWeight,

            (ArrayList<Pair<String, double[]>>) this.transformations.clone(),

            new Object[]{ x, y, w, h }
        ));
    }
    public void polygon(int[] px, int[] py) {
        this.shapes.add(new GUIShape(
            "rect",

            this.fill,
            this.stroke,

            this.strokeWeight,

            (ArrayList<Pair<String, double[]>>) this.transformations.clone(),

            new Object[]{ px, py }
        ));
    }
    public void tri(int x1, int y1, int x2, int y2, int x3, int y3) {
        this.shapes.add(new GUIShape(
            "polygon",

            this.fill,
            this.stroke,

            this.strokeWeight,

            (ArrayList<Pair<String, double[]>>) this.transformations.clone(),

            new Object[]{ new int[]{ x1, x2, x3 }, new int[]{y1, y2, y3} }
        ));
    }
    public void ellipse(int x, int y, int w, int h) {
        this.shapes.add(new GUIShape(
            "ellipse",

            this.fill,
            this.stroke,

            this.strokeWeight,

            (ArrayList<Pair<String, double[]>>) this.transformations.clone(),

            new Object[]{ x, y, w, h }
        ));
    }
    public void line(int x, int y, int x2, int y2) {
        this.shapes.add(new GUIShape(
            "line",

            this.fill,
            this.stroke,

            this.strokeWeight,

            (ArrayList<Pair<String, double[]>>) this.transformations.clone(),

            new Object[]{ x, y, x2, y2 }
        ));
    }
    public void text(String txt, int x, int y) {
        this.shapes.add(new GUIShape(
            "text",

            this.fill,

            this.fontName,
            this.fontSize,

            (ArrayList<Pair<String, double[]>>) this.transformations.clone(),

            new Object[]{ txt, x, y }
        ));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,	
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2d.setRenderingHints(rh);

        for(int i = 0; i<this.shapes.size(); i++){
            if(this.shapes.get(i) == null || i>=this.shapes.size()){
                continue;
            }

            this.shapes.get(i).display(g2d);
        }

        mouse.postUse();
        keys.postUse();
    }

    public void draw(){
        /*
        strokeWeight(1);
        stroke(255, 0, 0, 255);
        fill(0, 0, 255, 255);
        rect(30, 30, 50, 50);

        strokeWeight(4);
        stroke(0, 255, 0, 255);
        fill(255, 0, 0, 255);
        ellipse(200, 200, 50, 50);

        textFont("Trebuchet MS", 100);
        text("hi", 100, 100);
        */
    }

    public void run() {
        while(true){
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      
            int scrW = (int)screenSize.getWidth();
            int scrH = (int)screenSize.getHeight();
      
            frameWidth = scrW;
            frameHeight = scrH;
      
            this.shapes.clear();
            this.transformations.clear();
      
            draw();
      
            // mouse.postUse();
            // keys.postUse();
      
            try {
              Thread.sleep(10);
            }catch(InterruptedException e){}
      
            repaint();
        }
    }

    public static void main(String[] args){
        GUI g = new GUI("This is a window");
    }
}
